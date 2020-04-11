url = "http://localhost:8080/friends";
const addButtons = document.getElementsByClassName("add-button");
console.log(addButtons.length);
for (let i = 0; i < addButtons.length; i++) {
    addButtons[i].onclick = async function () {
        let form = document.getElementById("add-friend-form-" + addButtons[i].id);
        let promise = await fetch(url,
            {
                method: "POST",
                body: new FormData(form)
            });
        if (promise.ok) {
            let action = form.getElementsByClassName("action-input")[0];
            addButtons[i].innerText = action.value == "Add" ? "Remove from friends" : "Add to friends";
            action.value = action.value == "Add"? "Remove" : "Add";
        } else {
            alert("Error");
        }
    }
}