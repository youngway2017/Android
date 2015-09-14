package yw.cn.chat.utils;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;

import java.util.List;

/**
 * Created by Administrator on 2015-09-14.
 */
public class CommonUtil {

    public static boolean isServiceRunning(Context context,Class<? extends Service> clazz) {
        ActivityManager manager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> services = manager.getRunningServices(100);
        for (int i = 0;i<services.size();i++) {
            String className = services.get(i).service.getClassName();
            if(className.equals(clazz.getName())) {
                return true;
            }
        }
        return false;
    }
}
