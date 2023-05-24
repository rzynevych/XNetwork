package com.rz.xnetwork.repos;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rz.xnetwork.dto.UserListElem;
import com.rz.xnetwork.models.AppUser;

import java.util.List;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    public AppUser findByUserId(Long id);
    public AppUser findByUsername(String username);
    public AppUser findByEmail(String email);
    
    @Query(value = "SELECT new com.rz.xnetwork.dto.UserListElem( "
            + "u.userId, u.email, u.username, u.lastLogin, u.regDate, "
            + "CASE WHEN s1 IS NOT NULL THEN TRUE ELSE FALSE END, "
            + "CASE WHEN s2 IS NOT NULL THEN TRUE ELSE FALSE END) "
            + "from AppUser u "
            + "INNER JOIN Subscription s1 ON s1.subscriberId=u.userId AND s1.userId=?2 "
            + "LEFT JOIN Subscription s2 ON s2.userId=u.userId AND s2.subscriberId=?2 ")
    public List<UserListElem> getSubscribersForUser(Long userId, Pageable pageable);

    @Query(value = "SELECT new com.rz.xnetwork.dto.UserListElem( "
            + "u.userId, u.email, u.username, u.lastLogin, u.regDate, "
            + "CASE WHEN s1 IS NOT NULL THEN TRUE ELSE FALSE END, "
            + "CASE WHEN s2 IS NOT NULL THEN TRUE ELSE FALSE END) "
            + "from AppUser u "
            + "LEFT JOIN Subscription s1 ON s1.subscriberId=u.userId AND s1.userId=?2 "
            + "INNER JOIN Subscription s2 ON s2.userId=u.userId AND s2.subscriberId=?2 ")
    public List<UserListElem> getSubscriptionsForUser(Long userId, Pageable pageable);

    @Query(value = "SELECT new com.rz.xnetwork.dto.UserListElem( "
            + "u.userId, u.email, u.username, u.lastLogin, u.regDate, "
            + "CASE WHEN s1 IS NOT NULL THEN TRUE ELSE FALSE END, "
            + "CASE WHEN s2 IS NOT NULL THEN TRUE ELSE FALSE END) "
            + "from AppUser u "
            + "LEFT JOIN Subscription s1 ON s1.subscriberId=u.userId AND s1.userId=?2 "
            + "LEFT JOIN Subscription s2 ON s2.userId=u.userId AND s2.subscriberId=?2 "
            + "WHERE u.username LIKE CONCAT(?1, '%') OR u.email LIKE CONCAT(?1, '%')")
    public List<UserListElem> getUsersByQuery(String query, Long user_id, Pageable pageable);

}
