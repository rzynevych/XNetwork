package com.zman.znetwork.web;

import com.zman.znetwork.auth.UserHandler;
import com.zman.znetwork.models.friends.Friend;
import com.zman.znetwork.models.friends.FriendInsertException;
import com.zman.znetwork.models.users.AppUser;
import org.json.JSONObject;
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