package com.game.miaosha.service;

import com.game.miaosha.dao.UserDao;
import com.game.miaosha.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ls on 2019/8/5.
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getById(int id){
        return userDao.getById(id);
    }

    @Transactional
    public boolean insert(){

        User user2 = new User();
        user2.setId(5);
        user2.setName("212");
        userDao.insert(user2);


        User user1 = new User();
        user1.setId(1);
        user1.setName("121");
        userDao.insert(user1);


        return true;
    }
}
