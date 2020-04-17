function loadMessages(payload, position, first) {
    fetch(url,
        {
            method: "POST",
            body: JSON.stringify(payload)
        }).then(response => response.json()).then(json => {
        if (json.result == "ok") {
            for (let message of json.messages) {
                container.insertAdjacentElement(position, messageGenerator(message));
            }
            if (first)
                container.scrollTo(0, first.getBoundingClientRect().top - container.getBoundingClientRect().top);
            offset += 50;
            if (json.messages.length < 50)
                offset = -1;
        }
        else
            console.log(json.error);
    }).catch(console.log);
    timerId = 0;
}

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