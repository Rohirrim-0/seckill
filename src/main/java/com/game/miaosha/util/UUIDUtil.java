package com.game.miaosha.util;

import java.util.UUID;

/**
 * Created by ls on 2019/8/27.
 */
public class UUIDUtil {

    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
