package com.zman.znetwork.web;

import com.zman.znetwork.auth.UserHandler;
import com.zman.znetwork.models.friends.Friend;
import com.zman.znetwork.models.friends.FriendInsertException;
import com.zman.znetwork.models.users.AppUser;
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
    public ResponseEntity<String> friendsHandler(@RequestBody String json) {
        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        JSONObject jsonObject = new JSONObject(json);
        String target = jsonObject.getString("target");
        JSONObject response = new JSONObject();
        List<Friend> users = null;
        if (target.equals("friend-handling")) {
            String action = jsonObject.getString("action");
            if (action.equals("Add"))
                try {
                    friendDAO.insertFriend(appUser.getId(), jsonObject.getInt("id"));
                } catch (FriendInsertException e) {
                    e.printStackTrace();
                }
            else if (action.equals("Remove"))
                friendDAO.removeFriend(appUser.getId(), jsonObject.getInt("id"));
            response.put("result", "ok");
            response.put("action", action);
        } else if (target.equals("load")) {
            int offset = jsonObject.getInt("offset");
            String location = jsonObject.getString("location");
            if (location.equals("/friends"))
                users = friendDAO.getFriendsForUser(appUser.getId(), offset);
            else if (location.equals("/searchUsers"))
                users = friendDAO.getUsersByQuery(jsonObject.getString("query"), appUser.getId(), offset);
            JSONArray jsonUsers = JsonGenerator.generateUsersArray(users);
            response.put("result", "ok");
            response.put("items", jsonUsers);
        } else if (target.equals("search")) {
            users = friendDAO.getUsersByQuery(jsonObject.getString("query"), appUser.getId(), 0);
            JSONArray jsonUsers = JsonGenerator.generateUsersArray(users);
            response.put("result", "ok");
            response.put("users", jsonUsers);
        } else
            response.put("error", "Unknown target");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response.toString());
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