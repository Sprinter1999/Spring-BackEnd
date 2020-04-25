package com.demo.mapper;

import com.demo.entity.Follow;
import com.demo.entity.TimelyNews;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimelyNewsMapper {
    List<TimelyNews> findAll();

    int create(TimelyNews timelyNews);

    TimelyNews findById(@Param(value = "id") Integer id);

    int deleteById(@Param(value = "id") Integer id);
}
