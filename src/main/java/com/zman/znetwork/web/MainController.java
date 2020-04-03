package com.zman.znetwork.web;

import com.zman.znetwork.auth.UserHandler;
import com.zman.znetwork.models.friends.Friend;
import com.zman.znetwork.models.friends.FriendDAO;
import com.zman.znetwork.models.messages.Message;
import com.zman.znetwork.models.messages.MessageDAO;
import com.zman.znetwork.models.users.AppUser;
import com.zman.znetwork.models.users.AppUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String greeting(Model model) {
        return "greeting";
    }

    @GetMapping("/posts")
    public String posts(Model model) {
        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        List<Message> messages = messageDAO.selectItems("receiver", 0, 0);
        model.addAttribute("messages", messages);
        model.addAttribute("action", "/posts");
        return "posts";
    }

    @GetMapping("/userPosts")
    public String userPosts(Model model) {
        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        List<Message> messages = messageDAO.selectItems("parent_id", appUser.getId(), 0);
        model.addAttribute("messages", messages);
        model.addAttribute("action", "/posts");
        return "userPosts";
    }

    @PostMapping("/posts")
    public String send(@RequestParam String message_text, Model model) {

        AppUser user = UserHandler.getAuthorizedUser().getAppUser();
        messageDAO.insert(user.getId(), message_text, user.getUsername(), 0);
        List<Message> messages = messageDAO.selectItems("receiver",0, 0);
        model.addAttribute("messages", messages);
        model.addAttribute("action", "/posts");
        return "posts";
    }

    @GetMapping("/friends")
    public String friends(Model model) {
        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        List<Friend> users = friendDAO.getFriendsForUser(appUser.getId());
        for (Friend friend : users)
            friend.setFriend(true);
        model.addAttribute("users", users);
        return "friends";
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
    public String chat(@RequestParam int id1, @RequestParam int id2, Model model) {
        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        AppUser target = appUserDAO.getById(id2);
        if (id1 != appUser.getId())
        {
            model.addAttribute("error_message", "You are not authorized to access this page");
            return "chat";
        }
        List<Message> messages = messageDAO.getMessagesForChat(id1, id2, 0);
        model.addAttribute("messages", messages);
        model.addAttribute("title", target.getUsername());
        model.addAttribute("action", "/chat");
        model.addAttribute("id1", id1);
        model.addAttribute("id2", id2);
        return "chat";
    }

    @PostMapping("chat")
    public String sendToChat(@RequestParam int id1, @RequestParam int id2, @RequestParam String message_text, Model model) {
        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        AppUser target = appUserDAO.getById(id2);
        if (id1 != appUser.getId())
        {
            model.addAttribute("error_message", "You are not authorized for this request");
            return "chat";
        }
        messageDAO.insert(id1, message_text, appUser.getUsername(), id2);
        List<Message> messages = messageDAO.getMessagesForChat(id1, id2, 0);
        model.addAttribute("messages", messages);
        model.addAttribute("title", target.getUsername());
        model.addAttribute("action", "/chat");
        model.addAttribute("id1", id1);
        model.addAttribute("id2", id2);
        return "chat";
    }
}