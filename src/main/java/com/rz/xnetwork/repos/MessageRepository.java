package com.rz.xnetwork.repos;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.rz.xnetwork.models.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query(value = "SELECT messageid,senderid,receiver,sender_name,text,date " +
            "FROM message m INNER JOIN subscription ON s.subscriber_id = m.senderid WHERE s.userid=?1 " +
            "AND receiver = 0 ORDER BY messageid DESC LIMIT ?2, 50", nativeQuery = true)
    List<Message> selectPosts(long userID, int offset);

    @Query(value = "select m from Message m where m.senderID = ?1 AND m.receiver = 0")
    List<Message> selectOwnPosts(Long userID, Pageable pageable);

    @Query(value = "select m from Message m where (m.senderID=?1 AND m.receiver=?2) or (m.senderID=?2 AND m.receiver=?1)")
    List<Message> getMessagesForChat(Long userID1, Long userID2, Pageable pageable);

    @Query(value = "select m from Message m where (m.senderID=?1 AND m.receiver=?2) and m.messageID > ?3")
    List<Message> getMessagesForUpdate(Long userID1, Long userID2, Long lastMessageID);

}
