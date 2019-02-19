document.getElementById("addMessage").addEventListener("click", () => {
    addMessage("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut id orci porta, aliquet nulla non, ultricies quam. Donec ac venenatis odio. Aliquam non orci sed ante hendrerit condimentum. Donec sollicitudin diam ut metus ultrices fringilla. Vivamus at posuere justo. Morbi mollis pellentesque porta. Sed eget condimentum ipsum, ac eleifend mi. Phasellus auctor, nisi at dapibus fermentum, felis sem viverra felis, non molestie ipsum massa vel lectus. Cras imperdiet, urna semper fermentum facilisis, nibh libero faucibus sapien, sed placerat massa velit vel est.");
});

function addMessage(message) {
    // create message p
    const messageP = document.createElement("p");

    // set attributes
    messageP.innerHTML = message;
    messageP.classList.add('message');

    // put the message along with the others
    document.getElementById("messages").appendChild(messageP);
}
