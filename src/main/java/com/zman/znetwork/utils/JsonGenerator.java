package com.zman.znetwork.utils;

import com.zman.znetwork.models.Friend;
import com.zman.znetwork.models.Message;
import com.zman.znetwork.models.AppUser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class JsonGenerator {

    Date now;

    public JsonGenerator() {
        now = new Date();
    }

    public JSONObject generateChat(AppUser user) {
        JSONObject jsonUser = new JSONObject();
        jsonUser.put("id", user.getUserID());
        jsonUser.put("username", user.getUsername());
        jsonUser.put("email", user.getEmail());
        return jsonUser;
    }

    public JSONArray generateChatsArray(List<AppUser> userList) {
        JSONArray users = new JSONArray();
        for (AppUser user : userList)
            users.put(generateChat(user));
        return users;
    }

    public JSONObject generateUser(Friend user) {
        JSONObject jsonUser = new JSONObject();
        jsonUser.put("id", user.getUserID());
        jsonUser.put("username", user.getUsername());
        jsonUser.put("email", user.getEmail());
        jsonUser.put("friend", user.isFriend());
        return jsonUser;
    }

    public JSONArray generateUsersArray(List<Friend> userList) {
        JSONArray users = new JSONArray();
        for (Friend user : userList)
            users.put(generateUser(user));
        return users;
    }

    public JSONObject generateMessage(Message message) {
        JSONObject jsonMessage = new JSONObject();
        jsonMessage.put("message_id", message.getMessageID());
        jsonMessage.put("username", message.getUsername());
        jsonMessage.put("date", calculateTime(message.getDate()));
        jsonMessage.put("text", message.getText());
        return jsonMessage;
    }

    public JSONArray generateMessagesArray(List<Message> messageList) {
        JSONArray messages = new JSONArray();
        for (Message message : messageList)
            messages.put(generateMessage(message));
        return messages;
    }

    public String calculateTime(Date date) {

        long millis;
        long diff;
        StringBuilder time = new StringBuilder();

        millis = (now.getTime() - date.getTime());
        if ((TimeUnit.MINUTES.convert(millis, TimeUnit.MILLISECONDS)) < 1) {
            time.append("just now");
            return time.toString();
        } else if ((diff = TimeUnit.MINUTES.convert(millis, TimeUnit.MILLISECONDS)) < 60) {
            time.append(diff).append(" minute");
        } else if ((diff = TimeUnit.HOURS.convert(millis, TimeUnit.MILLISECONDS)) < 24) {
            time.append(diff).append(" hour");
        } else if ((diff = TimeUnit.DAYS.convert(millis, TimeUnit.MILLISECONDS)) < 7) {
            time.append(diff).append(" day");
        } else if ((diff = TimeUnit.DAYS.convert(millis, TimeUnit.MILLISECONDS) / 7) < 4) {
            time.append(diff).append(" week");
        } else if ((diff = TimeUnit.DAYS.convert(millis, TimeUnit.MILLISECONDS) / 30) < 12) {
            time.append(diff).append(" month");
        } else {
            diff = TimeUnit.DAYS.convert(millis, TimeUnit.MILLISECONDS) / 365;
            time.append(diff).append(" year");
        }
        if (diff % 10 != 1 || diff % 100 == 11)
            time.append("s");
        time.append(" ago");
        return time.toString();
    }
}
