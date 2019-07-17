const signIn = document.getElementById("signIn");
const signInSubmit = document.getElementById("signInSubmit");

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
            // xhr.onreadystatechange = function() {
            //     if (xhr.readyState === 4){
            //         if ((xhr.status >= 200 && xhr.status < 300) || xhr.status === 304){
            //             if (xhr.response !== null) {
            //                 alertSignIn.innerText = "Signed In successfully!";
            //                 signIn.submit();
            //             } else {
            //                 alertSignIn.innerText = "Wrong user name or password!";
            //                 changeVerify();
            //             }
            //         } else {
            //             alert("Request was unsuccessful: " + xhr.status);
            //         }
            //     }
            // };
            //
            // xhr.open("post", "/checkLogin", true);
            // xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            // xhr.send(serialize(signIn));
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
