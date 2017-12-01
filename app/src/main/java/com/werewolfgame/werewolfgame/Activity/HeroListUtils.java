package com.werewolfgame.werewolfgame.Activity;

import com.werewolfgame.werewolfgame.Activity.model.HeroInfo;

import java.util.ArrayList;


/**
 * Created by Administrator on 2016/2/5.
 */
public class HeroListUtils {
    public static ArrayList<HeroInfo> mHeroDataList = new ArrayList<HeroInfo>();

//    public static HeroInfo creatHero(int heroId) {
//        HeroInfo heroInfo = new HeroInfo();
//        switch (heroId) {
//            case 0:
//                heroInfo.setName(R.string.werewolf);
//                heroInfo.setPositive(-1);
//                heroInfo.setImgId(R.mipmap.werewolf);
//                heroInfo.setNumMax(4);
//                break;
//
//
//            case 1:
//                heroInfo.setName(R.string.villager);
//                heroInfo.setPositive(1);
//                heroInfo.setImgId(R.mipmap.villager);
//                heroInfo.setNumMax(4);
//                break;
//
//
//            case 2:
//                heroInfo.setName(R.string.seer);
//                heroInfo.setPositive(1);
//                heroInfo.setImgId(R.mipmap.seer);
//                heroInfo.setNumMax(1);
//                break;
//
//
//            case 3:
//
//                heroInfo.setName(R.string.witch);
//                heroInfo.setPositive(1);
//                heroInfo.setImgId(R.mipmap.witch);
//                heroInfo.setNumMax(1);
//                break;
//
//
//            case 4:
//                heroInfo.setName(R.string.hunter);
//                heroInfo.setPositive(1);
//                heroInfo.setImgId(R.mipmap.hunter);
//                heroInfo.setNumMax(1);
//                break;
//
//           default:
//               heroInfo.setName(R.string.villager);
//               heroInfo.setPositive(1);
//               heroInfo.setImgId(R.mipmap.villager);
//               break;
//        }
//        return heroInfo;
//    }
//
//    public ArrayList<HeroInfo> creatHeroInfoList(ArrayList<Integer> heroIdList) {
//        ArrayList<HeroInfo> heroInfoList = new ArrayList<>();
//        for (int i = 0; i < heroIdList.size(); i++) {
//            Log.i("zq","heroIdList.get(i) = "+heroIdList.get(i));
//            heroInfoList.add(creatHero(heroIdList.get(i)));
//        }
//        return heroInfoList;
//    }

}
