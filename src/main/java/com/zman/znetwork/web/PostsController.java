package com.zman.znetwork.web;

import com.zman.znetwork.auth.UserHandler;
import com.zman.znetwork.models.messages.Message;
import com.zman.znetwork.models.messages.MessageDAO;
import com.zman.znetwork.models.users.AppUser;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.zman.znetwork.utils.JsonGenerator.generateMessage;
import static com.zman.znetwork.utils.JsonGenerator.generateMessagesArray;

@Controller
public class PostsController {

    @Autowired
    private MessageDAO messageDAO;

    @GetMapping("/posts")
    public String posts(Model model) {
        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        List<Message> messages = messageDAO.selectPosts(appUser.getId(), 0);
        model.addAttribute("messages", messages);
        return "posts";
    }

    @GetMapping("/userPosts")
    public String userPosts(Model model) {
        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        List<Message> messages = messageDAO.getMessagesForChat(appUser.getId(), 0, 0);
        model.addAttribute("messages", messages);
        model.addAttribute("action", "/userPosts");
        model.addAttribute("from", "userPosts");
        return "userPosts";
    }

    @PostMapping("/posts")
    public ResponseEntity<String> send(@RequestBody String payload) {

        AppUser user = UserHandler.getAuthorizedUser().getAppUser();
        JSONObject json = new JSONObject(payload);
        JSONObject response = new JSONObject();
        String target = json.getString("target");
        if (target.equals("send")) {
            String message_text = json.getString("text");
            long id = messageDAO.insert(user.getId(), message_text, user.getUsername(), 0);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalDateTime now = LocalDateTime.now();
            response.put("result", "ok");
            response.put("message", generateMessage(new Message(
                    id, user.getUsername(), message_text, formatter.format(now))));
        } else if (target.equals("load")) {
            List<Message> messages = null;
            String location = json.getString("location");
            if (location.equals("/posts"))
                messages = messageDAO.selectPosts(user.getId(), json.getInt("offset"));
            else if (location.equals("/userPosts"))
                messages = messageDAO.getMessagesForChat(user.getId(), 0, json.getInt("offset"));
            else
                response.put("error", "Unknown location");
            if (messages != null) {
                JSONArray messagesArray = generateMessagesArray(messages);
                response.put("result", "ok");
                response.put("items", messagesArray);
            }
        } else
            response.put("error", "Unknown target");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response.toString());
    }
}
