package com.rz.xnetwork.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.rz.xnetwork.models.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query( value = "select case when count(c)> 0 then true else false end from Chat c where (c.userID1=?1 and c.userID2=?2) or (c.userID1=?2 and c.userID2=?1)")
    boolean existsChat(Long useruserID1, Long useruserID2);

    @Transactional
    @Modifying
    @Query("update Chat c set c.lastUsed=CURRENT_TIMESTAMP where (c.userID1=?1 and c.userID2=?2) or (c.userID1=?2 and c.userID2=?1)")
    void updateChat(Long useruserID1, Long useruserID2);

}
