function loadItems(payload, position, itemGenerator, callback) {
    fetch(url,
        {
            method: "POST",
            body: JSON.stringify(payload)
        }).then(response => response.json()).then(json => {
        if (json.result == "ok") {
            for (let item of json.items) {
                container.insertAdjacentElement(position, itemGenerator(item));
            }
            callback(json);
        }
        else
            console.log(json.error);
    }).catch(console.log);
}

function generateChat(json) {

    let chat = document.createElement("div");
        chat.className = "chat-user";
    let link = document.createElement("a");
        link.className = "chat-user-link";
        link.innerText = json.username;
        link.href = "chat?id=" + json.id;
    let email = document.createElement("span");
        email.innerText = json.email;
        email.className = "chat-user-email";
    chat.appendChild(link);
    chat.appendChild(email);
    return chat;
}

function generateUser(json) {
    let user = document.createElement ("div");
        user.className = "user";
    let username = document.createElement("span");
        username.className = "user-username";
        username.innerText = json.username;
    let email = document.createElement("span");
        email.className = "user-email";
        email.innerText = json.email;
    let add_form = document.createElement("form");
        add_form.method = "POST";
        add_form.action = "/friends";
        add_form.className = "add-friend-form";
        add_form.onsubmit = addFriend;
    let id = document.createElement("input");
        id.type = "hidden";
        id.className = "id-input";
        id.name = "id";
        id.value = json.id;
        add_form.appendChild(id);
    let action = document.createElement("input");
        action.type = "hidden";
        action.className = "action-input";
        action.name = "action";
        action.value = json.friend ? "Remove" : "Add";
    add_form.appendChild(action);
    let button = document.createElement("button");
        button.type = "submit";
        button.className = "users-form-button";
        button.innerText = json.friend ? "Remove from friends" : "Add to friends";
    add_form.appendChild(button);
    let chat_form = document.createElement("form");
        chat_form.method = "GET";
        chat_form.action = "/chat";
     let button1 = document.createElement("button");
        button1.type = "submit";
        button1.className = "users-form-button";
        button1.innerText = "Open chat";
    let id1 = document.createElement("input");
        id1.type = "hidden";
        id1.className = "id-input";
        id1.name = "id";
        id1.value = json.id;
    chat_form.appendChild(id1);
    chat_form.appendChild(button1);
    let divider = document.createElement("div");
        divider.className = "divider";
    user.appendChild(username);
    user.appendChild(email);
    let container = document.createElement("div");
    container.className = "user-forms-container";
    container.appendChild(add_form);
    container.appendChild(chat_form);
    user.appendChild(container);
    user.appendChild(divider);
    return user;
}

function generateMessage(json) {
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