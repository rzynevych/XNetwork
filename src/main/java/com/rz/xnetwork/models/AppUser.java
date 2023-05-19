package com.rz.xnetwork.models;

import javax.persistence.*;

import com.rz.xnetwork.dto.UserListElem;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@NamedNativeQueries({
    @NamedNativeQuery(
        name = "getUsersByQuery",
        query = "SELECT u.user_id as user_id, email, username, last_login , reg_date, "
        + "CASE WHEN NOT t1.subscriber_id = NULL THEN 1 ELSE 0 END AS subscribed, "
        + "CASE WHEN NOT t1.subscriber_id = NULL THEN 1 ELSE 0 END AS in_subscriptions "
        + "FROM app_user u LEFT JOIN (SELECT * FROM subscription WHERE user_id=?2) t1 "
        + "ON t1.subscriber_id=u.user_id "
        + "WHERE (username LIKE ?1 OR email LIKE ?1 ) LIMIT ?3, 50",
        resultSetMapping = "UserListElem"
    )
})
@SqlResultSetMappings({
    @SqlResultSetMapping(
        name = "UserListElem",
        classes = @ConstructorResult(
            targetClass = UserListElem.class,
            columns = {
                @ColumnResult(name = "user_id", type = Long.class),
                @ColumnResult(name = "email", type = String.class),
                @ColumnResult(name = "username", type = String.class),
                @ColumnResult(name = "last_login", type = Date.class),
                @ColumnResult(name = "reg_date", type = Date.class),
                @ColumnResult(name = "subscribed", type = Boolean.class),
                @ColumnResult(name = "in_subscriptions", type = Boolean.class)
            }
        )
    )
})
@Getter @Setter
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String email;
    private String username;
    private String password;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;
}
