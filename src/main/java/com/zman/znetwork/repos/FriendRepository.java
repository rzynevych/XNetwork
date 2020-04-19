package com.zman.znetwork.repos;

import com.zman.znetwork.models.friends.Friend;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FriendRepository extends CrudRepository<Friend, Integer> {

    @Query(value = "select user_id, email, reg_date, username, last_login, friend_id" +
            " from users inner join (select * from friends where usr_id=?1) t1 on t1.friend_id=users.user_id" +
            " ORDER BY t1.note_id DESC LIMIT ?2, 50", nativeQuery = true)
    public List<Friend> getFriendsForUser(int userID, int offset);

    @Query(value = "select user_id, email, reg_date, username, last_login, friend_id from users left join " +
            "(select * from friends where usr_id=?2) t1 on t1.friend_id=users.user_id WHERE NOT user_id=?2 " +
            "AND (username LIKE ?1 OR email LIKE ?1) LIMIT ?3, 50", nativeQuery = true)
    public List<Friend> getUsersByQuery(String query, int user_id, int offset);
}
