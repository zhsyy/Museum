const signIn = document.getElementById("signIn");
const signInSubmit = document.getElementById("signInSubmit");

signInSubmit.onclick = function () {
    let userName = document.getElementById("signInUserName").value;
    let password = document.getElementById("signInPassword").value;
    let verify = document.getElementById("verify").value;

    let alertSignIn = document.getElementById("alertSignIn");

    if (checkVerify(verify)){// input correct verify code
        if (userName.length === 0 || password.length === 0) {
            alertSignIn.innerText = "Empty user name or password!";
            changeVerify();
        } else {// submit to check username and password
            signIn.submit();
        }
    } else {
        alertSignIn.innerText = "Wrong verify code!";
        changeVerify();
    }
};

function checkVerify(verify) {
    let setVerify = document.getElementById("code").innerText;

    return verify === setVerify;
}

function changeVerify() {
    document.getElementById("code").innerText = (Math.round(Math.random() * 8999) + 1000).toString();
}
