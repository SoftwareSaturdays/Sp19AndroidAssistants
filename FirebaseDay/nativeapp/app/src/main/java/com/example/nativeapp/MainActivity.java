package com.example.nativeapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    // auth instance
    private FirebaseAuth mAuth;

    // Firestore instance
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create new auth instance
        mAuth = FirebaseAuth.getInstance();

        // sign in, handle success and failure
        mAuth.signInAnonymously()
                .addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(MainActivity.this, "Authenticated!", Toast.LENGTH_SHORT).show();

                        // retrieve the newly signed-in user
                        FirebaseUser user = mAuth.getCurrentUser();

                        updateUI(user);
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Authentication failed :(", Toast.LENGTH_SHORT).show();

                        updateUI(null);
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    public void updateUI(final FirebaseUser user) {
        final TextView tvUid = findViewById(R.id.tvUid);

        if (user == null) {
            tvUid.setText("uid: N/A");
            return;
        }

        tvUid.setText("uid: " + user.getUid());
    }

    public void sendMessage(View v) {
        // get the current user's uid
        final FirebaseUser currentUser = mAuth.getCurrentUser();
        final String uid = currentUser.getUid();

        // get the message to be sent
        final EditText etMessage = findViewById(R.id.etMessage);
        final String message = etMessage.getText().toString();

        // get the current UNIX timestamp
        final String timestamp = String.valueOf(
                TimeUnit.MILLISECONDS.toSeconds(
                        System.currentTimeMillis()
                )
        );

        // get the author's name
        final EditText etAuthor = findViewById(R.id.etAuthor);
        final String author = etAuthor.getText().toString();

        // gather up all of the data that we want to send
        Map<String, Object> data = new HashMap<>();
        data.put("timestamp", timestamp);
        data.put("uid", uid);
        data.put("content", message);
        data.put("author", author);

        // add the new message document to the messages collection
        db.collection("messages").add(data)
            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(
                            MainActivity.this,
                            "DocumentSnapshot written with ID: " + documentReference.getId(),
                            Toast.LENGTH_SHORT
                    ).show();
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(
                            MainActivity.this,
                            "Error adding document",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            });

        // clear the edit text for a new message
        etMessage.setText("");
    }
}


 data.put("author", author);
 // get the author's name
        final EditText etAuthor = findViewById(R.id.etAuthor);
        final String author = etAuthor.getText().toString();
