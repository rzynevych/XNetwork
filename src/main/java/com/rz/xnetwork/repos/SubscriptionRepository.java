package com.rz.xnetwork.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.rz.xnetwork.models.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    @Query( value = "select case when count(f)> 0 then true else false end from Subscription s where (s.subscriberID=?1 and s.userID=?2)")
    boolean existsSubscription(Long userID1, Long userID2);

    @Transactional
    @Modifying
    @Query(value = "delete from Subscription s where (s.subscriberID=?1 and s.userID=?2)")
    void deleteByUsrIDAndSubID(Long usrID, Long subID);
}
