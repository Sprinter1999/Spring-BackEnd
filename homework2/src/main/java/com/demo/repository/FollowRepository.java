package com.demo.repository;

import com.demo.entity.Follow;
import com.demo.entity.TimelyNews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Iterator;
import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Integer> {
    List<Follow> findByFollowerUserIdAndFolloweeUserId(Integer followerUserId, Integer followeeUserId);
}
