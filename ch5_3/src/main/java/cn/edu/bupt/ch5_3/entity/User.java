package cn.edu.bupt.ch5_3.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "用户")
public class User {
    @TableId(type = IdType.AUTO)
    private Long uid;
    private String nickname;
    private String password;
}
