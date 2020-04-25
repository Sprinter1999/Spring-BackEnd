package com.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@ApiModel(value = "关注关系", description = "关注关系记录")
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "被关注用户昵称")
    private String account;

    /**
     * 双外键的配置：
     *      一个猜测：
     *          （留待学分验证，记得把结果告诉我 [笑容逐渐猥琐] ）
     *          @JoinColumn 设置的名称应该是具有随意性的，为该属性在mySQL中的名称
     *          因为一张表中引用两个相同的外键很正常，
     *          但如果存在2个@JoinColumn的name属性相同的注解，会报错，通过解决此报错验证
     *      该报错：
     *          Follow中有两个外键，参照同一个主键，因而name属性的规范写法的取值就相同了，会报错，如下
     *          Repeated column in mapping for entity: com.demo.entity.Follow column:
     *              user_info_id (should be mapped with insert="false" update="false")
     *          这个提示有毒，我要是禁止插入和更新，写外键干什么？
     *          所以我给两个 @JoinColumn 的name属性写了不同的名字（乱写的），问题解决，学分最好再验证一下
     *
     *  双@JoinBackReference的配置：
     *      一个可能的报错：（没有验证过，在碰到之前就发现了，顺便，也留待学分验证 [啊哈哈哈哈] ）
     *          同一实体下两个@JsonBackReference是不能出现同一个value值的，
     *          然该注解的属性value，它有一个default值，
     *          所以我们填2个不同的value值即可
     *          具体参阅https://www.jianshu.com/p/da4a9eddd427
     *
     *  注：
     *      不要使用mySQL关键字作为属性名，如from
     *      此处@JoinColumn 和 @JsonBackReference 中属性写法均不规范，请参考TimelyNews中的注释
     *
     *                                                                              ——by your friend zhicheng lee
     */


    @ManyToOne()
    @JoinColumn(name = "user_info_follower")
    @JsonBackReference("user_info_follower")
    @ApiModelProperty(value = "关注者ID")
    private UserInfo follower;

    @ManyToOne()
    @JoinColumn(name = "user_info_followee")
    @JsonBackReference("user_info_followee")
    @ApiModelProperty(value = "被关注者ID")
    private UserInfo followee;

    public Follow() {
    }

    public Follow(String account) {
        this.account = account;
    }
}
