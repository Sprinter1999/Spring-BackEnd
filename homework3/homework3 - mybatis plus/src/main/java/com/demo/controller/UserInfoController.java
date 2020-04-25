package com.demo.controller;

import com.demo.common.Result;
import com.demo.entity.UserInfo;
import com.demo.mapper.UserInfoMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "UserInfoController", description = "控制用户信息的控制器")
@RequestMapping("/v1/user")
@RestController
public class UserInfoController {
    @Autowired
    UserInfoMapper userInfoMapper;

    @ApiOperation(value = "删除user信息", notes = "如果不存在，则返回状态码404，如果存在且删除成功，则返回状态码204")
    @DeleteMapping(path = "/{id}/info-delete")
    public Result<?> Delete(@PathVariable Integer id) {
        int count = userInfoMapper.deleteById(id);

        if (count > 0) {
            return Result.ok(204, "删除成功！");
        } else {
            return Result.error(404, "该用户不存在，删除失败！");
        }
    }

    @ApiOperation(value = "更新user信息", notes = "返回值为更新后的user信息，若不存在则返回状态码404")
    @PutMapping(path = "/{id}/info-update")
    public Result<?> Update(@PathVariable Integer id, @RequestBody UserInfo user) {
        user.setUserId(id);
        int count = userInfoMapper.updateById(user);

        if (count > 0) {
            return Result.ok(user);
        } else {  //若失败，是否为该用户不存在
            return Result.error(404, "该用户不存在！");
        }
    }

    @ApiOperation(value = "获取user信息", notes = "返回值为user信息，为空则返回状态码404")
    @GetMapping(path = "/{id}/info-show")
    public Result<?> Show(@PathVariable Integer id) {
        UserInfo user = userInfoMapper.selectById(id);

        if (user != null) {
            return Result.ok(user);
        } else {
            return Result.error(404, "该用户不存在！");
        }
    }
}
