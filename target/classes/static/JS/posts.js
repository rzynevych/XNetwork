url = "http://10.0.0.20:8080/userPosts";
form = document.getElementById("send-message-form-1");
form.onsubmit = sendMessageHandler;
container = document.getElementById("messages-container");
input = form.getElementsByClassName("input-message-1")[0];

function sendMessageHandler() {
    if(input.value == "")
        return false;
    fetch(url,
        {
            method: "POST",
            body: new FormData(this)
        }).then(response => response.json()).then(json => {
        if (json.result == "ok") {
            container.insertAdjacentElement("afterbegin", messageGenerator(json));
        }
        else
            console.log(json.error);
    }).catch(console.log);
    input.value = "";
    return false;
}
