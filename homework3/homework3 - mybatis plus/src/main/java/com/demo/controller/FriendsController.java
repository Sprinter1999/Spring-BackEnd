package com.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.common.Result;
import com.demo.entity.Follow;
import com.demo.entity.UserInfo;
import com.demo.mapper.FollowMapper;
import com.demo.mapper.UserInfoMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "UserController", description = "控制用户信息的控制器")
@RequestMapping("/v1/user")
@RestController
public class FriendsController {
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    FollowMapper followMapper;

    @ApiOperation(value = "添加关注用户", notes = "创建成功，返回关注信息")
    @PostMapping(path = "/{id1}/friends/add/{id2}")
    public Result<?> Add(@PathVariable Integer id1, @PathVariable Integer id2) {
        Follow follow=new Follow();
        UserInfo user=userInfoMapper.selectById(id2);

        if(user!=null){
            follow.setFollowerId(id1);
            follow.setFolloweeId(id2);
            follow.setAccount(user.getAccount());

            followMapper.insert(follow);
            return Result.ok(follow);
        }else{
            return Result.error(500, "被关注用户不存在！");
        }
    }

    @ApiOperation(value = "删除关注用户", notes = "如果不存在，则返回状态码404，如果存在且删除成功，则返回状态码204")
    @DeleteMapping(path = "/{id1}/friends/delete/{id2}")
    public Result<?> Delete(@PathVariable Integer id1, @PathVariable Integer id2) {
        QueryWrapper<Follow> queryWrapper=new QueryWrapper<Follow>();
        queryWrapper.eq("follower_id",id1).eq("followee_id",id2);
        int count=followMapper.delete(queryWrapper);

        if(count>0){
            return Result.ok(204, "删除成功！");
        }else{
            return Result.error(404, "该用户不存在，删除失败！");
        }
    }

    @ApiOperation(value = "获取被关注者信息", notes = "返回值为user信息，为空则返回状态码404")
    @GetMapping(path = "/{id}/friends/info-show")
    public Result<?> Show(@PathVariable Integer id) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("follower_id",id);
        List<Follow> followeeList = followMapper.selectList(queryWrapper);

        if (!followeeList.isEmpty()) {
            return Result.ok(followeeList);
        } else {
            return Result.error("该用户尚无好友！");
        }
    }

    @ApiOperation(value = "查找被关注者", notes = "返回值为user信息，为空则返回状态码404")
    @GetMapping(path = "/{id1}/friends/search/{id2}")
    public Result<?> Search(@PathVariable Integer id1, @PathVariable Integer id2) {
        QueryWrapper<Follow> queryWrapper=new QueryWrapper<Follow>();
        queryWrapper.eq("follower_id",id1).eq("followee_id",id2);
        Follow follow=followMapper.selectOne(queryWrapper);

        if(follow!=null){
            UserInfo user=userInfoMapper.selectById(id2);
            return Result.ok(user);
        }else{
            return Result.error(404, "您并未关注该用户！");
        }
    }
}
