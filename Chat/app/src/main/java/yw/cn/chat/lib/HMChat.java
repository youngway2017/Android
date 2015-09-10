package yw.cn.chat.lib;

import android.content.Context;

/**
 * Created by Administrator on 2015-09-10.
 */
public class HMChat {
    public static HMChat instance;
    public static Context context;
    public static HMChat getInstance() {
        if (instance == null) {
            synchronized (HMChat.class) {
                if (instance == null) {
                    instance = new HMChat();
                }
            }
        }
        return instance;
    }

    private HMChat() {

    }

    public static Context getContext() {
        if (HMChat.context == null) {
            throw new RuntimeException(
                    "请在Application的onCreate方法中调用HMChat.getInstance().setContext(context)初始化聊天引擎.");
        }
        return HMChat.context;
    }

    public static void setContext(Context context) {
        HMChat.context = context;
    }
}
