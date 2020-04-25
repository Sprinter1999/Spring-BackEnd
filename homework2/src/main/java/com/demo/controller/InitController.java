package com.demo.controller;

import com.demo.common.Result;
import com.demo.repository.UserRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.demo.entity.UserInfo;

import java.util.Optional;

@Api(value = "InitController", description = "控制注册、登陆的控制器")
@RequestMapping("/v1")
@RestController
public class InitController {
    @Autowired
    UserRepository userRepository;

    @ApiOperation(value = "登陆", notes = "返回登陆账号、密码的正误信息")
    @GetMapping(path = "/login")
    public Result<?> Login(Integer id, String password) {
        Optional<UserInfo> user = userRepository.findById(id);

        if (user.isPresent()) {//存在该用户

            if (user.get().getPassword().equals(password)) {//密码正确
                return Result.ok("登录成功！");
            } else {//密码错误
                return Result.error("密码错误！");
            }
        } else {//账号不存在
            return Result.ok("账号错误！");
        }
    }

    @ApiOperation(value = "注册", notes = "返回用户信息")
    @PostMapping(path = "/register")
    public Result<?> Register(@RequestBody UserInfo user) {
        user=userRepository.save(user);

        return Result.ok(user);
    }
}