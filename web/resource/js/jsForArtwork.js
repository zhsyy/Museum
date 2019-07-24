const btSubmit = document.getElementById("btSubmit");
const artworkFormSubmit = document.getElementById("artworkFormSubmit");

btSubmit.onclick = function () {
    let submitWay = document.getElementById("submitWay").value;
    let title = document.getElementById("title").value;
    let description = document.getElementById("description").value;
    let location = document.getElementById("location").value;
    let yearOfWork = document.getElementById("yearOfWork").value;
    let alertMessage = document.getElementById("alertMessage");
    var fileInput = $('#imageFileName').get(0).files[0];
    if (title.length === 0 || description.length === 0 || location.length === 0 || yearOfWork.length===0) {
        alertMessage.innerText = "Please fill in all fields needed!";
    } else {
        if (submitWay==="modify"){
            alertMessage.innerText = "All fields checked! Going to update!";
            artworkFormSubmit.submit();
        } else {
            if (!fileInput)
                alertMessage.innerText = "Please fill in an image at least!";
            else {
                alertMessage.innerText = "All fields checked! Going to update!";
                artworkFormSubmit.submit();
            }
        }
    }
};