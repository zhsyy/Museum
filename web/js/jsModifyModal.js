const modifySubmit = document.getElementById("modifySubmit");
const alertModify = document.getElementById("alertModify");
const modifyUserId = document.getElementById("userId");
const modifyPassword = document.getElementById("modifyPassword");

const modify = document.getElementById("modify");

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

modifySubmit.onclick = function () {
    if (null === modifyPassword.value || modifyPassword.value === "") {// password not entered
        alertModify.innerText = "Please enter your password.";
    } else {// success
        alertModify.innerText = "";
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4){
                if ((xhr.status >= 200 && xhr.status < 300) || xhr.status === 304){
                    alertModify.innerText = xhr.response;
                    if (alertModify.innerText.trim() === "") {// valid password
                        modify.submit();
                    }
                } else {
                    alert("Request was unsuccessful: " + xhr.status);
                }
            }
        };

        xhr.open("post", "/checkPassword.user", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.send("userId=" + modifyUserId.value + "&password=" + modifyPassword.value);
    }
};