package com.game.miaosha.redis;

/**
 * Created by ls on 2019/8/14.
 */
public class UserKey extends BasePrefix {

    public UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getById = new UserKey("id");
    private static UserKey getByName = new UserKey("name");


}
