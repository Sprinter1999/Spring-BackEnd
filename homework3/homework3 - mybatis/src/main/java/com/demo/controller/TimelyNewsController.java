package com.demo.controller;

import com.demo.common.Result;
import com.demo.entity.Follow;
import com.demo.entity.TimelyNews;
import com.demo.entity.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Api(value = "TimelyNewsController", description = "控制动态消息的控制器")
@RequestMapping("/v1/user")
@RestController
public class TimelyNewsController {

    /*
     *   登陆之后才能使用该控制器功能，因此不判断该用户是否存在
     */

//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    TimelyNewsRepository timelyNewsRepository;
//
//    @ApiOperation(value = "添加动态消息", notes = "添加成功，返回状态码201")
//    @PostMapping(path = "/{id}/display-add")
//    public Result<?> DisplayAdd(@PathVariable Integer id, @RequestBody String news) {
//        TimelyNews timelyNews = new TimelyNews(System.currentTimeMillis(), news);
//        Optional<UserInfo> result = userRepository.findById(id);
//
//        if (result.isPresent()) {
//            UserInfo user = result.get();
//
//            timelyNews.setPublisher(user);
//            timelyNewsRepository.save(timelyNews);
//
//            return Result.ok(timelyNews);
//        } else {
//            return Result.error(404, "该用户不存在");
//        }
//    }
//
//    @ApiOperation(value = "删除动态消息", notes = "如果不存在，则返回状态码404，如果存在且删除成功，则返回状态码204")
//    @DeleteMapping(path = "/{id1}/display-delete/{id2}")
//    public Result<?> DisplayDelete(@PathVariable Integer id1, @PathVariable Integer id2) {
//        Optional<TimelyNews> timelyNews = timelyNewsRepository.findById(id2);
//
//        if (timelyNews.isPresent()) {//该动态存在
//            //不删除未更新外键的关系是否会产生问题？？
//            timelyNewsRepository.deleteById(id2);
//            return Result.ok(204, "删除成功！");
//        } else {
//            return Result.error(404, "该动态不存在，删除失败！");
//        }
//    }
//
//    @ApiOperation(value = "更新动态消息", notes = "返回值为更新后的动态消息，为空则返回状态码404")
//    @PutMapping(path = "/{id1}/display-update/{id2}")
//    public Result<?> DisplayUpdate(@PathVariable Integer id1, @PathVariable Integer id2, @RequestBody String news) {
//        Optional<TimelyNews> result = timelyNewsRepository.findById(id2);
//
//        if (result.isPresent()) {//更新
//            //并未检查该用户是否发布该动态
//            TimelyNews timelyNews = result.get();
//            timelyNews.setNews(news);
//            timelyNews.setTime(System.currentTimeMillis());
//
//            timelyNews = timelyNewsRepository.save(timelyNews);
//
//            return Result.ok(timelyNews);
//        } else {
//            return Result.error(404, "该动态不存在！");
//        }
//    }
//
//    @ApiOperation(value = "获取动态消息", notes = "返回值为动态消息，为空则返回状态码404")
//    @GetMapping(path = "/{id}/display-show")
//    public Result<?> DisplayShow(@PathVariable Integer id) {
//        Optional<UserInfo> user = userRepository.findById(id);
//
//        if (user.isPresent()) {//用户存在
//            List<Follow> followeeList = user.get().getFolloweeList();
//
//            if (!followeeList.isEmpty()) {//被关注者存在
//                List<TimelyNews> timelyNewsList = new ArrayList<TimelyNews>();
//
//                for (int i = 0; i < followeeList.size(); i++) {//添加被关注用户动态消息
//                    Optional<UserInfo> result = userRepository.findById(followeeList.get(i).getFollowee().getUserId());
//
//                    if(result.isPresent()){//被关注用户存在
//                        UserInfo followee = result.get();
//
//                        if (followee.getTimelyNewsList().size() > 0) {
//                            timelyNewsList.addAll(followee.getTimelyNewsList());
//                        }
//                    }
//                }
//
//                if(timelyNewsList.size()>0){
//                    //动态未排序
//                    return Result.ok(timelyNewsList);
//                }else{
//                    return Result.error(404,"该用户的好友没有动态！");
//                }
//            } else {
//                return Result.error(404, "该用户没有好友！");
//            }
//        } else {
//            return Result.error(404, "该用户不存在！");
//        }
//    }
}
