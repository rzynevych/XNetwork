package com.rz.xnetwork.repos;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.rz.xnetwork.dto.ChatListElem;
import com.rz.xnetwork.models.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query( value = "select case when count(c)> 0 then true else false end from Chat c where (c.userId1=?1 and c.userId2=?2) or (c.userId1=?2 and c.userId2=?1)")
    boolean existsChat(Long useruserID1, Long useruserID2);

    @Modifying
    @Query("update Chat c set c.lastUsed=CURRENT_TIMESTAMP where (c.userId1=?1 and c.userId2=?2) or (c.userId1=?2 and c.userId2=?1)")
    void updateChat(Long useruserID1, Long useruserID2);

    @Query(value = "SELECT new com.rz.xnetwork.dto.ChatListElem(c.chatId, u.userId, u.username, c.lastUsed) "
            + "FROM Chat c INNER JOIN AppUser u ON u.userId = c.userId1 OR u.userId = c.userId2 "
            + "WHERE (c.userId1 = ?1 OR c.userId2 = ?1) AND NOT u.userId = ?1 AND NOT c.userId1 = c.userId2 ")
    public List<ChatListElem> getChatList(Long userID, Pageable pageable);

}
