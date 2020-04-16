package com.zman.znetwork.web;

import com.zman.znetwork.auth.UserHandler;
import com.zman.znetwork.models.friends.Friend;
import com.zman.znetwork.models.friends.FriendDAO;
import com.zman.znetwork.models.friends.FriendInsertException;
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
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private AppUserDAO appUserDAO;

    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private FriendDAO friendDAO;

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {

        if (UserHandler.isUserAuthorized())
        {
            AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
            model.addAttribute("account", "/account?id=" + appUser.getId());
        }
        else
            model.addAttribute("account", "/account?id=0");
        return "welcome";
    }

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
            response.put("message", MessagesController.generateMessage(new Message(
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
                JSONArray messagesArray = MessagesController.generateMessagesArray(messages);
                response.put("result", "ok");
                response.put("messages", messagesArray);
            }
        } else
            response.put("error", "Unknown target");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response.toString());
    }

    @GetMapping("/friends")
    public String friends(Model model) {
        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        List<Friend> users = friendDAO.getFriendsForUser(appUser.getId(), 0);
        model.addAttribute("users", users);
        model.addAttribute("appUser", appUser);
        model.addAttribute("from", "friends");
        return "friends";
    }

    @PostMapping("/friends")
    public ResponseEntity<String> friendsHandler(@RequestParam int id, @RequestParam String action, @RequestParam String from, Model model) {
        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        if (action.equals("Add"))
            try {
                friendDAO.insertFriend(appUser.getId(), id);
            } catch (FriendInsertException e) {
                e.printStackTrace();
            }
        else if (action.equals("Remove"))
            friendDAO.removeFriend(appUser.getId(), id);
        JSONObject object = new JSONObject();
        object.append("result", "ok");
        object.append("action", action);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(object.toString());
    }

    @GetMapping("/searchUsers")
    public String searchUsers(Model model) {

        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        List<Friend> users = friendDAO.getUsersByQuery("", appUser.getId(), 0);
        model.addAttribute("users", users);
        model.addAttribute("appUser", appUser);
        model.addAttribute("from", "searchUsers");
        return "searchUsers";
    }

    @PostMapping("/searchUsers")
    public String searchUsersHandler(@RequestParam String query, Model model) {

        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        List<Friend> users = friendDAO.getUsersByQuery(query, appUser.getId(), 0);
        model.addAttribute("users", users);
        model.addAttribute("appUser", appUser);
        model.addAttribute("from", "searchUsers");
        return "searchUsers";
    }
}