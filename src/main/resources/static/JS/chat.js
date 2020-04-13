url = "http://10.0.0.20:8080/chat";
const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
form = document.getElementById("send-message-form-1");
form.onsubmit = sendMessageHandler;
input = form.getElementsByClassName("input-message-1")[0];
container = document.getElementById("messages-container");
container.scrollTo(0, container.scrollHeight);
offset = 50;

container.onscroll = function () {
    let first = container.getElementsByClassName("message")[0];
    if (container.getBoundingClientRect().top - first.getBoundingClientRect().top < 0) {
        //loading messages
    }
}

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
            container.insertAdjacentElement("beforeend", messageGenerator(json.message));
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
            for (let i = 0; i < json.messages.length; i++) {
                console.log(JSON.stringify(json.messages[i]));
                container.insertAdjacentElement("beforeend", messageGenerator(json.messages[i]));
            }
            if (json.messages.length > 0)
                container.scrollTo(0, container.scrollHeight);
        } else
            console.log(json.error());
    }).catch(console.log);
    setTimeout(updateMessages, 1000);
}


