package com.zman.znetwork.models.users;

public class AppUser {
    private int id;
    private String email;
    private String regDate;
    private String username;
    private byte validate;
    private String password;
    private String last_login;

    public AppUser(int id, String email, String regDate, String username, byte validate, String password, String last_login) {
        this.id = id;
        this.email = email;
        this.regDate = regDate;
        this.username = username;
        this.validate = validate;
        this.password = password;
        this.last_login = last_login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte getValidate() {
        return validate;
    }

    public void setValidate(byte validate) {
        this.validate = validate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }
}
