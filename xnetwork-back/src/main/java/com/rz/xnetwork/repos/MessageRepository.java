package com.rz.xnetwork.repos;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.rz.xnetwork.models.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query(value = "select m from Message m INNER JOIN Subscription s "
        + "ON m.senderId = s.userId WHERE s.subscriberId=?1 AND m.receiver = 0")
    List<Message> selectPosts(long userID, Pageable pageable);

    @Query(value = "select m from Message m where m.senderId = ?1 AND m.receiver = 0")
    List<Message> selectOwnPosts(Long userID, Pageable pageable);

    @Query(value = "select m from Message m where (m.senderId=?1 AND m.receiver=?2) or (m.senderId=?2 AND m.receiver=?1)")
    List<Message> getMessagesForChat(Long userID1, Long userID2, Pageable pageable);

    @Query(value = "select m from Message m where (m.senderId=?1 AND m.receiver=?2) and m.messageId > ?3")
    List<Message> getMessagesForUpdate(Long userID1, Long userID2, Long lastMessageID, Sort sort);

}
