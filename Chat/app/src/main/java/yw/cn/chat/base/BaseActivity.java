package yw.cn.chat.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;

import yw.cn.chat.ChatApplication;

/**
 * Created by Administrator on 2015-09-08.
 */
public class BaseActivity extends FragmentActivity{
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        ((ChatApplication)getApplication()).addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((ChatApplication)getApplication()).removeActivity(this);
    }
}
