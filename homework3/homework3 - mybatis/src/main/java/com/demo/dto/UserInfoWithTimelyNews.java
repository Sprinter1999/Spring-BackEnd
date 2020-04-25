package com.demo.dto;

import com.demo.entity.TimelyNews;

import java.util.List;

public class UserInfoWithTimelyNews {
    private Integer userId;
    private String account;
    private String password;
    List<TimelyNews> timelyNewsList;
}
