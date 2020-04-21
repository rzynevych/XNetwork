package com.zman.znetwork.web;

import com.zman.znetwork.auth.UserHandler;
import com.zman.znetwork.models.Message;
import com.zman.znetwork.models.AppUser;
import com.zman.znetwork.repos.MessageRepository;
import com.zman.znetwork.utils.JsonGenerator;
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

import java.util.List;

@Controller
public class PostsController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/posts")
    public String posts() {
        return "posts";
    }

    @GetMapping("/userPosts")
    public String userPosts(Model model) {
        model.addAttribute("action", "/userPosts");
        model.addAttribute("from", "userPosts");
        return "userPosts";
    }

    @PostMapping("/posts")
    public ResponseEntity<String> send(@RequestBody String payload) {

        AppUser user = UserHandler.getAuthorizedUser().getAppUser();
        JSONObject json = new JSONObject(payload);
        JSONObject response = new JSONObject();
        JsonGenerator jsonGenerator =  new JsonGenerator();
        String target = json.getString("target");
        if (target.equals("send")) {
            String message_text = json.getString("text");
            Message message = new Message(user.getUserID(), 0, user.getUsername(), message_text);
            message = messageRepository.save(message);
            response.put("result", "ok");
            response.put("message", jsonGenerator.generateMessage(message));
        } else if (target.equals("load")) {
            List<Message> messages = null;
            String location = json.getString("location");
            if (location.equals("/posts"))
                messages = messageRepository.selectPosts(user.getUserID(), json.getInt("offset"));
            else if (location.equals("/userPosts"))
                messages = messageRepository.getMessagesForChat(user.getUserID(), 0, json.getInt("offset"));
            else
                response.put("error", "Unknown location");
            if (messages != null) {
                JSONArray messagesArray = jsonGenerator.generateMessagesArray(messages);
                response.put("result", "ok");
                response.put("items", messagesArray);
            }
        } else
            response.put("error", "Unknown target");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response.toString());
    }
}
