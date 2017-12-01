package com.werewolfgame.werewolfgame.Activity.model;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2016/5/1.
 */
public class MissionInfo extends DataSupport {
//    private String roleName;   //角色名称
    private int roleNum;     //角色人数
    private int totalNum;  //玩家人数
//    private int type ;    //角色类型



    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getRoleNum() {
        return roleNum;
    }

    public void setRoleNum(int roleNum) {
        this.roleNum = roleNum;
    }
}
