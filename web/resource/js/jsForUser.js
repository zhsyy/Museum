const btSave = document.getElementById("btSave");
const userFormSubmit = document.getElementById("userFormSubmit");

btSave.onclick = function () {
    let Username = document.getElementById("name").value;
    let Email = document.getElementById("email").value;
    let Password = document.getElementById("password").value;
    let alertMessage = document.getElementById("alertMessage");
    // alert(Username+" "+Email+" "+Password);
    if (Username.length === 0 || Email.length === 0 || Password.length === 0) {
        alertMessage.innerText = "All fields checked! Going to update!";
    } else {
        alertMessage.innerText = " ";
        userFormSubmit.submit();
    }
};