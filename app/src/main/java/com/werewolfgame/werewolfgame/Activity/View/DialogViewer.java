package com.werewolfgame.werewolfgame.Activity.View;

import android.app.Dialog;
import android.content.Context;
import android.text.Spanned;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.werewolfgame.werewolfgame.Activity.DialogListener;
import com.werewolfgame.werewolfgame.Activity.utils.Utils;
import com.werewolfgame.werewolfgame.R;


/**
 *
 *
 * @Description 弹框类
 */
public class DialogViewer {

    public static final int DIALOG_TITLE_STYLE_NORMAL = 0;//不显示图标
    public static final int DIALOG_TITLE_STYLE_SUCCESS = 1;//title显示打钩符号
    public static final int DIALOG_TITLE_STYLE_ATTENTION = 2;//title显示叹号
    public static final int DIALOG_TITLE_STYLE_ERROR = 3;//title显示叉

    private Context context;
    private Dialog dialog;
    private TextView titleTextView;
    private View titleLayout;
    private TextView contentView;
    private ImageView contentImg;
    //private ImageView titleImage;
    private Button leftButtonView;
    private Button rightButtonView;
    private Button submitButtonView;
    private View oneView;
    private View multiView;

    private String title;
    private int titleStyle;
    private int resId = 0;
    private String content;
    private Spanned contentSpanned;
    private String submitButtonText;
    private String leftButtonText;
    private String rightButtonText;
    private DialogListener dialogListener;
    private boolean isLeftActive;
    private boolean isRightActive;
    private boolean mDismissable = true;

    private View customView; //对话框中间内容视图  自定义视图

    /**
     * 可设置标题内容，左、右按钮文字内容
     *
     * @param context
     * @param title
     * @param leftButtonText
     * @param rightButtonText
     */
    public DialogViewer(Context context, String title, String leftButtonText, String rightButtonText) {
        this(context, title, DIALOG_TITLE_STYLE_NORMAL, null, leftButtonText, false, rightButtonText, true, null);
    }

    /**
     * 标题栏：attention图标 + 标题
     * 内容：没有
     * 按钮：确定与取消按钮
     *
     * @param context
     * @param title
     * @param dialogListener
     */
    public DialogViewer(Context context, String title, DialogListener dialogListener) {
        this(context, title, DIALOG_TITLE_STYLE_ATTENTION, null, context.getString(R.string.cancel), false, context.getString(R.string.button_comfirm), true, dialogListener);
    }

    /**
     * 标题栏：attention图标 + 标题
     * 内容：没有
     * 按钮：自定义
     *
     * @param context
     * @param title
     * @param leftButtonText
     * @param rightButtonText
     * @param dialogListener
     */
    public DialogViewer(Context context, String title, String leftButtonText, String rightButtonText, DialogListener dialogListener) {
        this(context, title, DIALOG_TITLE_STYLE_ATTENTION, null, leftButtonText, false, rightButtonText, true, dialogListener);
    }


    /**
     * 底部只有一个按钮,内容：文字+图片
     * 默认按钮文字为红色
     */
    public DialogViewer(Context context, String title, int titleStyle, int resId,String content, String submitButtonText, DialogListener dialogListener) {
        this(context, title, titleStyle, resId,content, null, false, null, false, submitButtonText, dialogListener);
    }

    /**
     * 底部只有一个按钮
     * 默认按钮文字为红色
     * @param context
     * @param title
     * @param titleStyle
     * @param content  自定义视图
     * @param submitButtonText
     */
    public DialogViewer(Context context, String title, int titleStyle, View content, String submitButtonText) {

        this.context = context;
        this.title = title;
        this.titleStyle = titleStyle;
        this.submitButtonText = submitButtonText;
        this.customView = content;

        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = (LinearLayout) inflater.inflate(R.layout.dialog_layout, null);

        dialog = new Dialog(context, R.style.dialog);
        dialog.getWindow().setContentView(dialogView);

        initTitle();
        initViewContent();
        initButton();
    }

    public DialogViewer(Context context, String title, int titleStyle, String content, String submitButtonText, DialogListener dialogListener, Spanned spannedContent) {
        this.context = context;
        this.title = title;
        this.titleStyle = titleStyle;
        this.content = content;
        this.dialogListener = dialogListener;
        this.submitButtonText = submitButtonText;
        this.contentSpanned = spannedContent;

        initView();
    }

    /**
     * 底部有两个按钮
     * 默认左边按钮文字是黑色，右边是红色
     */
    public DialogViewer(Context context, String title, int titleStyle, String content, String leftButtonText, String rightButtonText, DialogListener dialogListener) {
        this(context, title, titleStyle, content, leftButtonText, false, rightButtonText, true, dialogListener);
    }


    /**
     * 按钮颜色，
     * isLeftActive为true，则为红色 ，false为黑色
     * isRightActive为true，则为红色
     *
     * @param context
     * @param title
     * @param titleStyle
     * @param content
     * @param leftButtonText
     * @param isLeftActive
     * @param rightButtonText
     * @param isRightActive
     * @param dialogListener
     */
    public DialogViewer(Context context, String title, int titleStyle, String content, String leftButtonText, boolean isLeftActive, String rightButtonText, boolean isRightActive, DialogListener dialogListener) {
        this.context = context;
        this.title = title;
        this.titleStyle = titleStyle;
        this.content = content;
        this.leftButtonText = leftButtonText;
        this.rightButtonText = rightButtonText;
        this.dialogListener = dialogListener;
        this.isLeftActive = isLeftActive;
        this.isRightActive = isRightActive;

        initView();
    }


    public DialogViewer(Context context, String title, int titleStyle, String leftButtonText, boolean isLeftActive, String rightButtonText, boolean isRightActive, DialogListener dialogListener, Spanned content) {
        this.context = context;
        this.title = title;
        this.titleStyle = titleStyle;
        this.contentSpanned = content;
        this.leftButtonText = leftButtonText;
        this.rightButtonText = rightButtonText;
        this.dialogListener = dialogListener;
        this.isLeftActive = isLeftActive;
        this.isRightActive = isRightActive;

        if (!Utils.isNull(leftButtonText) && Utils.isNull(rightButtonText)) {
            this.submitButtonText = leftButtonText;
            this.leftButtonText = null;
            this.rightButtonText = null;
        }

        if (!Utils.isNull(rightButtonText) && Utils.isNull(leftButtonText)) {
            this.submitButtonText = rightButtonText;
            this.leftButtonText = null;
            this.rightButtonText = null;
        }

        initView();
    }

    public DialogViewer(Context context, String title, int titleStyle,int resId, String content, String leftButtonText, boolean isLeftActive, String rightButtonText, boolean isRightActive, String submitButtonText, DialogListener dialogListener) {
        this.context = context;
        this.title = title;
        this.titleStyle = titleStyle;
        this.content = content;
        this.leftButtonText = leftButtonText;
        this.rightButtonText = rightButtonText;
        this.dialogListener = dialogListener;
        this.isLeftActive = isLeftActive;
        this.isRightActive = isRightActive;
        this.submitButtonText = submitButtonText;
        this.resId = resId;
        initView();
    }

    public void setCancelable(boolean flag) {
        if (dialog != null) {
            dialog.setCancelable(flag);
        }
    }

    /**
     * 是否可以取消对话框，默认可以
     *
     * @param flag
     */
    public void setDismissable(boolean flag) {
        if (dialog != null) {
            mDismissable = flag;
        }
    }

    public DialogViewer show() {
        //预防遇到android.view.WindowManager$BadTokenException: Unable to add window -- token android.os.BinderProxy@42e9fd40 is not valid; is your activity running?
        try {
            if (dialog != null && !dialog.isShowing()) {
                dialog.show();
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        return null;
    }

    public void dismiss() {
        //预防遇到android.view.WindowManager$BadTokenException: Unable to add window -- token android.os.BinderProxy@42e9fd40 is not valid; is your activity running?
        try {
            if (mDismissable && dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public boolean isShowing() {
        if (dialog != null) {
            return dialog.isShowing();
        }
        return false;
    }

    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = (LinearLayout) inflater.inflate(R.layout.dialog_layout, null);

        dialog = new Dialog(context, R.style.dialog);
        dialog.getWindow().setContentView(dialogView);

        //对title不为空，content为空的情况做兼容。标题与内容显示方式不一样
        if (!Utils.isNull(title) && Utils.isNull(content)) {
            content = title;
            title = null;
        }

        initTitle();
        initContent();
        initButton();
    }


    private void initTitle() {
        //dialog标题栏
        titleLayout = dialog.findViewById(R.id.title_layout);
        titleLayout.setVisibility(View.GONE);

        titleTextView = (TextView) dialog.findViewById(R.id.title_text);
        if (!Utils.isNull(title)) {
            titleLayout.setVisibility(View.VISIBLE);
            titleTextView.setText(title);
            titleTextView.getPaint().setFakeBoldText(true);

            //有标题没内容，marginTop与marginBottom需要增大
            if (Utils.isNull(content) && contentSpanned == null) {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) titleLayout.getLayoutParams();
                if (params != null) {
                    int top = Utils.dp2px(context, 20);
                    int bottom = Utils.dp2px(context, 20);
                    params.topMargin = top;
                    params.bottomMargin = bottom;
                    titleLayout.setLayoutParams(params);
                }
            }
        }
    }

    /**
     * 设置标题（title）是否粗体
     * @param boldText
     */
    public void setTitleViewBoldText(boolean boldText) {
        titleTextView.getPaint().setFakeBoldText(boldText);
    }

    /**
     * 内容置中～
     */
    public void setContentCenter() {
        contentView.setGravity(Gravity.CENTER_HORIZONTAL);
    }

    private void initContent() {
        //dialog内容
        contentView = (TextView) dialog.findViewById(R.id.content_view);
        contentView.setVisibility(View.GONE);
        if (!Utils.isNull(content) || contentSpanned != null) {
            contentView.setVisibility(View.VISIBLE);

            if (contentSpanned != null) {
                contentView.setText(contentSpanned);
            }

            if (!Utils.isNull(content)) {
                contentView.setText(content);
            }

            //有内容没有标题
            if (Utils.isNull(title)) {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) contentView.getLayoutParams();
                if (params != null) {
                    int top = Utils.dp2px(context, 20);
                    int bottom = Utils.dp2px(context, 20);
                    params.topMargin = top;
                    params.bottomMargin = bottom;
                    contentView.setLayoutParams(params);
                }

                contentView.setGravity(Gravity.CENTER_HORIZONTAL);
            }
//            contentImg = (ImageView)dialog.findViewById(R.id.content_img);
//            contentImg.setVisibility(View.GONE);
//            if(resId != 0){
//                contentImg.setVisibility(View.VISIBLE);
//                contentImg.setImageResource(resId);
//            }
        }
    }

    private void initViewContent(){
        if(customView != null){
            LinearLayout container_view = (LinearLayout) dialog.findViewById(R.id.container_view);
            container_view.removeAllViews();
            container_view.addView(customView);
        }
    }

    private void initButton() {
        multiView = dialog.findViewById(R.id.multi_button);
        leftButtonView = (Button) dialog.findViewById(R.id.left_button);
        if (leftButtonText != null) {
            leftButtonView.setText(leftButtonText);

//            if(isLeftActive){
//                leftButtonView.setTextColor(context.getResources().getColor(R.color.app_text_red));
//            }else{
//                leftButtonView.setTextColor(context.getResources().getColor(R.color.app_text_black));
//            }

            leftButtonView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialogListener != null) {
                        dialogListener.onDialogClick(dialog, true, false);
                    }
                    dismiss();
                }
            });
        }

        rightButtonView = (Button) dialog.findViewById(R.id.right_button);
        if (rightButtonText != null) {
            rightButtonView.setText(rightButtonText);
//            if(isRightActive){
//                rightButtonView.setTextColor(context.getResources().getColor(R.color.app_text_red));
//            }else{
//                rightButtonView.setTextColor(context.getResources().getColor(R.color.app_text_black));
//            }

            rightButtonView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialogListener != null) {
                        dialogListener.onDialogClick(dialog, false, true);
                    }
                    dismiss();
                }
            });
        }

        oneView = dialog.findViewById(R.id.one_button);
        submitButtonView = (Button) dialog.findViewById(R.id.submit_button);
        if (submitButtonText != null) {
            multiView.setVisibility(View.GONE);
            oneView.setVisibility(View.VISIBLE);
            submitButtonView.setText(submitButtonText);

            submitButtonView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    if (dialogListener != null) {
                        dialogListener.onDialogClick(dialog, true, true);
                    }
                }
            });
        }

    }

    /**
     * 某些特殊需求，内容区域可点击，故提供此方法
     *
     * @return
     */
    public View getContentView() {
        return this.contentView;
    }


    /**
     * 开放设置标题文本接口
     */
    public void setTitleTextView(String title) {
        titleTextView.setText(title);
    }

    /**
     * 开放设置左边按钮文本接口
     *
     * @param leftButtonText
     */
    public void setLeftButtonText(String leftButtonText) {
        leftButtonView.setText(leftButtonText);
    }


    /**
     * 设置监听器
     *
     * @param listener
     */
    public void setDialogListener(DialogListener listener) {
        this.dialogListener = listener;
    }
}
