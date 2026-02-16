const nameInput = document.getElementById('nameInput');
const nameError = document.getElementById('nameError');

nameInput.addEventListener('input', function() {
    // Regular expression to allow only letters and spaces
    const regex = /^[A-Za-z\s]*$/;
    if (!regex.test(nameInput.value)) {
        nameError.textContent = "Name can only contain letters and spaces.";
    } else {
        nameError.textContent = ""; // Clear error message
    }
});

document.getElementById('commentForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent the form from submitting normally

    const nameInput = document.getElementById('nameInput');
    const productSelect = document.getElementById('productSelect');
    const commentInput = document.getElementById('commentInput');

    const name = nameInput.value;
    const product = productSelect.value;
    const commentText = commentInput.value;

    // Get the current date and time without seconds
    const dateTime = new Date().toLocaleString([], { 
        year: 'numeric', 
        month: '2-digit', 
        day: '2-digit', 
        hour: '2-digit', 
        minute: '2-digit' 
    }).replace(',', ''); // Remove the comma between date and time

    // Check for negative phrases
    const negativePhrases = ["disgusting", "i don't like it", "trash", "don't buy it","shit","taste wierd","heck","fuck"
        ,"disgusting","throw up","pangit","wag nyo bilihin","ang data","anong lasa to","walang kwenta","sayang pera"];
    const containsNegativePhrase = negativePhrases.some(phrase => commentText.toLowerCase().includes(phrase));

    // Create a new list item for the comment
    const li = document.createElement('li');
    li.className = 'comment-item';
    li.innerHTML = `
        <div>
            <strong>${name} (${product})</strong><br>
            <span>${commentText}</span><br>
            <small>${dateTime}</small>
        </div>
        <div class="comment-actions">
            <button class="vote-btn" onclick="upvote(this)">Upvote</button>
            <button class="vote-btn" onclick="downvote(this)">Downvote</button>
            <button class="delete-btn">Delete</button>
        </div>
        <ul class="replies"></ul>
    `;

    // Append the new comment to the comments list only if it doesn't contain negative phrases
    if (!containsNegativePhrase) {
        document.getElementById('commentsList').appendChild(li);
    } else {
        li.style.display = 'none'; // Hide the comment if it contains negative phrases
    }

    // Clear the input fields
    nameInput.value = '';
    productSelect.selectedIndex =  0; // Reset the product selection to the default option
    commentInput.value = '';

    // Add event listener for the delete button
    li.querySelector('.delete-btn').addEventListener('click', function() {
        li.remove(); // Remove the comment from the list
    });
});

// Upvote function
function upvote(button) {
    const commentItem = button.closest('.comment-item');
    let upvoteCount = commentItem.querySelector('.upvote-count');
    if (!upvoteCount) {
        upvoteCount = document.createElement('span');
        upvoteCount.className = 'upvote-count';
        upvoteCount.textContent = ' Upvotes: 1';
        commentItem.querySelector('.comment-actions').appendChild(upvoteCount);
    } else {
        const count = parseInt(upvoteCount.textContent.split(': ')[1]) + 1;
        upvoteCount.textContent = ` Upvotes: ${count}`;
    }
}

// Downvote function
function downvote(button) {
    const commentItem = button.closest('.comment-item');
    let downvoteCount = commentItem.querySelector('.downvote-count');
    if (!downvoteCount) {
        downvoteCount = document.createElement('span');
        downvoteCount.className = 'downvote-count';
        downvoteCount.textContent = ' Downvotes: 1';
        commentItem.querySelector('.comment-actions').appendChild(downvoteCount);
    } else {
        const count = parseInt(downvoteCount.textContent.split(': ')[1]) + 1;
        downvoteCount.textContent = ` Downvotes: ${count}`;
    }
}