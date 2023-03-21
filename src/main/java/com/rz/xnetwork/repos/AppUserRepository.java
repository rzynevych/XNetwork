package com.rz.xnetwork.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rz.xnetwork.models.AppUser;

import java.util.List;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {

    public AppUser findByUserID(int id);
    public AppUser findByUsername(String username);
    public AppUser findByEmail(String email);

    @Query(value = "SELECT dtype, userid, email, reg_date, username, validate, password, last_login " +
            "FROM app_user INNER JOIN chat ON (userid=id1 OR userid=id2) " +
            "WHERE (id1=?1 OR id2=?1) AND NOT (userid=?1 AND NOT id1=id2) ORDER BY last_used DESC LIMIT ?2, 50", nativeQuery = true)
    public List<AppUser> getChatUsers(int id, int offset);

}
