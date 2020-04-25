package com.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@ApiModel(value = "动态", description = "所有动态信息")
public class TimelyNews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "timelyNewsId")
    private Integer id;

    @ApiModelProperty(value = "发布时间")
    private Long time;
    @ApiModelProperty(value = "动态内容")
    private String news;

    /**
     * 配置多对一关系
     *     使用注解的形式配置多对一关系
     *      1.配置表关系
     *          @ManyToOne : 配置多对一关系
     *              targetEntity ：定义关系类的类型
     *                             默认是该成员属性对应的类的类型，所以通常不需要提供定义
     *      2.配置外键（中间表）
     *          @JoinColumn : 配置外键
     *              name ：外键字段名称
     *                     若不设置，默认取值为 ：实体名称 +"_"+ 被引用的主键列的名称
     *                     不设置，貌似会生成一张中间表之类，貌似不太好？
     *                     反正设置name属性就对了，上面情况具体参阅https://blog.csdn.net/mlin_123/article/details/51395701
     *
     *              referencedColumnName ：参照的主表的主键字段名称
     *                                     默认值为被引用的实体的主键的名称，因此通常不需要提供定义
     *                                     若不想被引用的实体的主键作为外键，则需要设置
     *
     *              注：
     *                  Follow实体中有配置双外键的例子和一个猜测
     *
     *          @JsonBackReference : 用于解决循环引用的问题
     *                              （比如本代码中，返回给前端的UserInfo实体被实例化，由于双向引用，
     *                                导致实例化的json数据无限循环）
     *              @JsonBackReference 在此处的作用相当于 @JsonIgnore，即在被注解处打断循环
     *
     *              拓展：
     *                  （可不看，本代码中没有使用）
     *              @JsonBackReference 与 @JsonManagedReference 这两个标注通常配对使用，通常用在父子关系中
     *              @JsonBackReference 标注的属性在序列化（即将对象转换为json数据）时，会被忽略（即结果中的json数据不包含该属性的内容）
     *              @JsonManagedReference 标注的属性则会被序列化
     *              在序列化时，@JsonBackReference 的作用相当于 @JsonIgnore，此时可以没有 @JsonManagedReference，
     *              所以我就没用 @JsonManagedReference
     *
     *              具体参考https://blog.csdn.net/qq_35357001/article/details/55505659
     *
     * * 配置外键的过程，配置到了多的一方，就会在多的一方维护外键
     *
     *                                                                              ——by your friend zhicheng lee
     */

    @ManyToOne()
    @JoinColumn(name = "user_info_user_id")
    @JsonBackReference
    @ApiModelProperty(value = "发布者ID")
    private UserInfo publisher;

    public TimelyNews() {
    }

    public TimelyNews(final Long time, final String news) {
        this.time = time;
        this.news = news;
    }
}
