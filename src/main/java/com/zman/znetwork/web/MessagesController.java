package com.zman.znetwork.web;

import com.zman.znetwork.auth.UserHandler;
import com.zman.znetwork.models.messages.Message;
import com.zman.znetwork.models.messages.MessageDAO;
import com.zman.znetwork.models.users.AppUser;
import com.zman.znetwork.models.users.AppUserDAO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import static com.zman.znetwork.utils.JsonGenerator.*;

@Controller
public class MessagesController {

    @Autowired
    private AppUserDAO appUserDAO;

    @Autowired
    private MessageDAO messageDAO;

    @GetMapping("chats")
    public String chats(Model model) {
        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        List<AppUser> users = messageDAO.getChatUsers(appUser.getId(), 0);
        model.addAttribute("users", users);
        model.addAttribute("appUser", appUser);
        return "chats";
    }

    @PostMapping("chats")
    public ResponseEntity<String> loadChats(@RequestBody String json) {
        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        JSONObject jsonObject = new JSONObject(json);
        List<AppUser> users = messageDAO.getChatUsers(appUser.getId(), jsonObject.getInt("offset"));
        JSONObject response = new JSONObject();
        JSONArray chatsArray = generateChatsArray(users);
        response.put("result", "ok");
        response.put("items", chatsArray);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response.toString());
    }

    @GetMapping("chat")
    public String chat(@RequestParam int id, Model model) {
        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        AppUser user = appUserDAO.getById(id);
        List<Message> messages = messageDAO.getMessagesForChat(appUser.getId(), id, 0);
        Collections.reverse(messages);
        model.addAttribute("messages", messages)
                .addAttribute("title", user.getUsername())
                .addAttribute("action", "/chat")
                .addAttribute("id", id);
        return "chat";
    }

    @PostMapping("chat")
    public ResponseEntity<String> chatController(@RequestBody String payload) {
        AppUser user = UserHandler.getAuthorizedUser().getAppUser();
        JSONObject json = new JSONObject(payload);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();
        ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON);
        JSONObject response = new JSONObject();
        List<Message> messages;
        String target = json.getString("target");
        if (target.equals("update")) {
            String str = json.getString("last").replaceAll("\\D", "");
            long last = Long.parseLong(str);
            messages = messageDAO.getMessagesForUpdate(user.getId(),
                    json.getInt("user_id"), last);
            JSONArray messagesArray = generateMessagesArray(messages);
            response.put("result", "ok");
            response.put("items", messagesArray);
            return bodyBuilder.body(response.toString());
        } else if (target.equals("send")) {
            String text = json.getString("text");
            long id = messageDAO.insert(user.getId(), text, user.getUsername(), json.getInt("user_id"));
            response.put("result", "ok");
            response.put("message_id", id);
            response.put("message", generateMessage(new Message(id, user.getUsername(), text, formatter.format(now))));
            return bodyBuilder.body(response.toString());
        } else if (target.equals("load")) {
            messages = messageDAO.getMessagesForChat(user.getId(), json.getInt("user_id"), json.getInt("offset"));
            JSONArray messagesArray = generateMessagesArray(messages);
            response.put("result", "ok");
            response.put("items", messagesArray);
            return bodyBuilder.body(response.toString());
        } else {
            response.put("result", "error");
            response.put("error", "Unknown target");
            return bodyBuilder.body(response.toString());
        }
    }
}

