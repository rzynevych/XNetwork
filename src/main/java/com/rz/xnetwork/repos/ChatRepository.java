package com.rz.xnetwork.repos;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.rz.xnetwork.models.Chat;

public interface ChatRepository extends CrudRepository<Chat, Long> {

    @Query( value = "select case when count(c)> 0 then true else false end from Chat c where (c.id1=?1 and c.id2=?2) or (c.id1=?2 and c.id2=?1)")
    boolean existsChat(int id1, int id2);

    @Transactional
    @Modifying
    @Query("update Chat c set c.lastUsed=CURRENT_TIMESTAMP where (c.id1=?1 and c.id2=?2) or (c.id1=?2 and c.id2=?1)")
    void updateChat(int id1, int id2);

}
