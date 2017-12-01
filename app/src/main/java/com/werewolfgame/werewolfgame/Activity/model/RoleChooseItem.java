package com.werewolfgame.werewolfgame.Activity.model;

import java.util.List;

/**
 * Created by Administrator on 2017/5/7 0007.
 */
public class RoleChooseItem {
    private boolean isChecked;
    private int id;
    private List<Integer> randomList;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getRandomList() {
        return randomList;
    }

    public void setRandomList(List<Integer> list) {
        this.randomList = list;
    }
}
