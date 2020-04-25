package com.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "user_info")//该注解为数据库自动配置的table的名称
@ApiModel(value = "用户", description = "用户详细信息")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "用户ID")
    @Column(name = "user_id")
    private Integer userId;

    @ApiModelProperty(value = "昵称")
    private String account;
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 配置一对多关系
     *      1.声明关系
     *          @OneToMany : 配置一对多关系
     *              targetEntity ：定义关系类的类型
     *                             默认是该成员属性对应的类的类型，所以通常不需要提供定义
     *              mappedBy ：定义类之间的双向关系（如果类之间是单向关系，不需要提供定义）
     *                         该属性的值为“多”方class里的“一”方的属性名称
     *              注：
     *                  因为mySQL中的字段是 不区分大小写 的，（因数据库而异，不同数据库情况可能不同）
     *                  一般情况下会把属性名的 大写字母 给转换成 _+小写字母 了，
     *                  比如 UserName, 在数据库里面应该就是user_name（第一个字母前不加_）
     *
     *              cascade : 配置级联（可以配置到设置多表的映射关系的注解上，即这种关系是递归调用的）
     *                  CascadeType.all     : 所有
     *                              REFRESH ：级联刷新
     *                              REMOVE  ：级联删除
     *                              MERGE   ：级联更新
     *                              PERSIST ：级联保存
     *
     *              fetch : 配置关联对象的加载方式
     *                  EAGER：立即加载（主类加载时加载）
     *                  LAZY ：延迟加载（关系类被访问时才加载）
     *
     * 一对多多对一关系配置好后，直接使用属性即可，如user.timelyNewsList
     *
     *                                                                              ——by your friend zhicheng lee
     */

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ApiModelProperty(value="该用户的动态")
    private List<TimelyNews> timelyNewsList = new ArrayList<TimelyNews>();

    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ApiModelProperty(value="该用户关注的人")
    private List<Follow> followeeList = new ArrayList<Follow>();


    //@OneToMany(mappedBy = "followee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@ApiModelProperty(value="关注该用户的人")
    //private List<Follow> followerList = new ArrayList<Follow>();

    public UserInfo() {
    }

    public UserInfo(String account, String password) {
        this.account = account;
        this.password = password;
    }

}
