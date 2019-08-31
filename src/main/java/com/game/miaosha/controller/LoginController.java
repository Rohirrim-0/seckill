package com.game.miaosha.controller;

import com.game.miaosha.domain.User;
import com.game.miaosha.redis.UserKey;
import com.game.miaosha.result.CodeMsg;
import com.game.miaosha.result.Result;
import com.game.miaosha.service.MiaoshaUserService;
import com.game.miaosha.service.RedisService;
import com.game.miaosha.service.UserService;
import com.game.miaosha.util.ValidatorUtil;
import com.game.miaosha.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by ls on 2019/8/5.
 */
@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @Autowired
    MiaoshaUserService miaoshaUserService;

    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response,@Valid LoginVo loginVo){
        //login
        miaoshaUserService.login(response,loginVo);
        return Result.success(true);
    }

}
