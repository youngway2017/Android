package yw.cn.chat.base;

import android.app.Service;

import yw.cn.chat.ChatApplication;

/**
 * Created by Administrator on 2015-09-14.
 */
public abstract class BaseService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        ((ChatApplication)getApplication()).addService(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((ChatApplication)getApplication()).removeService(this);
    }
}
