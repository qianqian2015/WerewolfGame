package com.werewolfgame.werewolfgame.Activity;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Administrator on 2017/4/28 0028.
 */
public  class randomUtil {

    /**
     * 打乱ArrayList
     */
    public static <V> ArrayList<V> randomList(ArrayList<V> sourceList) {
        if (isEmpty(sourceList)) {
            return sourceList;
        }

        ArrayList<V> randomList = new ArrayList<V>(sourceList.size());
        do {
            int randomIndex = Math.abs(new Random().nextInt(sourceList.size()));
            randomList.add(sourceList.remove(randomIndex));
        } while (sourceList.size() > 0);

        return randomList;
    }
    public static <V> boolean isEmpty(ArrayList<V> sourceList) {
        return (sourceList == null || sourceList.size() == 0);
    }

}
