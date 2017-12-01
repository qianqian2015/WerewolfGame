package com.werewolfgame.werewolfgame.Activity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.werewolfgame.werewolfgame.Activity.Adapter.AssignRoleAdapter;
import com.werewolfgame.werewolfgame.Activity.AssignInterface;
import com.werewolfgame.werewolfgame.Activity.utils.Utils;
import com.werewolfgame.werewolfgame.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AssignRoleActivity extends AppCompatActivity implements AssignInterface{
    private static final String TAG = "AssignRoleActivity" ;
    @BindView(R.id.role_listview)
    ListView roleListview;
    @BindView(R.id.assign_title_tv)
    TextView title;
    private AssignRoleAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_choose);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        Utils.createRoleList();
    }
    private void initView() {
        title.setText(getString(R.string.assign_title));
        adapter = new AssignRoleAdapter(this,this,Utils.roleArrayList);
        roleListview.setAdapter(adapter);
    }



    @Override
    public void next() {
        Intent intent = new Intent(AssignRoleActivity.this,WerewolfActivity.class);
        startActivity(intent);
        finish();
    }

}
