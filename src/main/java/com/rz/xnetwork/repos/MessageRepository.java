package com.rz.xnetwork.repos;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.rz.xnetwork.models.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query(value = "SELECT message_id,sender_id,receiver,sender_name,text,date " +
            "FROM message m INNER JOIN subscription ON s.subscriber_id = m.sender_id WHERE s.user_id=?1 " +
            "AND receiver = 0 ORDER BY message_id DESC LIMIT ?2, 50", nativeQuery = true)
    List<Message> selectPosts(long userID, int offset);

    @Query(value = "select m from Message m where m.senderId = ?1 AND m.receiver = 0")
    List<Message> selectOwnPosts(Long userID, Pageable pageable);

    @Query(value = "select m from Message m where (m.senderId=?1 AND m.receiver=?2) or (m.senderId=?2 AND m.receiver=?1)")
    List<Message> getMessagesForChat(Long userID1, Long userID2, Pageable pageable);

    @Query(value = "select m from Message m where (m.senderId=?1 AND m.receiver=?2) and m.messageId > ?3")
    List<Message> getMessagesForUpdate(Long userID1, Long userID2, Long lastMessageID, Sort sort);

}
