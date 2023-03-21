package com.rz.xnetwork.repos;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.rz.xnetwork.models.Friendship;

public interface FriendshipRepository extends CrudRepository<Friendship, Long> {

    @Query( value = "select case when count(f)> 0 then true else false end from Friendship f where (f.usrID=?1 and f.friendID=?2)")
    boolean existsFriendship(int id1, int id2);

    @Transactional
    @Modifying
    @Query(value = "delete from Friendship f where (f.usrID=?1 and f.friendID=?2)")
    void deleteByUsrIDAndFriendID(int usrID, int friendID);
}
