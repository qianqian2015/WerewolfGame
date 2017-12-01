package com.werewolfgame.werewolfgame.Activity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.werewolfgame.werewolfgame.Activity.Adapter.ChooseRoleAdapter;
import com.werewolfgame.werewolfgame.Activity.IdentifyInterface;
import com.werewolfgame.werewolfgame.Activity.randomUtil;
import com.werewolfgame.werewolfgame.Activity.utils.Utils;
import com.werewolfgame.werewolfgame.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.werewolfgame.werewolfgame.Activity.utils.Utils.roleArrayList;

public class ChooseRoleActivity extends AppCompatActivity implements IdentifyInterface {
   /*
    * 新的身份选择页面
    * */
    @BindView(R.id.tv_player_num)
    TextView tv_player_num;    //玩家编号

    @BindView(R.id.tv_role_choose_hint)
    TextView tv_role_choose_hint;    //玩家选择身份文字:?号玩家你的身份是？

    @BindView(R.id.ll_role_type)
    LinearLayout ll_role_type;

    @BindView(R.id.role_listview)
    ListView role_listview;

    private int index = 1;  //玩家序列号

    private static final String TAG = "ChooseRoleActivity";
    private ArrayList<Integer> maxNumArray = new ArrayList<Integer>();   //每个角色最多选择的次数
    private ChooseRoleAdapter adapter;
    private Button btCommit;
    private int[] tempArray = {0,0,0,0,0} ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate" );
        setContentView(R.layout.choose_role_layout);
        ButterKnife.bind(this);
        init();
        initView();

    }

    //初始化角色数据

    private void init()
    {
        Utils.roleArrayList.clear();
        initArrayList();
        initView();
    }

    private void initArrayList(){
        maxNumArray.add(0, Utils.getWerewolfNum());
        maxNumArray.add(1, Utils.mPlayerNum - Utils.getWerewolfNum() -3);

        maxNumArray.add(2,1);
        maxNumArray.add(3,1);
        maxNumArray.add(4,1);

    }


    //初始化界面组件


    private void initView(){
        tv_player_num.setText(String.valueOf(index));
        tv_role_choose_hint.setText(String.format(getString(R.string.choose_role_text1), index));
        adapter = new ChooseRoleAdapter(this,this, createRandomList());
        role_listview.setAdapter(adapter);
    }

    private ArrayList<Integer> createRandomList(){
        ArrayList<Integer> roleTypeList = new ArrayList<>();
        for(int i = 0; i< Utils.MAX_ROLE_TYPE; i++){
            roleTypeList.add(i);
        }
        Log.e("zq","roleTypeList.size = "+roleTypeList.size());
        return  new randomUtil().randomList(roleTypeList);
    }

    private void checkResult (){
        for(int i :tempArray){
            Log.i(TAG," i ="+i);
        }
        for(int j :maxNumArray){
            Log.i(TAG," j ="+j);
        }
        for(int i = 0; i< Utils.MAX_ROLE_TYPE; i++){
            if(maxNumArray.get(i) != tempArray[i]){
//                Utils.mHeroInfo.clear();
                gotoResultAct(false);
                break;
            }else if(i == Utils.MAX_ROLE_TYPE -1) {
                for (Integer heroInfo : roleArrayList) {
                    Log.i("zq", "" + heroInfo);
                }

                role_listview.setVisibility(View.GONE);

                gotoResultAct(true);
            }
        }
    }


    @Override
    public void identifyRole(int[] a) {
        btCommit.setVisibility(View.VISIBLE);
        tempArray = a;

    }

    @Override
    public void clickedRoleItem(int tag) {
        Utils.roleArrayList.add(tag);
        tempArray[tag]++;
        if(index >= Utils.mPlayerNum){
            //next step
            checkResult();

        }else{
            index ++ ;
            adapter.setData(createRandomList());
            tv_player_num.setText(String.valueOf(index));
            tv_role_choose_hint.setText(String.format(getString(R.string.choose_role_text1), index));

        }
    }

    private void gotoResultAct(boolean isVerrifySuccess){
        Intent intent = new Intent();
        intent.setClass(this,ChooseRoleResultActivity.class);
        intent.putExtra("isVerifySuccess",isVerrifySuccess);
        startActivity(intent);
    }


}
