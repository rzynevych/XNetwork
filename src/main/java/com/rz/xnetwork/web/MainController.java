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
import com.rz.xnetwork.models.Friend;
import com.rz.xnetwork.models.Friendship;
import com.rz.xnetwork.repos.FriendRepository;
import com.rz.xnetwork.repos.FriendshipRepository;
import com.rz.xnetwork.utils.JsonGenerator;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private FriendshipRepository friendshipRepository;

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {

        if (UserHandler.isUserAuthorized())
        {
            AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
            model.addAttribute("account", "/account?id=" + appUser.getUserID());
        }
        else
            model.addAttribute("account", "/account?id=0");
        return "welcome";
    }

    @GetMapping("/friends")
    public String friends(Model model) {
        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        List<Friend> users = friendRepository.getFriendsForUser(appUser.getUserID(), 0);
        model.addAttribute("users", users);
        model.addAttribute("appUser", appUser);
        model.addAttribute("from", "friends");
        return "friends";
    }

    private synchronized void insertFriend(int id1, int id2) {

        if (!friendshipRepository.existsFriendship(id1,id2))
            friendshipRepository.save(new Friendship(id1, id2));
    }

    @PostMapping("/friends")
    public ResponseEntity<String> friendsHandler(@RequestBody String json) {
        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        JSONObject jsonObject = new JSONObject(json);
        String target = jsonObject.getString("target");
        JSONObject response = new JSONObject();
        JsonGenerator jsonGenerator = new JsonGenerator();
        List<Friend> users = null;
        if (target.equals("friend-handling")) {
            String action = jsonObject.getString("action");
            if (action.equals("Add"))
                insertFriend(appUser.getUserID(), jsonObject.getInt("id"));
            else if (action.equals("Remove"))
                friendshipRepository.deleteByUsrIDAndFriendID(appUser.getUserID(), jsonObject.getInt("id"));
            response.put("result", "ok");
            response.put("action", action);
        } else if (target.equals("load")) {
            int offset = jsonObject.getInt("offset");
            String location = jsonObject.getString("location");
            if (location.equals("/friends"))
                users = friendRepository.getFriendsForUser(appUser.getUserID(), offset);
            else if (location.equals("/searchUsers"))
                users = friendRepository.getUsersByQuery(jsonObject.getString("query"), appUser.getUserID(), offset);
            JSONArray jsonUsers = jsonGenerator.generateUsersArray(users);
            response.put("result", "ok");
            response.put("items", jsonUsers);
        } else if (target.equals("search")) {
            users = friendRepository.getUsersByQuery(jsonObject.getString("query") + "%", appUser.getUserID(), 0);
            JSONArray jsonUsers = jsonGenerator.generateUsersArray(users);
            response.put("result", "ok");
            response.put("items", jsonUsers);
        } else
            response.put("error", "Unknown target");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response.toString());
    }

    @GetMapping("/searchUsers")
    public String searchUsers(Model model) {

        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        List<Friend> users = friendRepository.getUsersByQuery("%", appUser.getUserID(), 0);
        model.addAttribute("users", users);
        model.addAttribute("appUser", appUser);
        model.addAttribute("from", "searchUsers");
        return "searchUsers";
    }

    @PostMapping("/searchUsers")
    public String searchUsersHandler(@RequestParam String query, Model model) {

        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        List<Friend> users = friendRepository.getUsersByQuery(query + "%", appUser.getUserID(), 0);
        model.addAttribute("users", users);
        model.addAttribute("appUser", appUser);
        model.addAttribute("from", "searchUsers");
        return "searchUsers";
    }
}