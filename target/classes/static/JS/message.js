function messageGenerator(json) {
    let message = document.createElement ("div");
    message.className = "message";
    message.id = "id-" + json.message_id;
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
    return message;
}