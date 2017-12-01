package com.werewolfgame.werewolfgame.Activity;

/**
 * Created by Administrator on 2017/5/7 0007.
 */
public interface RoleItemInterface {
    void identifyRole();
    void refreshAdapter(int position);
    void setCountPlus1();
    int getRoleChooseCount();
    void setRoleMaxNumArrayPlus1(int position);
}
