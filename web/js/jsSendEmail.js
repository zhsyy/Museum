const btSend = document.getElementById("btSend");
const emailForm = document.getElementById("emailForm");
btSend.onclick = function () {
    let content = document.getElementById("content").value;
    let title = document.getElementById("title").value;
    let send = document.getElementById("send").value;

    let alertMessage = document.getElementById("alertMessage");

    if (send.length === 0 || title.length === 0 || content.length ===0 ) {
        alertMessage.innerText = "All fields checked! Going to send email!";
            changeVerify();
        } else {// submit to check username and password
        alertMessage.innerText = " ";
        emailForm.submit();
        }
};