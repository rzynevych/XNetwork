package com.rz.xnetwork.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rz.xnetwork.dto.UserListElem;
import com.rz.xnetwork.models.AppUser;

import java.util.List;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    public AppUser findByUserId(Long id);
    public AppUser findByUsername(String username);
    public AppUser findByEmail(String email);
    
    @Query(value = "select user_id, email, reg_date, username, validate, password, last_login, friendid AS friend "
            + "from app_user inner join (select * from friendship where usrid=?1) t1 on t1.friendid=app_user.userid "
            + "ORDER BY t1.noteid DESC LIMIT ?2, ?3", nativeQuery = true)
    public List<UserListElem> getSubscribersForUser(Long userId, int offset, int limit);

    @Query(value = "select user_id, email, reg_date, username, validate, password, last_login, friendid AS friend "
            + "from app_user inner join (select * from friendship where usrid=?1) t1 on t1.friendid=app_user.userid "
            + "ORDER BY t1.noteid DESC LIMIT ?2, ?3", nativeQuery = true)
    public List<UserListElem> getSubscriptionsForUser(Long userId, int offset, int limit);

    @Query(name = "getUsersByQuery", nativeQuery = true)
    public List<UserListElem> getUsersByQuery(String query, Long user_id, int offset, int limit);

}
