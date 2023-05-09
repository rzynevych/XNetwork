package com.rz.xnetwork.models;

import javax.persistence.*;

import com.rz.xnetwork.dto.AppUserDto;
import com.rz.xnetwork.dto.UserListElem;

import java.util.Date;

@Entity
@NamedNativeQueries({
    @NamedNativeQuery(
        name = "getUsersByQuery",
        query = "select u.userid as userid, email, username, last_login , reg_date, "
            + "case when not t1.subscriberid = NULL then 1 else 0 end as subscribed, "
            + "case when not t1.subscriberid = NULL then 1 else 0 end as in_subscriptions "
            + "from app_user u left join (select * from subscription where userid=?2) t1 "
            + "on t1.subscriberid=u.userid "
            + "WHERE (username LIKE ?1 OR email LIKE ?1 ) LIMIT ?3, 50",
        resultSetMapping = "UserListElem"
    ),
    @NamedNativeQuery(
        name = "getChatUsers",
        query = "select u.userid as userid, email, username, last_login , reg_date "
            + "from app_user u INNER JOIN chat c ON (u.userid=c.userid1 OR u.userid=c.userid2) "
            + "WHERE (c.userid1=?1 OR c.userid2=?1) ORDER BY c.last_used DESC LIMIT ?2, 50",
            resultSetMapping = "AppUserDto"
    )
})
@SqlResultSetMappings({
    @SqlResultSetMapping(
        name = "UserListElem",
        classes = @ConstructorResult(
            targetClass = UserListElem.class,
            columns = {
                @ColumnResult(name = "userID", type = Long.class),
                @ColumnResult(name = "email", type = String.class),
                @ColumnResult(name = "username", type = String.class),
                @ColumnResult(name = "last_login", type = Date.class),
                @ColumnResult(name = "reg_date", type = Date.class),
                @ColumnResult(name = "subscribed", type = Boolean.class),
                @ColumnResult(name = "in_subscriptions", type = Boolean.class)
            }
        )
    ),
    @SqlResultSetMapping(
        name = "AppUserDto",
        classes = @ConstructorResult(
            targetClass = AppUserDto.class,
            columns = {
                @ColumnResult(name = "userID", type = Long.class),
                @ColumnResult(name = "email", type = String.class),
                @ColumnResult(name = "username", type = String.class),
                @ColumnResult(name = "last_login", type = Date.class),
                @ColumnResult(name = "reg_date", type = Date.class)
            }
        )
    )
})
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userID;
    private String email;
    private String username;
    private String password;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
