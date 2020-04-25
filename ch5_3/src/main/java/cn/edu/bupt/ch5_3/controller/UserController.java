package cn.edu.bupt.ch5_3.controller;

import cn.edu.bupt.ch5_3.common.Result;
import cn.edu.bupt.ch5_3.entity.Comment;
import cn.edu.bupt.ch5_3.entity.Friend;
import cn.edu.bupt.ch5_3.entity.Product;
import cn.edu.bupt.ch5_3.entity.User;
import cn.edu.bupt.ch5_3.mapper.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Api(value = "UserController")
@RestController
@RequestMapping("/v1/users")
public class UserController {

    private UserMapper userMapper;
    private BlogMapper blogMapper;
    private FriendMapper friendMapper;


    public UserController(BlogMapper blogMapper, UserMapper userMapper,FriendMapper friendMapper) {
        this.blogMapper = blogMapper;
        this.userMapper = userMapper;
        this.friendMapper=friendMapper;
    }

    @ApiOperation(value = "获取用户列表", notes = "返回值为全部用户的列表")
    @GetMapping(path = "/", produces = "application/json")
    public Result<?> listAll() {
        List<User> users=userMapper.selectList(null);
        return Result.ok(users);
    }

    @ApiOperation(value="根据uid获取特定用户信息",notes="如果不存在则返回404")
    @GetMapping(path="/{uid}",produces="application/json")
    public Result<?> getById(@PathVariable("uid") Long uid){
        Optional<User> result= Optional.ofNullable(userMapper.selectById(uid));
        if(result.isPresent()){
            return Result.ok(result.get());
        }else{
            return Result.error(404,"该用户不存在");
        }
    }

    @ApiOperation(value = "添加新的用户", notes = "创建成功，返回状态码201")
    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public Result<?>  add(@RequestBody User user) {
        userMapper.insert(user);
        return Result.ok(user);
    }

    @ApiOperation(value = "根据id删除用户", notes = "如果不存在，则返回状态码404，如果存在且删除成功，则返回状态码204")
    @ApiResponses({@ApiResponse(code = 204, message = "删除成功")})
    @DeleteMapping(path = "/{userId}")
    public Result<?> delete(@PathVariable Long userId) {
        int count = userMapper.deleteById(userId);
        if (count>0) {
            return Result.ok();
        } else {
            return Result.error(204, "该用户不存在");
        }
    }

    @ApiOperation(value="更新用户信息",notes="如果用户不存在，则返回404")
    @PutMapping(path = "/{uid}",consumes = "application/json",produces = "application/json")
    public Result<?> update(@PathVariable Long uid,@RequestBody User user)
    {
        Optional<User> result= Optional.ofNullable(userMapper.selectById(uid));
        if(result.isPresent())
        {
            User currentUser = result.get();
            currentUser.setNickname(user.getNickname());
            currentUser.setPassword(user.getPassword());
            userMapper.updateById(currentUser);
            return Result.ok(currentUser);
        }else{
            return Result.error(404,"用户不存在");
        }
    }

    @ApiOperation(value = "创建新的双向好友关系", notes = "创建成功，返回状态码201,否则返回好友不存在")
    @PostMapping(path = "/{uid}/friends", consumes = "application/json", produces = "application/json")
    public Result<?> addFriend(@RequestBody Friend fri) {
        Optional<User> result= Optional.ofNullable(userMapper.selectById(fri.fid));
        if(result.isPresent())
        {
            friendMapper.insert(fri);
            Friend reverse=new Friend(fri.pid+1,fri.fid,fri.uid);
            friendMapper.insert(reverse);
            return Result.ok("Add Friend Success");
        }
        else
        {
            return Result.error(404,"目标好友不存在");
        }
    }

    @ApiOperation(value = "Delete Friend", notes = "如果不存在，返回状态码404")
    @DeleteMapping(path = "/{uid}/friends/{fid}", produces = "application/json")
    public Result<?> deleteFriend(@PathVariable("uid") Long uid, @PathVariable("fid") Long fid) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("uid",uid);
        queryWrapper.eq("fid",fid);
        Optional<Friend> result= Optional.ofNullable(friendMapper.selectOne(queryWrapper));
        if (result.isPresent()) {
            friendMapper.delete(queryWrapper);
            QueryWrapper queryWrapper2=new QueryWrapper();
            queryWrapper2.eq("uid",fid);
            queryWrapper2.eq("fid",uid);
            friendMapper.delete(queryWrapper2);
            return Result.ok("Delete Done");
        }
        else
            return Result.error(404,"目标好友关系不存在");
    }

    @ApiOperation(value = "Get Friend list", notes = "获取当前用户的所有好友")
    @GetMapping(path = "/{uid}/friends", produces = "application/json")
    public Result<?> getFriends(@PathVariable Long uid) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("uid",uid);
        List<Friend> friendRelation = friendMapper.selectList(queryWrapper);
        if(friendRelation.isEmpty())
        {
            return Result.error(204,"用户无好友");
        }
        else
        {
            List<User> myfriends = new ArrayList<>();
            for (Friend friID : friendRelation) {
                long idForOnce = friID.fid;
                User usrForOnce=userMapper.selectById(idForOnce);
                myfriends.add(usrForOnce);
            }
            return Result.ok(myfriends);
        }
    }


}
