package com.werewolfgame.werewolfgame.Activity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

import com.werewolfgame.werewolfgame.Activity.DialogListener;
import com.werewolfgame.werewolfgame.Activity.TimeOutInterface;
import com.werewolfgame.werewolfgame.Activity.View.CountDownTextView;
import com.werewolfgame.werewolfgame.Activity.View.DialogViewer;
import com.werewolfgame.werewolfgame.Activity.utils.SoundUtils;
import com.werewolfgame.werewolfgame.Activity.utils.Utils;
import com.werewolfgame.werewolfgame.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity extends WakeLockActivity implements TimeOutInterface {
    @BindView(R.id.ll_countdown)
    CountDownTextView countDownTextView;

//    @BindView(R.id.tv_headline)
//    TextView headline;

    @BindView(R.id.operate_layout)LinearLayout chooseLayout;

    @BindView(R.id.ll_action_ok)
    LinearLayout ll_action_ok;

    private int time = 0;

    private Handler mHandler;
    private Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //子类不再需要设置布局ID，也不再需要使用ButterKnife.bind()
        setContentView(provideContentViewId());
        ButterKnife.bind(this);

        mHandler = new Handler();
        init();
        initData();
        initView();
//        initHeadline();
        initCountDown();
    }
    public void init(){

    }


    //得到当前界面的布局文件id(由子类实现)
    protected abstract int provideContentViewId();
    @Override
    protected void onStop() {
        super.onStop();
    }

    public void initView() {

    }


    public void initData() {
    }

//    public abstract void initHeadline();


    protected void initCountDown(){
//        countDownTextView = (CountDownTextView)findViewById(R.id.ll_countdown);
//        countDownTextView.setTime(getTime());
        countDownTextView.setTimeOutInterface(this);
        countDownTextView.startCount();
    }

    private int getTime(){
        if(time == 0){
            return Utils.TIME_COUNT;
        }
        return time;
    }
    //若要子类设置倒计时时间，请复写次方法
    public void setTime(int time){
        this.time = time;
    }

    //倒计时间到，显示操作成功，并延迟调到下个页面
    @Override
    public void timeout() {
        setActionOkView();
        delayToNextPage();
    }

    protected abstract void delayToNextPage();

    protected void playMusic(int id) {
        SoundUtils.playSound(SoundUtils.mSoundPool,id);
    }


    public void jumpToActivity(Intent intent) {
        startActivity(intent);
    }

    public void jumpToActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

    public void jumpToWebViewActivity(String url) {
//        Intent intent = new Intent(this, WebViewActivity.class);
//        intent.putExtra("url", url);
//        jumpToActivity(intent);
    }
  //延迟跳转
    public void delayToJumpActivity(final Intent intent,final boolean isFinishSelf){
        mRunnable = new Runnable() {
            @Override
            public void run() {
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                if(isFinishSelf){
                    finish();
                }
            }
        };
        mHandler.postDelayed(mRunnable,Utils.TIME_DELAY);
    }




    public void jumpToActivityAndClearTask(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public void jumpToActivityAndClearTop(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    //显示弹窗
    public void showDialogViewer(String content, DialogListener listener){
        DialogViewer dialogViewer = new DialogViewer(this,content,listener);
        dialogViewer.setCancelable(false);
        dialogViewer.show();
    }

    //显示弹窗，底部只有一个按钮
    public void showDialogViewer2(String content,int resId,DialogListener listener){
        DialogViewer dialogViewer = new DialogViewer(this,null,R.style.menuTextStyle,resId,content,"确定",listener);
        dialogViewer.setCancelable(false);
        dialogViewer.show();
    }

    protected void setActionOkView(){
        ll_action_ok.setVisibility(View.VISIBLE);
        chooseLayout.setVisibility(View.GONE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mHandler != null){
            mHandler.removeCallbacks(mRunnable);
        }
        if(countDownTextView != null){
            countDownTextView.cancel();
        }
    }


}
