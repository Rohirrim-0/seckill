package com.game.miaosha.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by ls on 2019/8/5.
 */
@Data
public class User {

    private int Id;

    private String name;

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                '}';
    }
}
