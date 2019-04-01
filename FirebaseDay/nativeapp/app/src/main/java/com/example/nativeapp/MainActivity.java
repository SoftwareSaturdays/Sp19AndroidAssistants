package com.example.nativeapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "FIREBASE-DAY";

    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        mAuth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                // If sign in fails, display a message to the user.
                if (!task.isSuccessful()) {
                    Log.w(TAG, "signInAnonymously:failure", task.getException());
                    Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                    updateUI(null);

                    return;
                }

                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "signInAnonymously:success");
                FirebaseUser user = mAuth.getCurrentUser();
                updateUI(user);
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
                    Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w(TAG, "Error adding document", e);
                }
            });

        // clear the edit text for a new message
        etMessage.setText("");
    }
}
