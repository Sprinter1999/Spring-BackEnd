package cn.edu.bupt.ch5_3.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "朋友关系")
public class Friend {
    @TableId(type = IdType.AUTO)
    public Long pid; //pairID
    public Long fid;  //友人B
    public Long uid;   //友人A

    public Friend() {
    }

    public Friend(Long pid, Long uid, Long fid) {
        this.pid=pid;
        this.uid=uid;
        this.fid=fid;
    }
}
