package com.werewolfgame.werewolfgame.Activity.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.werewolfgame.werewolfgame.Activity.DialogListener;
import com.werewolfgame.werewolfgame.Activity.KillInterface;
import com.werewolfgame.werewolfgame.Activity.View.DialogViewer;
import com.werewolfgame.werewolfgame.Activity.utils.Utils;
import com.werewolfgame.werewolfgame.R;

/**
 * Created by Administrator on 2017/4/25 0025.
 */
public class KillChooseAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater mInflater;
    private  KillInterface killInterface;
    private View.OnClickListener onClickListener;
    private int Type;
    public static final int TYPE_WEREWOLF = 1;
    public static final int TYPE_WITCH = 2;
    public static final int TYPE_SEER = 3;
    boolean clickable = true;

    private int mClickPosition = -1;

    public KillChooseAdapter(Context context, KillInterface killInterface,int Type) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.killInterface = killInterface;
        this.Type = Type;
    }



    @Override
    public int getCount() {
        return Utils.mPlayerNum;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHoder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHoder();
            convertView = mInflater.inflate(R.layout.kill_choose_item, null);
            viewHolder.killButton = (Button)convertView.findViewById(R.id.kill_bt);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHoder) convertView.getTag();
        }
            Log.e("zq","position = "+position);

            viewHolder.killButton.setText(String.valueOf(position+1));

            viewHolder.killButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String content = null;
                    switch (Type){
                        case TYPE_WEREWOLF:
                            content = String.format(context.getString(R.string.kill_comfirm), position+1);
                            break;
                        case TYPE_WITCH:
                            content = String.format(context.getString(R.string.witch_kill_comfirm), position+1);
                            break;
                        case TYPE_SEER:
                            content = String.format(context.getString(R.string.seer_comfirm), position+1);
                            if(mClickPosition == -1 || (mClickPosition != -1 && mClickPosition == position)){

                                killInterface.kill(position);
                                mClickPosition = position;
                            }
                            return;
                    }
                    DialogViewer dialogViewer = new DialogViewer(context, content, new DialogListener() {
                        @Override
                        public void onDialogClick(Dialog dialog, boolean isLeftButonClick, boolean isRightButtonClick) {
                            if(isRightButtonClick){
                                killInterface.kill(position);
                            }
                        }
                    });
                    dialogViewer.show();
                }
            });

        return convertView;
    }

    private class ViewHoder {
        public Button killButton;
    }


    public void setButtonDisable(){
        clickable = false;
    }


}
