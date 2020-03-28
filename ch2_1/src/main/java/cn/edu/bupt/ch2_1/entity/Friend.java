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
@ApiModel(value="好友关系对",description = "单向好友关系")
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long pid; //pairID
    @ApiModelProperty(value="Friend B")
    public Long fid; //friendB ID

    public Friend() {
    }

    public Friend(Long pid, Long uid, Long fid) {
        this.pid=pid;
        this.uid=uid;
        this.fid=fid;
    }

    @ApiModelProperty(value="Friend A")
    public Long uid; //userA ID ???????

}
