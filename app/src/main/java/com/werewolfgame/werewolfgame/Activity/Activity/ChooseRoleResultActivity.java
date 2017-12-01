package com.werewolfgame.werewolfgame.Activity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.werewolfgame.werewolfgame.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChooseRoleResultActivity extends AppCompatActivity implements View.OnClickListener {
   /*
    * 新的身份验证页面
    * */

    @BindView(R.id.ll_choose_success)    //success
    RelativeLayout ll_choose_success;


    @BindView(R.id.ll_choose_fail)       //fail
    RelativeLayout ll_choose_fail;


    @BindView(R.id.btn_fail)
    ImageButton btn_fail;

    @BindView(R.id.btn_success)
    ImageButton btn_success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_role_result_layout);
        ButterKnife.bind(this);
        initView();

    }



    //初始化界面组件


    private void initView(){
        Intent intent = getIntent();
        boolean isVerifySuccess = intent.getBooleanExtra("isVerifySuccess",false);
        if(isVerifySuccess){
            ll_choose_success.setVisibility(View.VISIBLE);
            btn_success.setOnClickListener(this);
        }else{
            ll_choose_fail.setVisibility(View.VISIBLE);
            btn_fail.setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_fail:
                gotoChooseRoleAct();
                break;
            case R.id.btn_success:
                //next step
                gotoWerewolfAct();
                break;


        }
    }

    private void gotoWerewolfAct(){
        Intent intent = new Intent();
        intent.setClass(this,WerewolfActivity.class);
        startActivity(intent);
        finish();
    }
    private void gotoChooseRoleAct(){
        Intent intent = new Intent();
        intent.setClass(this,ChooseRoleActivity.class);
        startActivity(intent);
        finish();
    }
}
