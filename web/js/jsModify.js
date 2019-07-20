const signUp = document.getElementById("signUp");
const signUpSubmit = document.getElementById("signUpSubmit");
const signUpCommit = document.getElementById("signUpCommit");

const signUpUserName = document.getElementById("signUpUserName");
const signUpEmail = document.getElementById("signUpEmail");
const signUpPassword = document.getElementById("signUpPassword");
const signUpPasswordConfirm = document.getElementById("signUpPasswordConfirm");
const signUpSignature = document.getElementById("signUpSignature");

const alertUserName = document.getElementById("alertUserName");
const alertEmail = document.getElementById("alertEmail");
const alertPassword = document.getElementById("alertPassword");
const alertPasswordConfirm = document.getElementById("alertPasswordConfirm");
const alertSignature = document.getElementById("alertSignature");

const signUpInfo = document.getElementById("signUpInfo");

let xhr = createXHR();

function createXHR(){
    if (typeof XMLHttpRequest !== "undefined"){
        return new XMLHttpRequest();
    } else if (typeof ActiveXObject !== "undefined"){
        if (typeof arguments.callee.activeXString !== "string"){
            let versions = ["MSXML2.XMLHttp.6.0", "MSXML2.XMLHttp.3.0", "MSXML2.XMLHttp"], i, len;

            for (i=0,len=versions.length; i < len; i++){
                try {
                    let xhr = new ActiveXObject(versions[i]);
                    arguments.callee.activeXString = versions[i];
                    return xhr;
                } catch (ex){
                    //skip
                }
            }
        }
        return new ActiveXObject(arguments.callee.activeXString);
    } else {
        throw new Error("No XHR object available.");
    }
}

signUpSubmit.onclick = function () {
    let username = signUpUserName.value;
    let email = signUpEmail.value;
    let password = signUpPassword.value;
    let passwordConfirm = signUpPasswordConfirm.value;
    let signature = signUpSignature.value;

    checkSignUpUserName(username);
    checkSignUpEmail(email);
    checkSignUpPassword(password);
    checkSignUpPasswordConfirm(passwordConfirm);
    checkSignUpSignature(signature);

    if (alertUserName.innerText.trim() === "" &&
        alertEmail.innerText.trim() === "" &&
        alertPassword.innerText.trim() === "" &&
        alertPasswordConfirm.innerText.trim() === "" &&
        alertSignature.innerText === "") {// all fields passed
        signUpInfo.innerText = "All fields checked! Going to modify!";
    } else {
        signUpInfo.innerText = "Modify failed! Please refine your information. ";
    }
};

signUpCommit.onclick = function () {
    if (signUpInfo.innerText === "All fields checked! Going to modify!")
        signUp.submit();
};

function checkSignUpUserName(username) {
    if (username === "") {// 用户名为空
        alertUserName.innerText = "User name cannot be empty!";
        return false;
    }else if (username.length < 4) {// 长度小于4位
        alertUserName.innerText = "User name cannot be shorter than 4 characters!";
        return false;
    }else if (username.length > 15) {// 长度大于15位
        alertUserName.innerText = "User name cannot be longer than 15 characters!";
        return false;
    }else {// check if username has been used
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4){
                if ((xhr.status >= 200 && xhr.status < 300) || xhr.status === 304){
                    alertUserName.innerText = xhr.response;
                } else {
                    alert("Request was unsuccessful: " + xhr.status);
                }
            }
        };

        xhr.open("post", "/checkSignUp.user", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.send("username=" + username);
    }
}

function checkSignUpEmail(email) {
    if (email === "") {// 邮箱为空
        alertEmail.innerText = "Email cannot be empty!";
        return false;
    }else if (!/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(email)) {// 邮箱不符合规范
        alertEmail.innerText = "Please input a right email address!";
        return false;
    }else {
        alertEmail.innerText = "";
        return true;
    }
}

function checkSignUpPassword(password) {
    if (password === "") {// 密码为空
        alertPassword.innerText = "Password cannot be empty!";
        return false;
    }else if (password.length < 6) {// 长度小于6位
        alertPassword.innerText = "Password cannot be shorter than 6 characters!";
        return false;
    }else if (password.length > 10) {// 长度大于10位
        alertPassword.innerText = "Password cannot be longer than 10 characters!";
        return false;
    }else if (!/^[a-zA-Z0-9]+$/.test(password)) {// 包含除字母和数字之外的内容
        alertPassword.innerText = "Password cannot be consist of characters other than letters and numbers!";
        return false;
    }else if (!(/\d/.test(password) && /[a-z]/.test(password) && /[A-Z]/.test(password))) {// 不同时包含大小写字母和数字
        alertPassword.innerText = "Password cannot be only numbers or lower case letters or upper case letters!";
        return false;
    }else {
        alertPassword.innerText = "";
        return true;
    }
}

function checkSignUpPasswordConfirm(passwordConfirm) {
    let password = signUpPassword.value;

    if (passwordConfirm === "") {// 确认密码为空
        alertPasswordConfirm.innerText = "Confirm password cannot be empty!";
        return false;
    }else if (passwordConfirm !== password) {// 密码与确认密码不同
        alertPasswordConfirm.innerText = "Different from password!";
        return false;
    }else {
        alertPasswordConfirm.innerText = "";
        return true;
    }
}

function checkSignUpSignature(signature) {
    if (signature.length > 120) {
        alertSignature.innerText = "Signature is too long!";
        return false;
    } else {
        alertSignature.innerText = "";
        return true;
    }
}
