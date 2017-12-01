package com.werewolfgame.werewolfgame.Activity.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.werewolfgame.werewolfgame.Activity.Adapter.KillChooseAdapter;
import com.werewolfgame.werewolfgame.Activity.KillInterface;
import com.werewolfgame.werewolfgame.Activity.utils.Utils;
import com.werewolfgame.werewolfgame.R;

import butterknife.BindView;

public class SeerActivity extends BaseActivity implements KillInterface {
    private static final String TAG = "SeerActivity";
    @BindView(R.id.gridview) GridView verifyGrid;
    private int helpNum,witchKillNum ;
    private Dialog dialog;
    private KillChooseAdapter killChooseAdapter;
    private final int MSG_JUMPTONEXTPAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        init();
//        initView();
//        initCountDown();
        playMusic(Utils.SOUND_SEER_1);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_seer;
    }

    @Override
    public void init(){
        helpNum = getIntent().getIntExtra("helpNum",-1);
        witchKillNum = getIntent().getIntExtra("killNum",-1);
    }
    @Override
    public void initView(){
        verifyGrid = (GridView)findViewById(R.id.gridview);
        verifyGrid.setVisibility(View.VISIBLE);
        killChooseAdapter = new KillChooseAdapter(this,this,KillChooseAdapter.TYPE_SEER);
        verifyGrid.setAdapter(killChooseAdapter);

    }

    Handler mHandler = new Handler(){
        @Override
        public void dispatchMessage(Message msg) {
            if(MSG_JUMPTONEXTPAGE == msg.what ){
                dismissDialog();
                delayToNextPage();
            }

        }
    };

    @Override
    public void kill(int num) {
        if(Utils.roleArrayList != null && Utils.roleArrayList.size()>0){
            int type = Utils.roleArrayList.get(num);
            Log.i(TAG,"type = "+type);
            if(type>= 0 && type<Utils.MAX_ROLE_TYPE){
                int positive = Utils.rolePositive[type];
                showResultDialog(positive);
                //5秒之后调到关闭页面
                killChooseAdapter.setButtonDisable();
                mHandler.sendEmptyMessageDelayed(MSG_JUMPTONEXTPAGE,5000);
            }else{
                Toast.makeText(this,"数据有误，无法验证",Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this,"输入角色身份有误，无法验证",Toast.LENGTH_SHORT).show();
        }

    }

    private void showResultDialog(int type){

        View dialogView =  LayoutInflater.from(this).inflate(R.layout.seer_dialog, null);
        ImageView imageView = (ImageView)dialogView.findViewById(R.id.img_result);
        if(type == -1){
            imageView.setImageResource(R.mipmap.card_bad);
        }else{
            imageView.setImageResource(R.mipmap.card_good);
        }
        if(dialog == null){
            dialog = new Dialog(SeerActivity.this);

        }
        if(dialog.isShowing()){
            dialog.dismiss();
        }
        dialog.getWindow().setContentView(dialogView);
        dialog.show();

    }

    private void dismissDialog(){
        if( dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }

    }


    // 延迟跳转到下一个页面
    @Override
    public void delayToNextPage(){
        countDownTextView.cancel();
        playMusic(Utils.SOUND_SEER_2);
        Intent intent = new Intent(SeerActivity.this,ResultActivity.class);
        intent.putExtra("helpNum",helpNum);
        intent.putExtra("killNum",witchKillNum);
        delayToJumpActivity(intent,true);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



}
