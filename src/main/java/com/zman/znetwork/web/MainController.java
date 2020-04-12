package com.zman.znetwork.web;

import com.zman.znetwork.auth.UserHandler;
import com.zman.znetwork.models.friends.Friend;
import com.zman.znetwork.models.friends.FriendDAO;
import com.zman.znetwork.models.friends.FriendInsertException;
import com.zman.znetwork.models.messages.Message;
import com.zman.znetwork.models.messages.MessageDAO;
import com.zman.znetwork.models.users.AppUser;
import com.zman.znetwork.models.users.AppUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        List<Message> messages = messageDAO.selectItems("parent_id", appUser.getId(), 0);
        model.addAttribute("messages", messages);
        model.addAttribute("action", "/userPosts");
        model.addAttribute("from", "userPosts");
        return "userPosts";
    }

    @PostMapping("/userPosts")
    public ResponseEntity<String> send(@RequestParam String message_text, Model model) {

        AppUser user = UserHandler.getAuthorizedUser().getAppUser();
        messageDAO.insert(user.getId(), message_text, user.getUsername(), 0);

        String json = "{\"username\" : \"" + user.getUsername() + "\"," +
                "\"date\" : \"" + "17:05"  + "\"," +
                "\"text\" : \"" + message_text   + "\"," +
                "\"result\" : \"ok\"}";
        ResponseEntity entity = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
        return entity;
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
        String json = "{\"result\" : \"success\", \"action\" : \"" + action +"\"}";
        ResponseEntity<String> entity = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
        return entity;
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

    @GetMapping("chats")
    public String chats(Model model) {
        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        List<AppUser> users = messageDAO.getChatUsers(appUser.getId(), 0);
        model.addAttribute("users", users);
        model.addAttribute("appUser", appUser);
        return "chats";
    }

    @GetMapping("chat")
    public String chat(@RequestParam int id, Model model) {
        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        AppUser user = appUserDAO.getById(id);
        List<Message> messages = messageDAO.getMessagesForChat(appUser.getId(), id, 0);
        model.addAttribute("messages", messages)
        .addAttribute("title", user.getUsername())
        .addAttribute("action", "/chat")
        .addAttribute("id", id);
        return "chat";
    }

    @PostMapping("chat")
    public String sendToChat(@RequestParam int id, @RequestParam String message_text, Model model) {
        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        AppUser target = appUserDAO.getById(id);
        messageDAO.insert(appUser.getId(), message_text, appUser.getUsername(), id);
        List<Message> messages = messageDAO.getMessagesForChat(appUser.getId(), id, 0);
        model
                .addAttribute("messages", messages)
                .addAttribute("title", target.getUsername())
                .addAttribute("action", "/chat")
                .addAttribute("id", id);
        return "chat";
    }

    @GetMapping("/account")
    public String account(@RequestParam int id, Model model) {

        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        AppUser user;
        if (id == 0)
            user = appUser;
        else
            user = appUserDAO.getById(id);
        if (user == null) {
            model.addAttribute("message", "User not found");
            return "user";
        }
        model.addAttribute("user", user);
        if (user.getId() == appUser.getId())
            model.addAttribute("authority", true);
        return "user";
    }
}