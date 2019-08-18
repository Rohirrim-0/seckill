package com.game.miaosha.service;

import com.game.miaosha.dao.MiaoshaUserDao;
import com.game.miaosha.domain.MiaoshaUser;
import com.game.miaosha.result.CodeMsg;
import com.game.miaosha.util.MD5Util;
import com.game.miaosha.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ls on 2019/8/18.
 */
@Service
public class MiaoshaUserService {

    @Autowired
    MiaoshaUserDao miaoshaUserDao;

    public MiaoshaUser getById(long id){
        return miaoshaUserDao.getById(id);
    }

    public CodeMsg login(LoginVo loginVo) {
        if (loginVo == null){
            return CodeMsg.SERVER_ERROR;
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        MiaoshaUser user = getById(Long.parseLong(mobile));
        if (user==null){
            return CodeMsg.MOBILE_NOT_EXIST;
        }
        //validate password
        String dbPass = user.getPassword();
        String salt = user.getSalt();
        String calPass = MD5Util.formPassToDBPass(formPass, salt);
        if (!calPass.equals(dbPass)){
            return CodeMsg.PASSWORD_ERROR;
        }
        return CodeMsg.SUCCESS;
    }
}
