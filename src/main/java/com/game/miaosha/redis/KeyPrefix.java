package com.game.miaosha.redis;

/**
 * Created by ls on 2019/8/13.
 */
public interface KeyPrefix {

    int expireSeconds();

    String getPrefix();
}
