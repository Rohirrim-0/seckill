package com.game.miaosha.controller;

import com.game.miaosha.domain.User;
import com.game.miaosha.redis.UserKey;
import com.game.miaosha.result.Result;
import com.game.miaosha.service.RedisService;
import com.game.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ls on 2019/8/5.
 */
@Controller
@RequestMapping("/demo")
public class SampleController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbGet(){
        User user = userService.getById(1);
        return Result.success(user);
    }

    @RequestMapping("/db/tx")
    @ResponseBody
    public Result<Boolean> dbInsert(){
        userService.insert();
        return Result.success(true);
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet(){
        User user = redisService.get(UserKey.getById,"user1", User.class);
        System.out.println(redisService.get(UserKey.getById,"user1", User.class));
        return Result.success(user);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet(){
        User user = new User();
        user.setId(33);
        user.setName("33333");
        boolean b1 = redisService.set(UserKey.getById,"user1", user);
        System.out.println(redisService.get(UserKey.getById,"user1", User.class));
        return Result.success(true);
    }
}
