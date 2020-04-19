const url = "http://" + window.location.host + "/chat";
const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
let form = document.getElementById("send-message-form-1");
form.onsubmit = sendMessageHandler;
let input = form.getElementsByClassName("input-message-1")[0];
let container = document.getElementById("messages-container");
container.scrollTo(0, container.scrollHeight);
let offset = 50;
let timerId = 0;

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
        timerId = setTimeout(loadItems, 1000, payload, "afterbegin", generateMessage, first);
};

function sendMessageHandler() {
    if(input.value == "")
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
    fetch(url,
        {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(payload)
        }).then(response => response.json()).then(json => {
        if (json.result == "ok") {
            for (let message of json.items) {
                container.insertAdjacentElement("beforeend", generateMessage(message));
            }
            if (json.items.length > 0)
                container.scrollTo(0, container.scrollHeight);
        } else
            console.log(json.error());
    }).catch(error => console.log(error));
    setTimeout(updateMessages, 1000);
}
