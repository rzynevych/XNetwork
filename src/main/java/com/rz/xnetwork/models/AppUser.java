package com.rz.xnetwork.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer userID;
    private String email;
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;
    private String username;
    private Byte validate;
    private String password;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Byte getValidate() {
        return validate;
    }

    public void setValidate(Byte validate) {
        this.validate = validate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
