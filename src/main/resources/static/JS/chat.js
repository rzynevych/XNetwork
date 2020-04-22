const url = "http://" + window.location.host + "/chat";
const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
let form = document.getElementById("send-message-form-1");
form.onsubmit = sendMessageHandler;
let input = form.querySelector(".input-message-1");
let container = document.getElementById("messages-container");
let offset = 0;
let timerId = 0;
let updating = false;

loadItems({
    target : "load",
    user_id : urlParams.get("id"),
    offset : offset
},"afterbegin", generateMessage, json => {
    container.scrollTo(0, container.scrollHeight);
    offset += 50;
    if (json.items.length < 50)
        offset = -1;
});

container.onscroll = function () {
    if (timerId != 0 || offset < 0)
        return ;
    let first = container.querySelector(".message");
    if (first == undefined)
        return;
    let payload = {
        target : "load",
        user_id : urlParams.get("id"),
        offset : offset
    };
    if (container.getBoundingClientRect().top - first.getBoundingClientRect().top < 10)
        timerId = setTimeout(loadItems, 1000, payload, "afterbegin", generateMessage, json => {
            if (first.getBoundingClientRect().top > 100)
                container.scrollTo(0, first.getBoundingClientRect().top - container.getBoundingClientRect().top);
            if (json.items.length < 50)
                offset = -1;
            timerId = 0;
        });
};

function sendMessageHandler() {
    if (updating) {
        return false;
    }
    if (input.value == "")
        return false;
    let payload = {
        target : "send",
        user_id : urlParams.get("id"),
        text : input.value
    };
    fetch(url,
        {
            method: "POST",
            body: JSON.stringify(payload)
        }).then(response => response.json()).then(json => {
        console.log(JSON.stringify(json));
        if (json.result == "ok") {
            container.insertAdjacentElement("beforeend", generateMessage(json.message));
            container.scrollTo(0, container.scrollHeight);
        }
        else
            console.log(json.error);
    }).catch(console.log);
    input.value = "";
    return false;
}

setTimeout(updateMessages, 1000);
function updateMessages() {
    let messages = document.querySelectorAll(".message");
    if (messages.length == 0)
        return ;
    let last = messages[messages.length - 1];
    let payload = {
        target : "update",
        user_id : urlParams.get("id"),
        last : last.id
    };
    updating = true;
    loadItems(payload, "beforeend", generateMessage, json => {
        if (json.items.length > 0)
            container.scrollTo(0, container.scrollHeight);
        updating = false;
        setTimeout(updateMessages, 1000);
    })
}
