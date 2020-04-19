package com.zman.znetwork.repos;

import com.zman.znetwork.models.users.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {

    public AppUser findByUserID(int id);
    public AppUser findByUsername(String username);
    public AppUser getByEmail(String email);

}
