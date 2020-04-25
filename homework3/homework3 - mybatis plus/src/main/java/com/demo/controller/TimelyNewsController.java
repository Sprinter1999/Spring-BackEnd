package com.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.common.Result;
import com.demo.entity.TimelyNews;
import com.demo.entity.UserInfo;
import com.demo.mapper.TimelyNewsMapper;
import com.demo.mapper.UserInfoMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "TimelyNewsController", description = "控制动态消息的控制器")
@RequestMapping("/v1/user")
@RestController
public class TimelyNewsController {
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    TimelyNewsMapper timelyNewsMapper;

    @ApiOperation(value = "添加动态消息", notes = "添加成功，返回状态码201")
    @PostMapping(path = "/{id}/display-add")
    public Result<?> Add(@PathVariable Integer id, @RequestBody TimelyNews timelyNews) {
        UserInfo user = userInfoMapper.selectById(id);

        if (user != null) {
            timelyNews.setUserId(id);
            timelyNewsMapper.insert(timelyNews);
            return Result.ok(timelyNews);
        } else {
            return Result.error(404, "该用户不存在");
        }
    }

    @ApiOperation(value = "删除动态消息", notes = "如果不存在，则返回状态码404，如果存在且删除成功，则返回状态码204")
    @DeleteMapping(path = "/{id1}/display-delete/{id2}")
    public Result<?> Delete(@PathVariable Integer id1, @PathVariable Integer id2) {
        //未检查该动态是否属于该用户
        int count = timelyNewsMapper.deleteById(id2);

        if (count > 0) {
            return Result.ok(204, "删除成功！");
        } else {
            return Result.error(404, "该动态不存在，删除失败！");
        }
    }

    @ApiOperation(value = "更新动态消息", notes = "返回值为更新后的动态消息，为空则返回状态码404")
    @PutMapping(path = "/{id1}/display-update/{id2}")
    public Result<?> Update(@PathVariable Integer id1, @PathVariable Integer id2, @RequestBody TimelyNews timelyNews) {
        //并未检查该用户是否存在、该用户是否发布该动态
        timelyNews.setId(id2);
        int count=timelyNewsMapper.updateById(timelyNews);

        if(count>0){
            return Result.ok(timelyNews);
        }else{
            return Result.error(404, "该动态不存在！");
        }
    }

    @ApiOperation(value = "获取动态消息", notes = "返回值为动态消息，为空则返回状态码404")
    @GetMapping(path = "/{id}/display-show")
    public Result<?> Show(@PathVariable Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", id);
        List<TimelyNews> timelyNewsList = timelyNewsMapper.selectList(queryWrapper);

        if (!timelyNewsList.isEmpty()) {
            return Result.ok(timelyNewsList);
        } else {
            return Result.error(404, "动态不存在！");
        }
    }
}
