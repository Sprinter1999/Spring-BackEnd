package com.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;


@Data
@ApiModel(value = "用户", description = "用户详细信息")
public class UserInfo {
    @TableId(type = IdType.AUTO)
    private Integer userId;
    private String account;
    private String password;

    public UserInfo() {
    }

    public UserInfo(String account, String password) {
        this.account = account;
        this.password = password;
    }

}
