package com.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

@Data
@ApiModel(value = "关注关系", description = "关注关系记录")
public class Follow {
    private Integer id;
    private String account;
    @JsonIgnore
    @ApiModelProperty(value = "关注者ID")
    private Integer followerId;
    @JsonIgnore
    @ApiModelProperty(value = "被关注者ID")
    private Integer followeeId;

    public Follow() {
    }

    public Follow(String account) {
        this.account = account;
    }
}
