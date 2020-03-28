package cn.edu.bupt.ch2_1.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@ApiModel(value = "用户信息",description = "ApiModel注解用户示例")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uid;//用户id
    @ApiModelProperty(value = "昵称")
    private String nickname;//用户昵称
    @ApiModelProperty(value="密码")
    private String password;//用户密码

}
