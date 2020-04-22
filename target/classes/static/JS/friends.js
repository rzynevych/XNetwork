const url = "http://" + window.location.host + "/friends";
let searchForm = document.querySelector(".send-message-form-1");
let container = document.getElementById("users-container");
let offset = 50;
let timerId = 0;

let forms = document.getElementsByClassName("add-friend-form");
for (let i = 0; i < forms.length; i++) {
    forms[i].onsubmit = addFriend;
}

if (searchForm != undefined) {
    searchForm.onsubmit = function () {

        let payload = {
            target: "search",
            query: document.getElementById("input_query").value
        };
        let items = container.querySelectorAll(".user");
        for (let i = 0; i < items.length; i++)
            container.removeChild(items[i]);
        loadItems(payload, "beforeend", generateUser, () => {});
        return false;
    };
}

container.onscroll = function () {
    if (timerId != 0 || offset < 0)
        return ;
    let users = container.querySelectorAll(".user");
    let last = users[users.length - 1];
    let payload = {
        target : "load",
        location : window.location.pathname,
        query: window.location.pathname == "/searchUsers" ? document.getElementById("input_query").value : null,
        offset : offset
    };
    if (last.getBoundingClientRect().top - container.getBoundingClientRect().bottom < 10)
        timerId = setTimeout(loadItems, 1000, payload, "beforeend", generateUser, json => {
            offset += 50;
            if (json.items.length < 50)
                offset = -1;
        });
};

function addFriend() {
    let button = this.querySelector(".users-form-button");
    let id_input = this.querySelector(".id-input");
    let action_input = this.querySelector(".action-input");
    let payload = {
        target : "friend-handling",
        id : id_input.value,
        action : action_input.value
    };
    fetch(url,
        {
            method: "POST",
            body: JSON.stringify(payload)
        }).then(response => response.json()).then(json => {
        if (json.result == "ok") {
            button.innerText = json.action == "Add" ? "Remove from friends" : "Add to friends";
            let action = this.querySelector(".action-input");
            action.value = json.action == "Add"? "Remove" : "Add";
        } else {
            console.log(json.error);
        }
    }).catch(console.log);
    return false;
}