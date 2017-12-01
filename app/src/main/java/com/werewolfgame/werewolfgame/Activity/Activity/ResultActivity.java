package com.werewolfgame.werewolfgame.Activity.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.werewolfgame.werewolfgame.Activity.DialogListener;
import com.werewolfgame.werewolfgame.Activity.View.DialogViewer;
import com.werewolfgame.werewolfgame.Activity.model.HeroInfo;
import com.werewolfgame.werewolfgame.Activity.utils.SoundUtils;
import com.werewolfgame.werewolfgame.Activity.utils.Utils;
import com.werewolfgame.werewolfgame.R;

public class ResultActivity extends WakeLockActivity {
    private int helpNum,killNum;
    private TextView tvResult;
    private TextView tvAllRoleIdentity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        init();
        initView();
        playMusic(Utils.SOUND_OVER);
    }
    private void init(){
        helpNum = getIntent().getIntExtra("helpNum",-1);
        killNum = getIntent().getIntExtra("killNum",-1);

    }

    private void initView(){
        tvResult = (TextView)findViewById(R.id.tv_result);
        tvAllRoleIdentity = (TextView)findViewById(R.id.tv_allrole);
        findViewById(R.id.role_check).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogViewer dialogViewer = new DialogViewer(ResultActivity.this, getString(R.string.check_allrole_identity), new DialogListener() {
                    @Override
                    public void onDialogClick(Dialog dialog, boolean isLeftButonClick, boolean isRightButtonClick) {
                        if(isRightButtonClick){
                            //查看所有玩家身份
                            tvAllRoleIdentity.setVisibility(View.VISIBLE);
                            tvAllRoleIdentity.setText(getAllRoleIdentity());
                        }
                    }
                });
                dialogViewer.show();
            }
        });
    }
    public void ResultOnClick(View v){
        tvResult.setVisibility(View.VISIBLE);
        if(killNum == -1){
            if(helpNum == -1){
                tvResult.setText("昨晚是个平安夜！");
            }else {
                tvResult.setText(String.format(getString(R.string.result),helpNum+1));
            }
        }else {
            if(helpNum != -1){
                tvResult.setText(String.format(getString(R.string.result_two),helpNum+1,killNum+1));
            }else {
                tvResult.setText(String.format(getString(R.string.result),killNum+1));
            }
        }


    }
    protected void playMusic(int id) {
        SoundUtils.playSound(SoundUtils.mSoundPool,id);
    }

    @NonNull
    private String getAllRoleIdentity(){
        StringBuffer roleIdentityStr = new StringBuffer();
        for(int i =0; i< Utils.mPlayerNum;i++){
            roleIdentityStr.append(String.format("%d号玩家,",i+1));
            HeroInfo heroInfo = HeroInfo.find(HeroInfo.class,Utils.roleArrayList.get(i)+1);
            if(heroInfo != null){
                roleIdentityStr.append(" 身份："+ heroInfo.getRoleName());
                roleIdentityStr.append("\n");
            }

        }



        return roleIdentityStr.toString();
    }
}
