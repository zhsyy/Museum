const btSearch = document.getElementById("btSearch");

btSearch.onclick = function () {
    const searchAlert = document.getElementById("searchAlert");
    const ckbTitle = document.getElementById("ckbTitle");
    const ckbIntroduction = document.getElementById("ckbIntroduction");
    const ckbLocation = document.getElementById("ckbLocation");
    const filterView = document.getElementById("filterView");
    const search = document.getElementById("search");

    if (ckbTitle.checked || ckbIntroduction.checked || ckbLocation.checked) {
        searchAlert.className = "invisible";
        if (filterView!=null&&filterView.checked){
            $("#sortBy").attr("value","view");
        }
        else
            $("#sortBy").attr("value","");
        search.submit();
    } else {// none of the checkboxes checked
        searchAlert.innerText = "Please choose.";
        searchAlert.className = "visible alert";
    }
};
