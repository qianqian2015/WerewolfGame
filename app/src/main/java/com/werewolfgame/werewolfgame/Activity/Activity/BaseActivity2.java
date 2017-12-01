package com.werewolfgame.werewolfgame.Activity.Activity;

import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017-05-14.
 */
public class BaseActivity2 extends AppCompatActivity {
    protected PowerManager.WakeLock wakeLock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PowerManager powerManager = (PowerManager)this.getSystemService(Context.POWER_SERVICE);
         wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "My Lock");
    }

    @Override
    protected void onStart() {
        super.onStart();
        wakeLock.acquire();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wakeLock.release();
    }
}
