package com.werewolfgame.werewolfgame.Activity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.werewolfgame.werewolfgame.Activity.Adapter.KillChooseAdapter;
import com.werewolfgame.werewolfgame.Activity.KillInterface;
import com.werewolfgame.werewolfgame.Activity.utils.Utils;
import com.werewolfgame.werewolfgame.R;

import butterknife.BindView;


public class WerewolfActivity extends BaseActivity implements KillInterface {
    @BindView(R.id.gridview)
    GridView playersNubGrid;
    private int killNum = -1;
//    private RadioButton btKillOk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playMusic(Utils.SOUND_WEREWOLF_1);
    }

    @Override
    public void initData() {
        super.initData();
    }
    //初始化标题


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_werewolf;
    }

    @Override
    public void initView(){

        playersNubGrid.setVisibility(View.VISIBLE);
        KillChooseAdapter killChooseAdapter = new KillChooseAdapter(this,this,KillChooseAdapter.TYPE_WEREWOLF);
        playersNubGrid.setAdapter(killChooseAdapter);
    }


    @Override
    public void kill(int killNum) {
        this.killNum = killNum;
        setActionOkView();
    }

    // 延迟跳转到下一个页面
    @Override
    public void delayToNextPage(){
        countDownTextView.cancel();
        playMusic(Utils.SOUND_WEREWOLF_2);
        Intent intent = new Intent(WerewolfActivity.this,WitchActivity.class);
        intent.putExtra("killNum", killNum);
        delayToJumpActivity(intent,true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
