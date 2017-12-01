package com.werewolfgame.werewolfgame.Activity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.werewolfgame.werewolfgame.Activity.Adapter.KillChooseAdapter;
import com.werewolfgame.werewolfgame.Activity.KillInterface;
import com.werewolfgame.werewolfgame.Activity.utils.Utils;
import com.werewolfgame.werewolfgame.R;

import butterknife.BindView;
import butterknife.OnClick;

public class WitchActivity extends BaseActivity implements KillInterface {

//    private TextView helpTitle,killTitle;
    private int helpNum;
    private int witchKillNum = -1 ;
    private KillChooseAdapter adapter;
    @BindView(R.id.title_text)
    TextView title_text;

    @BindView(R.id.help_layout)
    LinearLayout helpLayout;

    @BindView(R.id.poison_layout)
    LinearLayout poisonLayout;

    @BindView(R.id.gridview)
    GridView poisonGridview;


    @BindView(R.id.tv_title)
    TextView tvTitle;


//    @BindView(R.id.tv_headline)
//    TextView headline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playMusic(Utils.SOUND_WITCH_1);
    }


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_witch;
    }

    @Override
    public void init(){
        helpNum = getIntent().getIntExtra("killNum",-1);
//        mHandler = new Handler();
    }
    @Override
    public void initView(){
//        btActionOK = (RadioButton)findViewById(R.id.action_ok);

        adapter = new KillChooseAdapter(this,this,KillChooseAdapter.TYPE_WITCH);
        poisonGridview.setAdapter(adapter);
        poisonGridview.setVisibility(View.GONE);
        if(helpNum != -1){
//            Html.fromHtml(String.format(getString(R.string.help_tile),helpNum));
            tvTitle.setText(Html.fromHtml(String.format(getString(R.string.help_tile),helpNum +1)));
            helpLayout.setVisibility(View.VISIBLE);
            poisonLayout.setVisibility(View.GONE);
        }else {
            tvTitle.setText(getString(R.string.nobody_kill));
            helpLayout.setVisibility(View.GONE);
            poisonLayout.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({ R.id.bt_help_yes, R.id.bt_help_no, R.id.bt_kill_yes, R.id.bt_kill_no })
    public void OnClick(View v){
        switch (v.getId()){
            case R.id.bt_help_yes:
                poisonLayout.setVisibility(View.GONE);
                helpNum = -1;
                setActionOkView();
                break;
            case R.id.bt_help_no:
                helpLayout.setVisibility(View.GONE);
                poisonLayout.setVisibility(View.VISIBLE);
//                killTitle.setVisibility(View.VISIBLE);
                break;
            case R.id.bt_kill_yes:
                helpLayout.setVisibility(View.GONE);
                poisonLayout.setVisibility(View.GONE);
                tvTitle.setText(getString(R.string.kill_who));
                title_text.setText(getString(R.string.witch_nvwoduren));
                showKillTab();
                break;
            case R.id.bt_kill_no:
                setActionOkView();
                poisonLayout.setVisibility(View.GONE);
                tvTitle.setVisibility(View.GONE);
                break;
        }
    }

    private void showKillTab(){
        poisonGridview.setVisibility(View.VISIBLE);
    }

    @Override
    public void kill(int killNum) {
        witchKillNum = killNum;
        setActionOkView();
    }

    // 延迟跳转到下一个页面
    @Override
    public void delayToNextPage(){
        countDownTextView.cancel();
        playMusic(Utils.SOUND_WITCH_2);
        Intent intent = new Intent(WitchActivity.this,SeerActivity.class);
        intent.putExtra("killNum", witchKillNum);
        intent.putExtra("helpNum", helpNum);
        delayToJumpActivity(intent,true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
