package yw.cn.chat.service;

import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Map;

import yw.cn.chat.base.BaseService;
import yw.cn.chat.db.AccountDao;
import yw.cn.chat.lib.HMChatManager;

/**
 * Created by Administrator on 2015-09-14.
 */
public class ChatCoreService extends BaseService implements HMChatManager.HMConnectListener, HMChatManager.OnPushListener {

    private AccountDao accountDao;
    private HMChatManager chatManager;

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        accountDao = new AccountDao(this);
        chatManager = HMChatManager.getInstance();
        chatManager.addConnectionListener(this);
        chatManager.setPushListener(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onConnecting() {

    }

    @Override
    public void onConnected() {

    }

    @Override
    public void onDisconnected() {

    }

    @Override
    public void onReconnecting() {

    }

    @Override
    public void onAuthFailed() {

    }

    @Override
    public boolean onPush(String type, Map<String, Object> data) {
        return false;
    }
}
