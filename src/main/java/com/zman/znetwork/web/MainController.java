package com.zman.znetwork.web;

import com.zman.znetwork.auth.UserHandler;
import com.zman.znetwork.models.friends.Friend;
import com.zman.znetwork.models.friends.FriendDAO;
import com.zman.znetwork.models.friends.FriendInsertException;
import com.zman.znetwork.models.messages.Message;
import com.zman.znetwork.models.messages.MessageDAO;
import com.zman.znetwork.models.users.AppUser;
import com.zman.znetwork.models.users.AppUserDAO;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
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
        Collections.reverse(messages);
        model.addAttribute("messages", messages);
        model.addAttribute("action", "/userPosts");
        model.addAttribute("from", "userPosts");
        return "userPosts";
    }

    @PostMapping("/userPosts")
    public ResponseEntity<String> send(@RequestParam String message_text) {

        AppUser user = UserHandler.getAuthorizedUser().getAppUser();
        messageDAO.insert(user.getId(), message_text, user.getUsername(), 0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();
        JSONObject object = new JSONObject();
        object.append("result", "ok");
        object.append("username", user.getUsername());
        object.append("date", formatter.format(now));
        object.append("text", message_text);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(object.toString());
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