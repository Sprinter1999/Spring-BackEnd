package com.demo.controller;

import com.demo.common.Result;
import com.demo.entity.Follow;
import com.demo.entity.UserInfo;
import com.demo.repository.FollowRepository;
import com.demo.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(value = "UserController", description = "控制用户信息的控制器")
@RequestMapping("/v1/user")
@RestController
public class FriendsController {

    /*
     *   登陆之后才能使用该控制器功能，因此不判断该用户是否存在
     */

    @Autowired
    UserRepository userRepository;
    @Autowired
    FollowRepository followRepository;

    @ApiOperation(value = "添加关注用户", notes = "创建成功，返回关注信息")
    @PostMapping(path = "/{id1}/friends/add/{id2}")
    public Result<?> Add(@PathVariable Integer id1, @PathVariable Integer id2) {
        Optional<UserInfo> result = userRepository.findById(id1);

        if (result.isPresent()) {//该用户存在
            Optional<UserInfo> followee = userRepository.findById(id2);
            UserInfo user = result.get();

            if (followee.isPresent()) {//被关注用户存在
                List<Follow> followList = followRepository.findByFollowerUserIdAndFolloweeUserId(id1, id2);

                if (followList.isEmpty()) {//该关注关系并未建立过
                    Follow follow = new Follow(followee.get().getAccount());
                    follow.setFollower(user);
                    follow.setFollowee(followee.get());

                    //user.getFolloweeList().add(follow);
                    follow = followRepository.save(follow);
                    //userRepository.save(user);

                    return Result.ok(follow);
                } else {
                    return Result.error("该关注关系已存在！");
                }
            } else {
                return Result.error(500, "被关注用户不存在！");
            }
        }else{
            return Result.error("该用户不存在！");
        }
    }

    @ApiOperation(value = "删除关注用户", notes = "如果不存在，则返回状态码404，如果存在且删除成功，则返回状态码204")
    @DeleteMapping(path = "/{id1}/friends/delete/{id2}")
    public Result<?> Delete(@PathVariable Integer id1, @PathVariable Integer id2) {
        Optional<UserInfo> followee = userRepository.findById(id2);

        if (followee.isPresent()) {//该用户存在
            //不删除未更新外键的关系是否会产生问题？？
            userRepository.deleteById(id2);
            return Result.ok(204, "删除成功！");
        } else {
            return Result.error(404, "该用户不存在，删除失败！");
        }
    }

    @ApiOperation(value = "获取被关注者信息", notes = "返回值为user信息，为空则返回状态码404")
    @GetMapping(path = "/{id}/friends/info-show")
    public Result<?> Show(@PathVariable Integer id) {
        List<Follow> followeeList = userRepository.findById(id).get().getFolloweeList();

        if (!followeeList.isEmpty()) {
            return Result.ok(followeeList);
        } else {
            return Result.ok("该用户尚无好友！");
        }
    }

    @ApiOperation(value = "查找被关注者", notes = "返回值为user信息，为空则返回状态码404")
    @GetMapping(path = "/{id1}/friends/search/{id2}")
    public Result<?> Search(@PathVariable Integer id1, @PathVariable Integer id2) {
        List<Follow> followList = followRepository.findByFollowerUserIdAndFolloweeUserId(id1, id2);

        if(!followList.isEmpty()){
            Optional<UserInfo> user = userRepository.findById(id2);

            return Result.ok(user);
        }else{
            return Result.error(404, "您并未关注该用户！");
        }
    }
}
