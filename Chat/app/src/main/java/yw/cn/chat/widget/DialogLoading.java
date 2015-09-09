package yw.cn.chat.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;

import yw.cn.chat.R;


/**
 * Created by Administrator on 2015-09-09.
 */
public class DialogLoading extends Dialog {

    public DialogLoading(Context context) {
        super(context, R.style.dialog_logout);
        setCanceledOnTouchOutside(false);
        setCancelable(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        setContentView(R.layout.dialog_loading);
    }

}