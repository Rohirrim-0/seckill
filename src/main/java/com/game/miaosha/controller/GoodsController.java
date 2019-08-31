package com.game.miaosha.controller;

import com.game.miaosha.domain.MiaoshaUser;
import com.game.miaosha.domain.User;
import com.game.miaosha.result.Result;
import com.game.miaosha.service.MiaoshaUserService;
import com.game.miaosha.service.RedisService;
import com.game.miaosha.service.UserService;
import com.game.miaosha.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by ls on 2019/8/5.
 */
@Controller
@RequestMapping("/goods")
@Slf4j
public class GoodsController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @Autowired
    MiaoshaUserService miaoshaUserService;

    @RequestMapping("/to_list")
    //手机端传cookie可能是从url传的
    public String toLogin(Model model, MiaoshaUser miaoshaUser){
        model.addAttribute("user",miaoshaUser);
        return "goods_list";
    }



}
