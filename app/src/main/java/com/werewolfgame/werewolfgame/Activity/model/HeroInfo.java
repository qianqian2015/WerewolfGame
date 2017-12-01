package com.werewolfgame.werewolfgame.Activity.model;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2016/2/5.
 */
public class HeroInfo extends DataSupport {
    private int num;      //num
    private int positive; //good or bad
    private String roleName;   //name
    private int type ;  //1: werewolf ;2: volleage ; 3:witch ; 4: seer ; 5: hunter

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getPositive() {
        return positive;
    }

    public void setPositive(int positive) {
        this.positive = positive;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
