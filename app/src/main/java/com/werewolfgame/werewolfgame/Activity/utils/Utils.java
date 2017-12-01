package com.werewolfgame.werewolfgame.Activity.utils;

import android.content.Context;
import android.util.Log;

import com.werewolfgame.werewolfgame.Activity.model.HeroInfo;
import com.werewolfgame.werewolfgame.Activity.randomUtil;
import com.werewolfgame.werewolfgame.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/30 0030.
 */
public class Utils {
    public final static String[] roleName = new String[]{"狼人", "村民", "预言家", "女巫", "猎人", "村民", "村民"};
    public final static int[] resArray = new int[]{ R.mipmap.werewolf,R.mipmap.villager,R.mipmap.seer,R.mipmap.witch,R.mipmap.hunter};
    public final static int[] rolePositive = new int[]{-1, 1, 1, 1, 1, 1};
    public static int MAX_ROLE_TYPE = 5;
    public final static int MIN_PLAYER_NUM = 8;

    public static ArrayList<HeroInfo> mHeroInfo = new ArrayList<HeroInfo>(); //保存玩家的身份信息

    public static int mPlayerNum = MIN_PLAYER_NUM + 1;
    public static final int TIME_DELAY = 6 * 1000;  //5秒延迟
    public static int TIME_COUNT = 25 * 1000;    //20秒倒计时

    public final static int SOUND_CLICK = 8;
    public final static int SOUND_WEREWOLF_1 = 1;
    public final static int SOUND_WEREWOLF_2 = 2;
    public final static int SOUND_WITCH_1 = 3;
    public final static int SOUND_WITCH_2 = 4;
    public final static int SOUND_SEER_1 = 5;
    public final static int SOUND_SEER_2 = 6;
    public final static int SOUND_OVER = 7;

    public static ArrayList<Integer> roleArrayList = new ArrayList<Integer>();

    /**
     * 对象不为空
     */
    public static boolean notNull(Object obj) {
        if (null != obj && !"".equals(obj)) {
            return true;
        }
        return false;


    }

    public static void initRoleList(){
        for(int i=0;i<mPlayerNum;i++){
            roleArrayList.add(-1);
        }
    }

    //创建随机角色
    public static void createRoleList(){
        initRoleList();
        ArrayList<Integer> roleTypeList = new ArrayList<>();
        //添加 狼人
        for(int i = 0;i< getWerewolfNum();i++){
            roleTypeList.add(0);
        }
        //添加 村民
        for(int i = 0;i< mPlayerNum - getWerewolfNum() - 3;i++){
            roleTypeList.add(1);
        }
        roleTypeList.add(2);
        roleTypeList.add(3);
        roleTypeList.add(4);

        roleArrayList =  randomUtil.randomList(roleTypeList);
        for(Integer role : roleTypeList){
            Log.e("Utils","type = "+role+"\\n");
        }
    }
    public static boolean isNull(Object obj) {
        if (null == obj || "".equals(obj)) {
            return true;
        }
        return false;
    }

    public static int dp2px(Context context, int dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int px2dp(Context context, int px) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static int getWerewolfNum() {
        switch (mPlayerNum) {
            case 8:
            case 9:
            case 10:
                return 3;
            case 11:
            case 12:
                return 4;
            default:
                return 3;

        }
    }
    public static int getDrawbleResId(int type){
        if(type< resArray.length){
            return resArray[type];
        }else
            return R.mipmap.villager;

    }


    public static void initSQliteData(Context context){


        //初始化 角色数据库内容

        String[] roleArray = context.getResources().getStringArray(R.array.roleName);
        for(int i = 0 ;i < MAX_ROLE_TYPE ; i++){
            HeroInfo heroInfo = new HeroInfo();
            if(i == 0){
                heroInfo.setPositive(-1);
            }
            else{
                heroInfo.setPositive(1);
            }
            heroInfo.setNum(i+1);
            heroInfo.setRoleName(roleArray[i]);
            heroInfo.setType(i);
            heroInfo.save();
        }

    }

    public static String getRoleName(int position){
        if(position>=0 && position<roleName.length){
            return roleName[position];
        }
        return "";
    }


}
