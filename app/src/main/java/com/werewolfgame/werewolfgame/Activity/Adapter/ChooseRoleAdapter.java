package com.werewolfgame.werewolfgame.Activity.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.werewolfgame.werewolfgame.Activity.DialogListener;
import com.werewolfgame.werewolfgame.Activity.IdentifyInterface;
import com.werewolfgame.werewolfgame.Activity.RoleItemInterface;
import com.werewolfgame.werewolfgame.Activity.View.DialogViewer;
import com.werewolfgame.werewolfgame.Activity.utils.Utils;
import com.werewolfgame.werewolfgame.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/25 0025.
 */
public class ChooseRoleAdapter extends BaseAdapter implements RoleItemInterface {
    private Context context;
    private LayoutInflater mInflater;
    private ArrayList<Integer> mRoleChooseItemList;  //随机打算的序列列表
    public  int[] roleMaxNumArray = new int[]{0,0,0,0,0};  //临时保存每个角色选择的数量
    private  IdentifyInterface identifyInterface;

    private int count = 0;

    public ChooseRoleAdapter(Context context, IdentifyInterface identifyInterface, ArrayList<Integer> roleChooseItemList) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.identifyInterface = identifyInterface;
        this.mRoleChooseItemList = roleChooseItemList;
    }

    public void setData(ArrayList<Integer> roleChooseItemList){
        this.mRoleChooseItemList = roleChooseItemList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mRoleChooseItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return mRoleChooseItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.choose_role_item, null);
            viewHolder.roleItemTv = (TextView) convertView.findViewById(R.id.tv_role_item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.roleItemTv.setText(Utils.getRoleName(mRoleChooseItemList.get(position)));
        viewHolder.roleItemTv.setTag(mRoleChooseItemList.get(position));
        viewHolder.roleItemTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               final int tag = (int)view.getTag();
                Log.i("zq","tag = "+tag);
                if(tag >=0 && tag <Utils.roleName.length){
                    String content = String.format(context.getString(R.string.dialog_role_confirm),Utils.roleName[tag]);
                     DialogViewer dialogViewer = new DialogViewer(context, content, new DialogListener() {
                        @Override
                        public void onDialogClick(Dialog dialog, boolean isLeftButonClick, boolean isRightButtonClick) {
                            if(isRightButtonClick){
                                identifyInterface.clickedRoleItem(tag);
                            }
                        }
                    });
                    dialogViewer.show();
                }

            }
        });
        return convertView;
    }


    @Override
    public void identifyRole() {
        identifyInterface.identifyRole(roleMaxNumArray);
    }

    @Override
    public void refreshAdapter(int position) {
//        mRoleChooseItemList.get(position).setChecked(true);
        notifyDataSetChanged();
    }

    @Override
    public void setCountPlus1() {
        count ++;
    }

    @Override
    public int getRoleChooseCount() {
        return count;
    }

    @Override
    public void setRoleMaxNumArrayPlus1(int position) {
        roleMaxNumArray[position] = roleMaxNumArray[position] +1;
    }

    class ViewHolder {
            public TextView roleItemTv;
        }
}
