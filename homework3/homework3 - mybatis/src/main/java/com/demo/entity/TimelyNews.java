package com.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.Data;


@Data
@ApiModel(value = "动态", description = "所有动态信息")
public class TimelyNews {
    private Integer id;
    private Long time;
    private String news;
    @JsonIgnore
    private Integer userId;

    public TimelyNews() {
    }

    public TimelyNews(final Long time, final String news) {
        this.time = time;
        this.news = news;
    }
}
