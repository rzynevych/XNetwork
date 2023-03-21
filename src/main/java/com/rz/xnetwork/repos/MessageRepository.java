package com.rz.xnetwork.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rz.xnetwork.models.Message;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Integer> {

    @Query(value = "SELECT messageid,parentid,receiver,username,text,date " +
            "FROM message INNER JOIN friendship ON friendid=parentid WHERE usrid=?1 " +
            "AND receiver=0 ORDER BY messageid DESC LIMIT ?2, 50", nativeQuery = true)
    List<Message> selectPosts(int id, int offset);

    @Query(value = "SELECT messageid,parentid,receiver,username,text,date " +
            "FROM message WHERE (parentid=?1 AND receiver=?2) OR (parentid=?2 AND receiver=?1) " +
            "ORDER BY messageid DESC LIMIT ?3, 50", nativeQuery = true)
    List<Message> getMessagesForChat(int id1, int id2, int offset);

    @Query(value = "SELECT messageid,parentid,receiver,username,text,date FROM message " +
            "WHERE (parentid=?1 AND receiver=?2) AND messageid > ?3 " +
            "ORDER BY messageid ASC LIMIT 50", nativeQuery = true)
    List<Message> getMessagesForUpdate(int id1, int id2, long last);

}
