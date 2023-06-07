package com.rz.xnetwork.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.rz.xnetwork.models.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    @Query( value = "select case when count(s)> 0 then true else false end from Subscription s where (s.subscriberId=?1 and s.userId=?2)")
    Boolean existsSubscription(Long userID1, Long userID2);

    @Transactional
    @Modifying
    @Query(value = "delete from Subscription s where (s.subscriberId=?1 and s.userId=?2)")
    void deleteByUsrIdAndSubId(Long subscriberId, Long userId);
}
