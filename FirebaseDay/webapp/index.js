// sign in anonymously
firebase.auth().signInAnonymously().catch((error) => {
  console.log('error signing in:', error);
});

// check for auth state change
firebase.auth().onAuthStateChanged((user) => {
  if (user) {
    // signed in
    console.log('signed in:', user);
  } else {
    // signed out
    console.log('signed out');
  }
});

// initialize the database connection
const db = firebase.firestore();

// add test data
// db.collection("messages").add({
//   content: 'Hello, World!',
//   author: "Tester",
//   timestamp: Date.now(),
//   uid: (firebase.auth().currentUser !== null)? firebase.auth().currentUser.uid : 'aaaaa'
// })
// .then(function(docRef) {
//   console.log("Document written with ID: ", docRef.id);
// })
// .catch(function(error) {
//   console.error("Error adding document: ", error);
// });

// get live updates
db.collection("messages").orderBy('timestamp', 'asc').onSnapshot(function(querySnapshot) {
  // empty message board
  clearAllMessages();

  // cycle through all documents
  querySnapshot.forEach((doc) => {
    // only add messages if they have content
    if (!doc.data().content || !doc.data().uid) {
      return;
    }

    // add message to board
    addMessage(
      ((!!doc.data().author)? doc.data().author  + ' ' : '') +
      '(uid: ' + doc.data().uid.substring(0, 5) + '...): ' +
      doc.data().content
    );
  });
});

// add a message to the board
function addMessage(message) {
  // create message p
  const messageP = document.createElement("p");

  // set attributes
  messageP.innerHTML = message;
  messageP.classList.add('message');

  // put the message along with the others
  document.getElementById("messages").prepend(messageP);
}

// clear all messages from the board
function clearAllMessages() {
  const messages = document.getElementById("messages")
  while (messages.firstChild) {
    messages.removeChild(messages.firstChild);
  }
}

// let counter = 5;
// document.getElementById("addMessage").addEventListener("click", () => {
//   addMessage(counter++);
// });
