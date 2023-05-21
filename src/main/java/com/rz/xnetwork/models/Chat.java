package com.rz.xnetwork.models;

import javax.persistence.*;

import com.rz.xnetwork.dto.ChatListElem;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@NamedNativeQuery(
    name = "getChatList",
    query = "SELECT chat_id, user_id as converser_id, username as converser_name, last_used "
        + "FROM chat c INNER JOIN app_user u ON (u.user_id=c.user_id2) WHERE (c.user_id1=?1) " 
        + "UNION ALL "
        + "SELECT chat_id, user_id as converser_id, username as converser_name, last_used "
        + "FROM chat c INNER JOIN app_user u ON (u.user_id=c.user_id1) WHERE (c.user_id2=?1) AND NOT (c.user_id1 = c.user_id2) "
        + "ORDER BY last_used DESC LIMIT ?2, ?3",
        resultSetMapping = "ChatListElem"
)
@SqlResultSetMapping(
    name = "ChatListElem",
    classes = @ConstructorResult(
        targetClass = ChatListElem.class,
        columns = {
            @ColumnResult(name = "chat_id", type = Long.class),
            @ColumnResult(name = "converser_id", type = Long.class),
            @ColumnResult(name = "converser_name", type = String.class),
            @ColumnResult(name = "last_used", type = Date.class)
        }
    )
)
@Getter @Setter
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long chatId;
    private Long userId1;
    private Long userId2;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUsed;

    public Chat(){}

    public Chat(Long id1, Long id2, Date date) {
        this.userId1 = id1;
        this.userId2 = id2;
        this.lastUsed = date;
    }
}
