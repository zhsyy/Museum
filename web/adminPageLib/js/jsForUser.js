const btSave = document.getElementById("btSave");
const userFormSubmit = document.getElementById("userFormSubmit");

btSave.onclick = function () {
    let Username = document.getElementById("Username").value;
    let Email = document.getElementById("Email").value;
    let Password = document.getElementById("Password").value;
    let alertMessage = document.getElementById("alertMessage");

    if (Username.length === 0 || Email.length === 0 || Password.length === 0) {
        alertMessage.innerText = "All fields checked! Going to update!";
    } else {
        userFormSubmit.submit();
    }
};