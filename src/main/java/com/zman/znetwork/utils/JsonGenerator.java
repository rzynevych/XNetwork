package com.zman.znetwork.utils;

import com.zman.znetwork.models.friends.Friend;
import com.zman.znetwork.models.messages.Message;
import com.zman.znetwork.models.users.AppUser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class JsonGenerator {

    public static JSONObject generateChat(AppUser user) {
        JSONObject jsonUser = new JSONObject();
        jsonUser.put("id", user.getId());
        jsonUser.put("username", user.getUsername());
        jsonUser.put("email", user.getEmail());
        return jsonUser;
    }

    public static JSONArray generateChatsArray(List<AppUser> userList) {
        JSONArray users = new JSONArray();
        for (AppUser user : userList)
            users.put(generateChat(user));
        return users;
    }

    public static JSONObject generateUser(Friend user) {
        JSONObject jsonUser = new JSONObject();
        jsonUser.put("id", user.getId());
        jsonUser.put("username", user.getUsername());
        jsonUser.put("email", user.getEmail());
        jsonUser.put("friend", user.isFriend());
        return jsonUser;
    }

    public static JSONArray generateUsersArray(List<Friend> userList) {
        JSONArray users = new JSONArray();
        for (Friend user : userList)
            users.put(generateUser(user));
        return users;
    }

    public static JSONObject generateMessage(Message message) {
        JSONObject jsonMessage = new JSONObject();
        jsonMessage.put("message_id", message.getId());
        jsonMessage.put("username", message.getUsername());
        jsonMessage.put("date", message.getDate());
        jsonMessage.put("text", message.getText());
        return jsonMessage;
    }

    public static JSONArray generateMessagesArray(List<Message> messageList) {
        JSONArray messages = new JSONArray();
        for (Message message : messageList)
            messages.put(generateMessage(message));
        return messages;
    }
}
