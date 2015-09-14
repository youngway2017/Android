package yw.cn.chat;

import android.app.Activity;
import android.app.Application;
import android.app.Service;

import java.util.LinkedList;
import java.util.List;

import yw.cn.chat.lib.HMChat;

/**
 * Created by Administrator on 2015-09-08.
 */
public class ChatApplication extends Application {
    public static final String TAG_LOG = "young";

    private List<Activity> activities = new LinkedList<Activity>();
    private List<Service> services = new LinkedList<Service>();
    @Override
    public void onCreate() {
        super.onCreate();
        HMChat.getInstance().setContext(this);
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public void addService(Service service) {
        services.add(service);
    }

    public void removeService(Service service) {
        services.remove(service);
    }
}
