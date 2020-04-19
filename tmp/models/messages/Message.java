package com.zman.znetwork.models.messages;

public class Message {

    private long id;
    private int parent_id;
    private int receiver;
    private String username;
    private String text;
    private String date;

    public Message () {

    }

    public Message(long id, int parent_id, int receiver, String username, String text, String date) {
        this.id = id;
        this.parent_id = parent_id;
        this.receiver = receiver;
        this.username = username;
        this.text = text;
        this.date = date;
    }

    public Message(long id, String username, String text, String date) {
        this.id = id;
        this.username = username;
        this.text = text;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
