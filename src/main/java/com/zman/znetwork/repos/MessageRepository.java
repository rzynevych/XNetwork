package com.zman.znetwork.repos;

import com.zman.znetwork.models.messages.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Integer> {

    @Query(value = "SELECT message_id,parent_id,receiver,username,text,date " +
            "FROM messages INNER JOIN friends ON friend_id=parent_id WHERE usr_id=?1 " +
            "AND receiver=0 ORDER BY message_id DESC LIMIT ?2, 50", nativeQuery = true)
    public List<Message> selectPosts(int id, int offset);

    @Query(value = "SELECT message_id,parent_id,receiver,username,text,date " +
            "FROM messages WHERE (parent_id=?1 AND receiver=?2) OR (parent_id=?2 AND receiver=?1) " +
            "ORDER BY message_id DESC LIMIT ?3, 50", nativeQuery = true)
    public List<Message> getMessagesForChat(int id1, int id2, int offset);

    @Query(value = "SELECT message_id,parent_id,receiver,username,text,date FROM messages " +
            "WHERE (parent_id=?1 AND receiver=?2) AND message_id > 3 " +
            "ORDER BY message_id ASC LIMIT 50", nativeQuery = true)
    public List<Message> getMessagesForUpdate(int id1, int id2, long last);

}
