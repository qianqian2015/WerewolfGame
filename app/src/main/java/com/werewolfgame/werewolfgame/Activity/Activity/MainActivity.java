package com.werewolfgame.werewolfgame.Activity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.werewolfgame.werewolfgame.Activity.utils.SoundUtils;
import com.werewolfgame.werewolfgame.Activity.utils.Utils;
import com.werewolfgame.werewolfgame.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends WakeLockActivity {
    private static final String TAG = "MainActivity" ;
//    @BindView(R.id.sb_hum_num) SeekBar mSbHumNum;
    @BindView(R.id.bt_conform) ImageView btConform;
    @BindView(R.id.tv_players) TextView tvPlayerNum;
    @BindView(R.id.tv_mode_switch) TextView tvModeSwitch;
    @BindView(R.id.tv_role_num) TextView tvRoleNumIntroduction;
    @BindView(R.id.tv_renju) TextView tvPlayers;
    @BindView(R.id.bt_mode_switch) ImageView imgModeSwitch;
//    @BindView(R.id.bt_guard_switch)
//    ImageView imgGuardSwitch;
    @BindView(R.id.btn_sub)
    ImageButton btn_sub;          //人数加
    @BindView(R.id.btn_plus)
    ImageButton btn_plus;          //人数减
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate");
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        initView();
    }


    private void init() {
        initMusic();
    }
    private void initMusic() {
        // 通过 load 方法加载指定音频流，并将返回的音频 ID 放入 musicId 中

        SoundUtils.musicId.put(Utils.SOUND_WEREWOLF_1, SoundUtils.mSoundPool.load(this, R.raw.werewolf_1, 1));
        SoundUtils.musicId.put(Utils.SOUND_WEREWOLF_2, SoundUtils.mSoundPool.load(this, R.raw.werewolf_3, 1));
        SoundUtils.musicId.put(Utils.SOUND_WITCH_1, SoundUtils.mSoundPool.load(this, R.raw.witch_1, 1));
        SoundUtils.musicId.put(Utils.SOUND_WITCH_2, SoundUtils.mSoundPool.load(this, R.raw.witch_2, 1));
        SoundUtils.musicId.put(Utils.SOUND_SEER_1, SoundUtils.mSoundPool.load(this, R.raw.seer_1, 1));
        SoundUtils.musicId.put(Utils.SOUND_SEER_2, SoundUtils.mSoundPool.load(this, R.raw.seer_2, 1));
        SoundUtils.musicId.put(Utils.SOUND_OVER, SoundUtils.mSoundPool.load(this, R.raw.tianliangle, 1));
        SoundUtils.musicId.put(Utils.SOUND_CLICK, SoundUtils.mSoundPool.load(this, R.raw.click, 1));

    }

    //设置加或者减的图片显示
    private void setSubOrPlusBtnImage(){
         if(Utils.mPlayerNum <= 8){
             //最少8人
             btn_sub.setEnabled(false);
             btn_plus.setEnabled(true);

         }else if(Utils.mPlayerNum >=12){
             //最多12人
             btn_sub.setEnabled(true);
             btn_plus.setEnabled(false);

         }else{
             btn_sub.setEnabled(true);
             btn_plus.setEnabled(true);
         }

    }

    private void initView(){

        tvRoleNumIntroduction.setText(String.format(getString(R.string.role_num_introduction),Utils.getWerewolfNum(),Utils.mPlayerNum - Utils.getWerewolfNum()-3));
        tvPlayers.setText(String.format(getString(R.string.role_players),Utils.mPlayerNum));
//        mSbHumNum.setProgress(Utils.mPlayerNum - Utils.MIN_PLAYER_NUM );
        tvPlayerNum.setText(String.valueOf(Utils.mPlayerNum));
        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.mPlayerNum ++ ;
                setSubOrPlusBtnImage();
                tvPlayerNum.setText(String.valueOf(Utils.mPlayerNum));
                tvRoleNumIntroduction.setText(String.format(getString(R.string.role_num_introduction),Utils.getWerewolfNum(),Utils.mPlayerNum - Utils.getWerewolfNum()-3));
                tvPlayers.setText(String.format(getString(R.string.role_players),Utils.mPlayerNum));
            }
        });
        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.mPlayerNum -- ;
                setSubOrPlusBtnImage();
                tvPlayerNum.setText(String.valueOf(Utils.mPlayerNum));
                tvRoleNumIntroduction.setText(String.format(getString(R.string.role_num_introduction),Utils.getWerewolfNum(),Utils.mPlayerNum - Utils.getWerewolfNum()-3));
                tvPlayers.setText(String.format(getString(R.string.role_players),Utils.mPlayerNum));
            }
        });

        imgModeSwitch.setImageLevel(0);
        //默认添加守卫
//        imgGuardSwitch.setImageLevel(0);
        tvModeSwitch.setText(getString(R.string.mode_youpai));
        imgModeSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.bt_mode_switch:
                        switch (imgModeSwitch.getDrawable().getLevel()) {
                            case 0:
                                //分配身份
                                imgModeSwitch.setImageLevel(1);
                                tvModeSwitch.setText(getString(R.string.mode_wupai));
                                Log.i(TAG,"imgModeSwitch，需要分配身份");
                                break;
                            case 1:
                                //有牌，身份已分配好
                                imgModeSwitch.setImageLevel(0);
                                tvModeSwitch.setText(getString(R.string.mode_youpai));
                                Log.i(TAG,"imgModeSwitch，固定身份");
                                break;
                        }
                        break;

                }
            }
        });




        btConform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                switch (imgModeSwitch.getDrawable().getLevel()) {
                    case 0:
                        //分配身份
                        intent = new Intent(MainActivity.this, ChooseRoleActivity.class);
                        startActivity(intent);
                        Log.i(TAG,"btConform OnClickListener，分配身份");
                        break;
                    case 1:
                        //有牌，身份已分配好
                        intent = new Intent(MainActivity.this, AssignRoleActivity.class);
                        Log.i(TAG,"btConform OnClickListener，固定身份");
                        break;
                    default:
                        intent = new Intent(MainActivity.this, AssignRoleActivity.class);
                }
                startActivity(intent);
            }
        });




    }
}
