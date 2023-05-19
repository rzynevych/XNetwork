package com.rz.xnetwork.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ChatListElem {
    private Long chatId;
    private Long converserId;
    private String converserName;
    private Date lastUsed;


    public ChatListElem(Long chatId, Long converserId, String converserName, Date lastUsed) {
        this.chatId = chatId;
        this.converserId = converserId;
        this.converserName = converserName;
        this.lastUsed = lastUsed;
    }
}

