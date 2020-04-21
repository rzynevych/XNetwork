const url = "http://" + window.location.host + "/posts";
console.log(window.location.pathname);
let form = document.getElementById("send-message-form-1");
let input;
if (form != null) {
    form.onsubmit = sendMessageHandler;
    input = form.querySelector(".input-message-1");
}
let container = document.getElementById("messages-container");
let offset = 0;
let timerId = 0;
loadItems({
    target : "load",
    location : window.location.pathname,
    offset : offset
},"beforeend", generateMessage, null)


container.onscroll = function () {
    if (timerId != 0 || offset < 0)
        return ;
    let messages = container.querySelectorAll(".message");
    let last = messages[messages.length - 1];
    let payload = {
        target : "load",
        location : window.location.pathname,
        offset : offset
    };
    if (last.getBoundingClientRect().top - container.getBoundingClientRect().bottom < 10)
        timerId = setTimeout(loadItems, 1000, payload, "beforeend", generateMessage, null);
};

function sendMessageHandler() {
    if(input.value == "")
        return false;
    let payload = {
        target : "send",
        text : input.value
    };
    fetch(url,
        {
            method: "POST",
            body: JSON.stringify(payload)
        }).then(response => response.json()).then(json => {
        if (json.result == "ok") {
            container.insertAdjacentElement("afterbegin", generateMessage(json.message));
        }
        else
            console.log(json.error);
    }).catch(console.log);
    input.value = "";
    return false;
}
