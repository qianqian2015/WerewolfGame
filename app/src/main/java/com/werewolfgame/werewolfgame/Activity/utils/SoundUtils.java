package com.werewolfgame.werewolfgame.Activity.utils;

import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/5/7 0007.
 */
public class SoundUtils {
    public static HashMap<Integer, Integer> musicId = new HashMap<Integer, Integer>();
    public static SoundPool mSoundPool = new SoundPool(12, AudioManager.STREAM_MUSIC, 0);
    public static void playSound(SoundPool soundPool, int id) {

//        soundPool.play(id, 1, 1, 1, 0, 1);
    }

}
