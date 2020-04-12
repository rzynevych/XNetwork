url = "http://localhost:8080/userPosts";
form = document.getElementById("send-message-form-1");
form.onsubmit = sendMessageHandler;

function sendMessageHandler() {
    fetch(url,
        {
            method: "POST",
            body: new FormData(this)
        }).then(response => response.json()).then(json => responseHandler(json, form, this)).catch(console.log);
    return false;
}

function responseHandler(json) {
    if (json.result == "ok") {
        let container = document.getElementById("messages-container");
        let message = document.createElement ("div");
        message.className = "message";
        let header = document.createElement("div");
        header.className = "message-header";
        let username = document.createElement("span");
        username.className = "message-username";
        username.innerText = json.username;
        let date = document.createElement("span");
        date.className = "message-date";
        date.innerText = json.date;
        header.appendChild(username);
        header.appendChild(date);
        let text = document.createElement("div");
        text.className = "message-text";
        text.innerText = json.text;
        let divider = document.createElement("div");
        divider.className = "divider";
        message.appendChild(header);
        message.appendChild(text);
        message.appendChild(divider);
        container.insertAdjacentElement("afterbegin", message);
    }
}

