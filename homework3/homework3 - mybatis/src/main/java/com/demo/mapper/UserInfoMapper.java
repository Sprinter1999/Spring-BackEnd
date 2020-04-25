package com.demo.mapper;

import com.demo.dto.UserInfoWithFollow;
import com.demo.dto.UserInfoWithTimelyNews;
import com.demo.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoMapper {
    List<UserInfo> findAll();

    int create(UserInfo userInfo);

    UserInfo findById(@Param(value = "id") Integer id);

    int deleteById(@Param(value = "id") Integer id);

    List<UserInfoWithFollow> findAllWithFollows();

    List<UserInfoWithTimelyNews> findAllWithTimelyNews();
}
