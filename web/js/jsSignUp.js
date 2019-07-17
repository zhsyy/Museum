const signUp = document.getElementById("signUp");
const signUpSubmit = document.getElementById("signUpSubmit");
const signUpCommit = document.getElementById("signUpCommit");

const signUpUserName = document.getElementById("signUpUserName");
const signUpEmail = document.getElementById("signUpEmail");
const signUpPassword = document.getElementById("signUpPassword");
const signUpPasswordConfirm = document.getElementById("signUpPasswordConfirm");
const signUpPhone = document.getElementById("signUpPhone");
const signUpAddress = document.getElementById("signUpAddress");

const alertUserName = document.getElementById("alertUserName");
const alertEmail = document.getElementById("alertEmail");
const alertPassword = document.getElementById("alertPassword");
const alertPasswordConfirm = document.getElementById("alertPasswordConfirm");
const alertPhone = document.getElementById("alertPhone");
const alertAddress = document.getElementById("alertAddress");

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

function serialize(form){
    let parts = Array();
    let field = null;

    for (let i=0, len=form.elements.length; i < len; i++){
        field = form.elements[i];

        switch(field.type){
            case "select-one":
            case "select-multiple":
                for (let j=0, optLen = field.options.length; j < optLen; j++){
                    let option = field.options[j];
                    if (option.selected){
                        let optValue = "";
                        if (option.hasAttribute){
                            optValue = (option.hasAttribute("value") ?
                                option.value : option.text);
                        } else {
                            optValue = (option.attributes["value"].specified ?
                                option.value : option.text);
                        }
                        parts.push(encodeURIComponent(field.name) + "=" +
                            encodeURIComponent(optValue));
                    }
                }
                break;

            case undefined:     //fieldset
            case "file":        //file input
            case "submit":      //submit button
            case "reset":       //reset button
            case "button":      //custom button
                break;

            case "radio":       //radio button
            case "checkbox":    //checkbox
                if (!field.checked){
                    break;
                }
            /* falls through */

            default:
                parts.push(encodeURIComponent(field.name) + "=" +
                    encodeURIComponent(field.value));
        }
    }
    return parts.join("&");
}

signUpSubmit.onclick = function () {
    let username = signUpUserName.value;
    let email = signUpEmail.value;
    let password = signUpPassword.value;
    let passwordConfirm = signUpPasswordConfirm.value;
    let phone = signUpPhone.value;
    let address = signUpAddress.value;

    checkSignUpUserName(username);
    checkSignUpEmail(email);
    checkSignUpPassword(password);
    checkSignUpPasswordConfirm(passwordConfirm);
    checkSignUpPhone(phone);
    checkSignUpAddress(address);

    if (alertUserName.innerText !== "" || alertEmail.innerText !== "" || alertPassword.innerText !== "" ||
        alertPasswordConfirm.innerText !== "" || alertPhone.innerText !== "" || alertAddress.innerText !== "") {
        signUpInfo.innerText = "Sign up failed! Please refine your information. ";
    } else {
        signUpInfo.innerText = "Signed up successfully!";
    }
};

signUpCommit.onclick = function () {
    if (signUpInfo.innerText === "Signed up successfully!")
        signUp.submit();
};

function checkSignUpUserName(username) {
    if (username === "") {// 用户名为空
        alertUserName.innerText = "User name cannot be empty!";
        return false;
    }else if (username.length < 6) {// 长度小于6位
        alertUserName.innerText = "User name cannot be shorter than 6 characters!";
        return false;
    }else if (/^[a-zA-Z]+$/.test(username) || /^\d+$/.test(username)) {// 全部是数字或字母
        alertUserName.innerText = "User name cannot be only numbers or characters!";
        return false;
    }else {
        // check if username has been used
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4){
                if ((xhr.status >= 200 && xhr.status < 300) || xhr.status === 304){
                    if (xhr.response) {
                        alertUserName.innerText = "";
                        return true;
                    } else {
                        alertUserName.innerText = "Sorry! This name has been used. Please change another one.";
                        return false;
                    }
                } else {
                    alert("Request was unsuccessful: " + xhr.status);
                }
            }
        };

        xhr.open("get", "checkSignUp.php?username=" + username, true);
        xhr.send(null);
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
    let username = signUpUserName.value;

    if (password === "") {// 密码为空
        alertPassword.innerText = "Password cannot be empty!";
        return false;
    }else if (password.length < 6) {// 长度小于6位
        alertPassword.innerText = "Password cannot be shorter than 6 characters!";
        return false;
    }else if (/^\d+$/.test(password) || /^[a-zA-Z]+$/.test(password)) {// 纯数字或纯字母
        alertPassword.innerText = "Password cannot be only numbers or characters!";
        return false;
    } else if (password === username) {// 密码与用户名相同
        alertPassword.innerText = "Password cannot be the same as user name!";
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

function checkSignUpPhone(phone) {
    if (!/^\d+$/.test(phone)) {// 不是纯数字
        alertPhone.innerText = "Phone number should be all numbers!";
        return false;
    }else if (phone.length !== 11) {// 长度不为11位
        alertPhone.innerText = "Phone number should be 11 numbers!";
        return false;
    }else {
        alertPhone.innerText = "";
        return true;
    }
}

function checkSignUpAddress(address) {
    if (address === "") {
        alertAddress.innerText = "Address cannot be empty!";
        return false;
    }else {
        alertAddress.innerText = "";
        return true;
    }
}
