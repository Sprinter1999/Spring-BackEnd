package cn.edu.bupt.ch2_1.controller;

import cn.edu.bupt.ch2_1.common.Result;
import cn.edu.bupt.ch2_1.entity.Blog;
import cn.edu.bupt.ch2_1.entity.Friend;
import cn.edu.bupt.ch2_1.entity.User;
import cn.edu.bupt.ch2_1.repository.BlogRepository;
import cn.edu.bupt.ch2_1.repository.FriendRepository;
import cn.edu.bupt.ch2_1.repository.NoPassword;
import cn.edu.bupt.ch2_1.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Api(value = "UserController v1", description = "利用自定义消息格式的用户信息控制器")
@RequestMapping("/v1/users")
@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    FriendRepository friendRepository;
    @Autowired
    BlogRepository blogRepository;
    
    @ApiOperation(value = "获取用户列表", notes = "返回值为全部的userlist，为空时状态码为204")
    @GetMapping(path = "/", produces = "application/json")
    public Result<?> listAll() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return Result.error(204, "users为空");
        }
        return Result.ok(users);
    }

    @ApiOperation(value = "根据uid获取相应的user,获取特定用户的信息", notes = "如果不存在，返回状态码为404")
    @GetMapping(path = "/{uid}", produces = "application/json")
    public Result<?> getById(@PathVariable("uid") Long uid) {
        Optional<User> result = userRepository.findById(uid);
        if (result.isPresent()) {
            return Result.ok(result.get());
        } else {
            return Result.error(404, "该user不存在");
        }
    }

    @ApiOperation(value = "创建新的user", notes = "创建成功，返回状态码201")
    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public Result<?> add(@RequestBody User user) {
        user = userRepository.save(user);
        return Result.ok(user);
    }

    @ApiOperation(value = "创建新的双向好友关系", notes = "创建成功，返回状态码201,否则返回好友不存在")
    @PostMapping(path = "/{uid}/friends", consumes = "application/json", produces = "application/json")
    public Result<?> addFriend(@RequestBody Friend fri) {
        Optional<User> result=userRepository.findById(fri.fid);
        if(result.isPresent())
        {
            fri = friendRepository.save(fri);
//            System.out.println(fri);
            Friend reverse=new Friend(fri.pid+1,fri.fid,fri.uid);
//            System.out.println(reverse);
            reverse=friendRepository.save(reverse);
            return Result.ok("Add Friend Success");
        }
        else
        {
            return Result.error(404,"目标好友不存在");
        }
    }

    @ApiOperation(value = "根据id删除user", notes = "如果不存在，则返回状态码404，如果存在且删除成功，则返回状态码204")
    @ApiResponses({@ApiResponse(code = 204, message = "删除成功")})
    @DeleteMapping(path = "/{uid}")
    public Result<?> delete(@PathVariable Long uid) {
        Optional<User> result = userRepository.findById(uid);
        if (result.isPresent()) {
            userRepository.deleteById(uid);
            return Result.ok();
        } else {
            return Result.error(204, "该user不存在，删除失败");
        }
    }

    @ApiOperation(value = "更新user", notes = "如果不存在，则返回状态码404")
    @PutMapping(path = "/{uid}", consumes = "application/json", produces = "application/json")
    public Result<?> update(@PathVariable Long uid, @RequestBody User user) {
        Optional<User> result = userRepository.findById(uid);
        if (result.isPresent()) {
            User currentUser = result.get();
            currentUser.setNickname(user.getNickname());
            currentUser.setPassword(user.getPassword());
            userRepository.save(currentUser);
            return Result.ok(currentUser);
        } else {
            return Result.error(404, "该user不存在");
        }
    }


    @ApiOperation(value = "Delete Friend", notes = "如果不存在，返回状态码404")
    @DeleteMapping(path = "/{uid}/friends/{fid}", produces = "application/json")
    public Result<?> deleteFriend(@PathVariable("uid") Long uid, @PathVariable("fid") Long fid) {
        Optional<Friend> result = friendRepository.findByUidAndFid(uid, fid);
        System.out.println(uid);
        if (result.isPresent()) {
            friendRepository.deleteByUidAndFid(uid, fid);
            friendRepository.deleteByUidAndFid(fid,uid);
            return Result.ok("Delete Done");
        }
        else
            return Result.error(404,"目标好友关系不存在");

    }

    @ApiOperation(value = "Get Friend list", notes = "获取当前用户的所有好友")
    @GetMapping(path = "/{uid}/friends", produces = "application/json")
    public Result<?> getFriends(@PathVariable Long uid) {
        List<Friend> friendRelation = friendRepository.findAllByUid(uid);
        if(friendRelation.isEmpty())
        {
            return Result.error(204,"无好友");
        }
        else
        {
            List<Optional<NoPassword>> myfriends = new ArrayList<>();
            for (Friend friID : friendRelation) {
                long idForOnce = friID.fid;
                NoPassword usrForOnce=userRepository.findByUid(idForOnce);
                myfriends.add(Optional.ofNullable(usrForOnce));
            }
            return Result.ok(myfriends);
        }
    }


    
    @ApiOperation(value="Get all friends blogs",notes="获取所有好友的动态列表")
    @GetMapping(path="/{uid}/friends/blogs", produces = "application/json")
    public Result<?> getFriendsBlogs(@PathVariable Long uid)
    {
        List<Friend> friendRelation = friendRepository.findAllByUid(uid);
        List<Optional<Blog>> myFriendsBlogs = new ArrayList<>();
        for (Friend friID : friendRelation) {
            long idForOnce = friID.fid;
            List<Optional<Blog>> BlogsForOnce = blogRepository.findAllByUid(idForOnce);
            myFriendsBlogs.addAll(BlogsForOnce);
        }
        return Result.ok(myFriendsBlogs);
    }

    @ApiOperation(value="Get a specific user's blogs",notes="获取某一特定用户的动态，无动态则返回404")
    @GetMapping(path="/{uid}/blogs",produces = "application/json")
    public Result<?> getAFriendBlog(@PathVariable Long uid) {
        List<Optional<Blog>> blogs=blogRepository.findAllByUid(uid);
        if(blogs.isEmpty())
        {
            return Result.error(204,"No blogs!");
        }
        else
        {
            return Result.ok(blogs);
        }
    }

}

