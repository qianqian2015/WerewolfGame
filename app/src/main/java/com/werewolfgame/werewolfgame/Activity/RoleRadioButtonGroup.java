package com.werewolfgame.werewolfgame.Activity;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.werewolfgame.werewolfgame.Activity.View.DialogViewer;
import com.werewolfgame.werewolfgame.Activity.model.HeroInfo;
import com.werewolfgame.werewolfgame.Activity.utils.SoundUtils;
import com.werewolfgame.werewolfgame.Activity.utils.Utils;
import com.werewolfgame.werewolfgame.R;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/7 0007.
 */
public class RoleRadioButtonGroup extends LinearLayout {
    private static final String TAG = "RoleRadioButtonGroup";
    private List<Integer> list = new ArrayList<>();
    private Context mContext;
    private OnClickListener mButtomListener;
    private  RoleItemInterface roleItemInterface;
    private int mPosition;  //保存每个人选择的角色信息

    public RoleRadioButtonGroup(Context context) {
        super(context);
        mContext = context;
    }

    public RoleRadioButtonGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        for(int i = 0 ; i < Utils.MAX_ROLE_TYPE; i++) {
            Log.d("daixw","addView i ="+i);
            Button radioButton = new Button(mContext);
            LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT - 10, LayoutParams.WRAP_CONTENT - 10);
            layoutParams.setMargins(10, 10, 10, 10);
            layoutParams.weight = 1;
            radioButton.setLayoutParams(layoutParams);
            radioButton.setTextSize(16);
            radioButton.setTextColor(mContext.getResources().getColor(R.color.white));
            radioButton.setBackgroundResource(R.mipmap.btn_x);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                radioButton.setBackground(mContext.getResources().getDrawable(R.drawable.bt_bg_style));
//            }
            radioButton.setGravity(Gravity.CENTER);
            addView(radioButton);
        }
    }

    public void setData(List<Integer> list){
        this.list = list;
        initButtonInfo();
    }

    public void setRoleItemInterface(RoleItemInterface roleItemInterface ){
        this.roleItemInterface = roleItemInterface;
    }
    public void setPosition( int position){
        mPosition = position;
    }

    public void initButtonInfo() {
        Log.e("daixw","initButtonInfo list.size() ="+list.size());
        for(int i = 0 ; i <list.size(); i++){
            Button radioButton = (Button)getChildAt(i);
            if(radioButton != null){
//                radioButton.setText(Utils.roleName[list.get(i)]);
//                Song song = DataSupport.find(Song.class, id);
                Log.e("zq","list.get(i) = "+list.get(i));
                HeroInfo heroInfo = DataSupport.find(HeroInfo.class, list.get(i)+1); //根据id查找
                String roleName = heroInfo.getRoleName();
                if(!TextUtils.isEmpty(roleName)){
                    radioButton.setText(roleName);
                }
                radioButton.setTag(heroInfo.getType());
//                Log.i("daixw","i ="+i+"tag = list.get(i) = "+list.get(i));
                radioButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int tag =(int)v.getTag();
                        Log.i("t","tag = "+tag);
                        String content = String.format(mContext.getString(R.string.role_comfirm),mPosition+1 , Utils.roleName[tag]);
                        DialogViewer dialogViewer = new DialogViewer(mContext, content, new DialogListener() {
                            @Override
                            public void onDialogClick(Dialog dialog, boolean isLeftButonClick, boolean isRightButtonClick) {
                                if(isRightButtonClick){
                                    SoundUtils.playSound(SoundUtils.mSoundPool,Utils.SOUND_CLICK);
                                    if(tag>=0 && Utils.MAX_ROLE_TYPE > tag){
                                        roleItemInterface.setRoleMaxNumArrayPlus1(tag);
                                    }

//                                    Utils.mHeroInfo.add(DataSupport.find(HeroInfo.class,tag));
                                    Utils.roleArrayList.set(mPosition,tag);
                                    setVisibility(View.GONE);
                                    roleItemInterface.setCountPlus1();
                                    roleItemInterface.refreshAdapter(mPosition);
                                    if(roleItemInterface.getRoleChooseCount() >= Utils.mPlayerNum){
                                        //核对身份
                                        roleItemInterface.identifyRole();
                                    }
                                }
                            }
                        });
                        dialogViewer.show();
                    }
                });
            }

        }

    }
}
