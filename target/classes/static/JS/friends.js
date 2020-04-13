url = "http://10.0.0.20:8080/friends";
const addButtons = document.getElementsByClassName("add-button");

for (let i = 0; i < addButtons.length; i++) {
    addButtons[i].onclick = async function () {
        let form = document.getElementById("add-friend-form-" + this.id);
        fetch(url,
            {
                method: "POST",
                body: new FormData(form)
            }).then(response => response.json()).then(json => {
            if (json.result == "ok") {
                this.innerText = json.action == "Add" ? "Remove from friends" : "Add to friends";
                let action = form.getElementsByClassName("action-input")[0];
                action.value = json.action == "Add"? "Remove" : "Add";
            } else {
                console.log(json.error);
            }
        }).catch(console.log);
    }
}