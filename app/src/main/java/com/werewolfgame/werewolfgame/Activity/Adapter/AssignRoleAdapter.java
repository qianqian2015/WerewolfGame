package com.werewolfgame.werewolfgame.Activity.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.werewolfgame.werewolfgame.Activity.AssignInterface;
import com.werewolfgame.werewolfgame.Activity.DialogListener;
import com.werewolfgame.werewolfgame.Activity.View.DialogViewer;
import com.werewolfgame.werewolfgame.Activity.utils.SoundUtils;
import com.werewolfgame.werewolfgame.Activity.utils.Utils;
import com.werewolfgame.werewolfgame.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-06-24.
 */
public class AssignRoleAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private AssignInterface assignInterface;
    private final ArrayList<Integer> roleArrayList;
    private Context context;
    private ArrayList<Boolean> hasLookArrayList = new ArrayList<>();
    private int mCount = 0;

    public AssignRoleAdapter(Context context, AssignInterface assignInterface, ArrayList<Integer> roleArrayList) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.assignInterface = assignInterface;
        this.roleArrayList = roleArrayList;
        for(int i = 0;i< roleArrayList.size() ;i++){
            hasLookArrayList.add(false);
        }
    }

    @Override
    public int getCount() {
        return roleArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return roleArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        Log.d("daixw","getView position="+position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.assign_role_item, null);
            viewHolder.lookedTv = (TextView)convertView.findViewById(R.id.img_has_choosed);
            viewHolder.lookBt = (Button) convertView.findViewById(R.id.bt_look_role);
            viewHolder.numTv = (TextView)convertView.findViewById(R.id.tv_num);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.lookBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹窗
                String content = String.format(context.getString(R.string.seer_page_result),position +1, Utils.roleName[roleArrayList.get(position)]);
                Log.i("adapter",position+1 +" = "+Utils.roleName[roleArrayList.get(position)]);
                DialogViewer dialogViewer = new DialogViewer(context,null,R.style.menuTextStyle,Utils.getDrawbleResId(roleArrayList.get(position)),content,"确定", new DialogListener() {
                    @Override
                    public void onDialogClick(Dialog dialog, boolean isLeftButonClick, boolean isRightButtonClick) {
                        SoundUtils.playSound(SoundUtils.mSoundPool,Utils.SOUND_CLICK);
                        hasLookArrayList.set(position,true);
                        mCount ++;
                        if(mCount >= roleArrayList.size()){
                            assignInterface.next();
                        }
                        notifyDataSetChanged();

                    }
                });
                dialogViewer.setCancelable(false);
                dialogViewer.show();
            }
        });
        viewHolder.numTv.setText(String.valueOf(position + 1));
        if(hasLookArrayList.get(position)){
            //若已查看，则隐藏按钮
            viewHolder.lookBt.setVisibility(View.GONE);
            viewHolder.lookedTv.setVisibility(View.VISIBLE);
        }else {
            viewHolder.lookBt.setVisibility(View.VISIBLE);
            viewHolder.lookedTv.setVisibility(View.GONE);
        }


        return convertView;
    }

    class ViewHolder {
        public TextView lookedTv;
        public TextView numTv;
        public Button lookBt;
    }
}
