package com.game.miaosha.service;

import com.game.miaosha.dao.MiaoshaUserDao;
import com.game.miaosha.domain.MiaoshaUser;
import com.game.miaosha.exception.GlobleException;
import com.game.miaosha.redis.MiaoshaUserKey;
import com.game.miaosha.result.CodeMsg;
import com.game.miaosha.util.MD5Util;
import com.game.miaosha.util.UUIDUtil;
import com.game.miaosha.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ls on 2019/8/18.
 */
@Service
public class MiaoshaUserService {

    public static final String COOKIE_NAME_TOKEN = "token";

    @Autowired
    MiaoshaUserDao miaoshaUserDao;

    @Autowired
    private RedisService redisService;

    public MiaoshaUser getById(long id){
        return miaoshaUserDao.getById(id);
    }

    public boolean login(HttpServletResponse response,LoginVo loginVo) {
        if (loginVo == null){
            throw  new GlobleException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        MiaoshaUser user = getById(Long.parseLong(mobile));
        if (user==null){
            throw new GlobleException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //validate password
        String dbPass = user.getPassword();
        String salt = user.getSalt();
        String calPass = MD5Util.formPassToDBPass(formPass, salt);
        if (!calPass.equals(dbPass)){
            throw new GlobleException(CodeMsg.PASSWORD_ERROR);
        }

        //生成cookie，存储token
        String token = UUIDUtil.uuid();
        redisService.set(MiaoshaUserKey.token,token,user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN,token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
        return true;
    }
}
