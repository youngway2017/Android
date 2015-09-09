package yw.cn.chat.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2015-09-09.
 */
public class ToastUtil {

    public static void show(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    public static void show(Context context, int contentId) {
        Toast.makeText(context, contentId, Toast.LENGTH_SHORT).show();
    }
}