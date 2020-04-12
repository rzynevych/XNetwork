url = "http://localhost:8080/friends";
const addButtons = document.getElementsByClassName("add-button");
console.log(addButtons.length);
function responseHandler(json, form, button) {
    if (json.result == "success") {
        button.innerText = json.action == "Add" ? "Remove from friends" : "Add to friends";
        let action = form.getElementsByClassName("action-input")[0];
        action.value = json.action == "Add"? "Remove" : "Add";
    } else {
        alert("Error");
    }
}
for (let i = 0; i < addButtons.length; i++) {
    addButtons[i].onclick = async function () {
        let form = document.getElementById("add-friend-form-" + this.id);
        fetch(url,
            {
                method: "POST",
                body: new FormData(form)
            }).then(response => response.json()).then(json => responseHandler(json, form, this)).catch(console.log);
    }
}