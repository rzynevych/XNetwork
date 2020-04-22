package com.zman.znetwork.repos;

import com.zman.znetwork.models.Friend;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FriendRepository extends CrudRepository<Friend, Integer> {

    @Query(value = "select userid, email, reg_date, username, validate, password, last_login, friendid AS friend" +
            " from app_user inner join (select * from friendship where usrid=?1) t1 on t1.friendid=app_user.userid" +
            " ORDER BY t1.noteid DESC LIMIT ?2, 50", nativeQuery = true)
    public List<Friend> getFriendsForUser(int userID, int offset);

    @Query(value = "select userid, email, reg_date, username, validate, password, last_login, friendid AS friend from app_user left join " +
            "(select * from friendship where usrid=?2) t1 on t1.friendid=app_user.userid " +
            "WHERE (username LIKE ?1 OR email LIKE ?1) LIMIT ?3, 50", nativeQuery = true)
    public List<Friend> getUsersByQuery(String query, int user_id, int offset);
}
