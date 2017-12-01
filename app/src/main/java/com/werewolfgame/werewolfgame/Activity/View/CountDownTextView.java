package com.werewolfgame.werewolfgame.Activity.View;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.werewolfgame.werewolfgame.Activity.TimeOutInterface;
import com.werewolfgame.werewolfgame.Activity.utils.Utils;
import com.werewolfgame.werewolfgame.R;

/**
 * Created by Administrator on 2017/5/10.
 */
public class CountDownTextView extends LinearLayout {
    private Context context;
    private TextView countDownText;
    private TimeOutInterface timeOutInterface;
    private String countDownTextString = "操作倒计时%d";
    public CountDownTextView(Context context) {
        super(context);
        this.context = context;
        initView();
    }
    public void setTimeOutInterface(TimeOutInterface timeOutInterface){
        this.timeOutInterface = timeOutInterface;
    }
    public CountDownTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public CountDownTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView(){
        countDownText = new TextView(context);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 10, 10, 10);
        countDownText.setLayoutParams(layoutParams);
        countDownText.setTextSize(20);
        countDownText.setTextColor(context.getResources().getColor(R.color.darkRed));
//            radioButton.setBackgroundResource(R.drawable.bt_bg_style);
        countDownText.setGravity(Gravity.CENTER);
        addView(countDownText);
    }

    private CountDownTimer timer = new CountDownTimer(Utils.TIME_COUNT, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            countDownText.setText(String.format(countDownTextString,millisUntilFinished / 1000));
        }

        @Override
        public void onFinish() {
//            countDownText.setText("时间到!请闭眼！");
            timeOutInterface.timeout();
        }
    };
    public void setCountDownTextString(String string){
        countDownTextString = string;
    }

    public void startCount(){
        countDownText.setVisibility(VISIBLE);
        timer.start();
    }
    public void setTime(int millis){
        Utils.TIME_COUNT = millis;
    }
    public void cancel(){
        countDownText.setVisibility(GONE);
        timer.cancel();
    }
}
