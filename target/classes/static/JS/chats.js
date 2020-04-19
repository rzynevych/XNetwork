const url = "http://" + window.location.host + "/chats";
let container = document.querySelector(".items-container");
let offset = 50;
let timerId = 0;

container.onscroll = function () {
    if (timerId != 0 || offset < 0)
        return ;
    let chats = container.querySelectorAll(".chat-user");
    let last = chats[chats.length - 1];
    let payload = {
        target : "load",
        location : window.location.pathname,
        offset : offset
    };
    if (last.getBoundingClientRect().top - container.getBoundingClientRect().bottom < 10)
        timerId = setTimeout(loadItems, 1000, payload, "beforeend", generateChat(), null);
};