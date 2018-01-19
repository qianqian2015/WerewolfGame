package com.werewolfgame.werewolfgame.Activity.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.werewolfgame.werewolfgame.Activity.utils.SoundUtils;
import com.werewolfgame.werewolfgame.Activity.utils.Utils;
import com.werewolfgame.werewolfgame.R;

public class ResultActivity extends WakeLockActivity {
    private int helpNum,killNum;
    private TextView tvResult;
    private TextView tvAllRoleIdentity;
    private TextView tvResultTitle, tvResultTitle2, tvResultTitle3;
    private Dialog dialog;

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
        tvResultTitle = (TextView) findViewById(R.id.tv_result_title);
        tvResultTitle2 = (TextView) findViewById(R.id.tv_result_title2);
        tvResultTitle3 = (TextView) findViewById(R.id.tv_result_title3);

        tvResultTitle.setText(Html.fromHtml(getString(R.string.result_title1)));
        tvResultTitle2.setText(Html.fromHtml(getString(R.string.result_title2)));

        tvResult = (TextView)findViewById(R.id.tv_result);
        tvAllRoleIdentity = (TextView)findViewById(R.id.tv_allrole);
    }
    public void ResultOnClick(View v){
        tvResult.setVisibility(View.VISIBLE);
        tvResultTitle3.setVisibility(View.VISIBLE);
        tvResultTitle2.setVisibility(View.GONE);
        tvResultTitle.setText(getString(R.string.result_text2));
        tvResultTitle.setTextColor(getResources().getColor(R.color.app_text_new_black));
        if(killNum == -1){
            if(helpNum == -1){
                tvResult.setText("是个平安夜！");
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
        findViewById(R.id.imageview_retry).setVisibility(View.VISIBLE);
        v.setVisibility(View.GONE);
    }

    public void OnClickRetry(View v) {
        //重新开始游戏

    }

    public void OnClickAllIdentity(View v) {
        //查看所有玩家身份
        showResultDialog();

    }
    protected void playMusic(int id) {
        SoundUtils.playSound(SoundUtils.mSoundPool,id);
    }

    @NonNull
    private String getAllRoleIdentity(){
        StringBuffer roleIdentityStr = new StringBuffer();
        for (int i = 0; i < Utils.roleArrayList.size(); i++) {
            roleIdentityStr.append(String.format("%d号:", i + 1));
//            HeroInfo heroInfo = HeroInfo.find(HeroInfo.class,Utils.roleArrayList.get(i)+1);
            String playName = Utils.roleName[Utils.roleArrayList.get(i)];
            if (!TextUtils.isEmpty(playName)) {
                roleIdentityStr.append(playName);
                if (i % 2 != 0) {
                    roleIdentityStr.append("\n");
                }
            }

        }
        return roleIdentityStr.toString();
    }

    private void showResultDialog() {

        View dialogView = LayoutInflater.from(this).inflate(R.layout.check_identy_dialog, null);

        TextView tvTitle = (TextView) dialogView.findViewById(R.id.tv_result_title);
        tvTitle.setText(getAllRoleIdentity());

        if (dialog == null) {
            dialog = new Dialog(ResultActivity.this);

        }
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        dialog.getWindow().setContentView(dialogView);
        dialog.show();

    }
}
