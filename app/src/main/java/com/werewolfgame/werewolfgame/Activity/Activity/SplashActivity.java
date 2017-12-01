package com.werewolfgame.werewolfgame.Activity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.werewolfgame.werewolfgame.Activity.utils.SpUtils;
import com.werewolfgame.werewolfgame.Activity.utils.Utils;
import com.werewolfgame.werewolfgame.R;

public class SplashActivity extends AppCompatActivity {
    private boolean isFirstStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void init(){
        setContentView(R.layout.activity_splash);
        isFirstStart = SpUtils.getInstance(this).getBoolean("isFirst",true);
//        if(isFirstStart){
////            initData();
//            SpUtils.getInstance(this).putBoolean("isFirst",false);
//         }
        initView();
    }
    //初始化数据
    private void initData() {
        Thread thread = new Thread(){
            @Override
            public void run() {
                Utils.initSQliteData(getApplicationContext());
            }
        };
        thread.start();
    }

    private void initView() {
        findViewById(R.id.bt_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMainAct();
            }
        });
    }

    private void toMainAct(){
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}
