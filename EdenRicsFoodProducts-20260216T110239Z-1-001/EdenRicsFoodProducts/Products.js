const foodItems = document.querySelectorAll('.food-items');

foodItems.forEach(item => {
    item.addEventListener('mouseenter', () => {
        foodItems.forEach(otherItem => {
            if (otherItem !== item) {
                otherItem.classList.add('blur'); // Add blur class to other items
            }
        });
    });

    item.addEventListener('mouseleave', () => {
        foodItems.forEach(otherItem => {
            otherItem.classList.remove('blur'); // Remove blur class from all items
        });
    });
});

let cartCount = 0;

function addToCart(productName, productPrice) {
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    
    // Check if the product is already in the cart
    const itemExists = cart.some(item => item.name === productName);

    if (itemExists) {
        // Show custom message using modal
        showCustomModal('Item already exists in your cart!');
    } else {
        // Add item to the cart
        cart.push({ name: productName, price: productPrice });
        localStorage.setItem('cart', JSON.stringify(cart));
        showCustomModal('Item successfully added to your cart!');
    }
}

// Function to show a custom modal message
function showCustomModal(message) {
    const modal = document.getElementById('addToCartModal');
    const modalContent = document.querySelector('.modal-content p');
    
    modalContent.textContent = message; // Set the custom message dynamically
    modal.style.display = 'block';
}

// Close modal when OK button is clicked
function closeModal() {
    const modal = document.getElementById('addToCartModal');
    modal.style.display = 'none';
}

// Handle clicks outside modal to close it
window.onclick = function (event) {
    const modal = document.getElementById('addToCartModal');
    if (event.target === modal) {
        modal.style.display = 'none';
    }
}

 function goToAddToCartPage() {
    window.location.href = 'addToCart.html'; // Redirect to the addToCart page
}
