package com.demo.mapper;

import com.demo.entity.Follow;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowMapper {
    List<Follow> findAll();

    int create(Follow follow);

    Follow findByFollowerIdAndFolloweeId(@Param(value = "id1") Integer id1, @Param(value = "id2") Integer id2);

    int deleteById(@Param(value = "id") Integer id);
}
