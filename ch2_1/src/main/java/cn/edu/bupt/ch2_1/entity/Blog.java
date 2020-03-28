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
@ApiModel(value = "用户动态",description = "ApiModel注解动态示例")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sid; //动态编号
    @ApiModelProperty(value = "用户昵称")
    private String nickname; //用户
    @ApiModelProperty(value="用户编号")
    private Long uid;
    @ApiModelProperty(value="内容")
    private String content; //动态内容

}
