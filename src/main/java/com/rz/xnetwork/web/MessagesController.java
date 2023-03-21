package com.rz.xnetwork.web;

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

import com.rz.xnetwork.auth.UserHandler;
import com.rz.xnetwork.models.AppUser;
import com.rz.xnetwork.models.Chat;
import com.rz.xnetwork.models.Message;
import com.rz.xnetwork.repos.AppUserRepository;
import com.rz.xnetwork.repos.ChatRepository;
import com.rz.xnetwork.repos.MessageRepository;
import com.rz.xnetwork.utils.JsonGenerator;

import java.util.List;

@Controller
public class MessagesController {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatRepository chatRepository;

    @GetMapping("chats")
    public String chats(Model model) {
        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        List<AppUser> users = appUserRepository.getChatUsers(appUser.getUserID(), 0);
        model.addAttribute("users", users);
        model.addAttribute("appUser", appUser);
        return "chats";
    }

    @PostMapping("chats")
    public ResponseEntity<String> loadChats(@RequestBody String json) {
        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        JSONObject jsonObject = new JSONObject(json);
        List<AppUser> users = appUserRepository.getChatUsers(appUser.getUserID(), jsonObject.getInt("offset"));
        JSONObject response = new JSONObject();
        JSONArray chatsArray = new JsonGenerator().generateChatsArray(users);
        response.put("result", "ok");
        response.put("items", chatsArray);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response.toString());
    }

    @GetMapping("chat")
    public String chat(@RequestParam int id, Model model) {
        AppUser user = appUserRepository.findByUserID(id);
        model
                .addAttribute("title", user.getUsername())
                .addAttribute("action", "/chat")
                .addAttribute("id", id);
        return "chat";
    }

    @PostMapping("chat")
    public ResponseEntity<String> chatController(@RequestBody String payload) {
        AppUser user = UserHandler.getAuthorizedUser().getAppUser();
        JSONObject json = new JSONObject(payload);
        ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON);
        JSONObject response = new JSONObject();
        JsonGenerator jsonGenerator = new JsonGenerator();
        List<Message> messages;
        String target = json.getString("target");
        if (target.equals("update")) {
            String str = json.getString("last").replaceAll("\\D", "");
            long last = Long.parseLong(str);
            messages = messageRepository.getMessagesForUpdate(user.getUserID(),
                    json.getInt("user_id"), last);
            JSONArray messagesArray = jsonGenerator.generateMessagesArray(messages);
            response.put("result", "ok");
            response.put("items", messagesArray);
            return bodyBuilder.body(response.toString());
        } else if (target.equals("send")) {
            String text = json.getString("text");
            Message message = new Message(user.getUserID(), json.getInt("user_id"),user.getUsername(), text);
            message = messageRepository.save(message);
            if (chatRepository.existsChat(user.getUserID(), json.getInt("user_id")))
                chatRepository.updateChat(user.getUserID(), json.getInt("user_id"));
            else
                chatRepository.save(new Chat(user.getUserID(), json.getInt("user_id"), message.getDate()));
            response.put("result", "ok");
            response.put("message_id", message.getMessageID());
            response.put("message", jsonGenerator.generateMessage(message));
            return bodyBuilder.body(response.toString());
        } else if (target.equals("load")) {
            messages = messageRepository.getMessagesForChat(user.getUserID(), json.getInt("user_id"), json.getInt("offset"));
            JSONArray messagesArray = jsonGenerator.generateMessagesArray(messages);
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

