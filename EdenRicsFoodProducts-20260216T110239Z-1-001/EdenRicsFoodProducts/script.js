function toggleModal() {
    const modal = document.getElementById('modal');
    const overlay = document.getElementById('overlay');
    
    if (modal.style.display === "none" || modal.style.display === "") {
        modal.style.display = "block";
        overlay.style.display = "block";
    } else {
        modal.style.display = "none";
        overlay.style.display = "none";
    }
}function toggleModal() {
    const modal = document.querySelector('.modal');
    const overlay = document.querySelector('.overlay');
    
    // Toggle the visibility of the modal and overlay
    modal.classList.toggle('active');
    overlay.classList.toggle('active');
}function toggleModal() {
    const overlay = document.querySelector('.overlay');
    const modal = document.querySelector('.modal');
    modal.classList.toggle('active');
    overlay.classList.toggle('active');
}

function toggleLogin() {
    document.getElementById('signupWrapper').style.display = 'none';
    document.getElementById('loginWrapper').style.display = 'flex';
}

function toggleSignUp() {
    document.getElementById('loginWrapper').style.display = 'none';
    document.getElementById('signupWrapper').style.display = 'flex';
}
window.onload = function() {
    document.getElementById('modal').style.display = 'none';
    document.getElementById('overlay').style.display = 'none';
};
function toggleModal() {
    const modal = document.querySelector('.modal');
    const overlay = document.querySelector('.overlay');
    modal.classList.toggle('active');
    overlay.classList.toggle('active');
}

function toggleSignUp() {
    const loginWrapper = document.querySelector('.login-wrapper');
    const signupWrapper = document.querySelector('.signup-wrapper');

    // Hide the login form and show the sign-up form
    loginWrapper.classList.remove('active');
    signupWrapper.classList.add('active');
}

function toggleLogin() {
    const loginWrapper = document.querySelector('.login-wrapper');
    const signupWrapper = document.querySelector('.signup-wrapper');

    // Hide the sign-up form and show the login form
    signupWrapper.classList.remove('active');
    loginWrapper.classList.add('active');
}