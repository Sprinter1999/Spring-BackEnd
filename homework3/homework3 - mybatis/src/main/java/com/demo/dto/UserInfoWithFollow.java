package com.demo.dto;

import com.demo.entity.Follow;

import java.util.List;

public class UserInfoWithFollow {
    private Integer userId;
    private String account;
    private String password;
    List<Follow> followList;
}
