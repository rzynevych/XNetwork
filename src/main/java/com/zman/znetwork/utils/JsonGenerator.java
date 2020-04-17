package com.zman.znetwork.utils;

import com.zman.znetwork.models.messages.Message;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class JsonGenerator {

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
