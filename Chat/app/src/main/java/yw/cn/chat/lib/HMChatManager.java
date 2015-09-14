package yw.cn.chat.lib;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import yw.cn.chat.lib.callback.HMObjectCallBack;
import yw.cn.chat.lib.lib.HMFuture.HttpFuture;

/**
 * Created by Administrator on 2015-09-10.
 */
public class HMChatManager {
    private Map<String,String> headers = new HashMap<String,String>();
    private List<HMConnectListener> connectListeners = new LinkedList<HMConnectListener>();
    private OnPushListener pushListener;
    private static HMChatManager instance;
    private Context context;
    private Thread mainThread;

    public static HMChatManager getInstance() {
        if (instance == null) {
            synchronized (HMChatManager.class) {
                if (instance == null) {
                    instance = new HMChatManager();
                }
            }
        }
        return  instance;
    }

    private HMChatManager() {
        this.context = HMChat.getContext();
        this.mainThread = Thread.currentThread();
    }

    /**
     * 初始化连接用户安全信息
     * @param account
     * @param token
     */
    public void initAccount(String account,String token) {
        headers.put("account",account);
        headers.put("token",token);
    }

    public HttpFuture register(String account,String password,HMObjectCallBack callBack) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.setTimeout(30 * 1000);
        client.setMaxRetriesAndTimeout(5, 30 * 1000);
        client.setResponseTimeout(30 * 1000);
        String url = HMURL.URL_HTTP_REGISTER;
        RequestParams requestParams = new RequestParams();
        requestParams.put("account",account);
        requestParams.put("password",password);

        return new HttpFuture(client.post(context, url, requestParams, newObjectResponseHandler(callBack)));
    }

    private TextHttpResponseHandler newObjectResponseHandler(final HMObjectCallBack callBack) {
        return new TextHttpResponseHandler("utf-8") {

            @Override
            public void onSuccess(int statusCode, org.apache.http.Header[] headers, String responseString) {
                Log.d("###", "" + responseString);
//              Json解析
//                {
//                    "flag": true,
//                        "data": {
//                            "account": "zhangsan",
//                            "name": "张三",
//                            "sex": 1,
//                            "icon": "/a/b/zhangsan.png",
//                            "sign": "我的个性签名",
//                            "area": "深圳",
//                            "token": "5904c7ae-3e75-48c8-bbee-ad094533a422"
//                }
//                }
                if (statusCode == 200) {
                    JsonParser parser = new JsonParser();
                    JsonElement parse = parser.parse(responseString);
                    JsonObject root = parse.getAsJsonObject();
                    if (root == null) {
                        if (callBack != null) {
                            callBack.onError(HMError.ERROR_SERVER, "服务器异常");
                        }
                    } else {
                        if (callBack != null) {
                            boolean flag = root.getAsJsonPrimitive("flag").getAsBoolean();
                            if (flag) {
                                JsonObject jsonData = root.getAsJsonObject("data");
                                if (jsonData == null) {
                                    callBack.onSuccess(null);
                                } else {
                                    Gson g = new Gson();
                                    Object data = g.fromJson(jsonData, callBack.getClazz());
                                    callBack.onSuccess(data);
                                }
                            } else {
                                // 如果返回错误
                                // 获得错误code
                                JsonPrimitive errorCodeObj = root
                                        .getAsJsonPrimitive("errorCode");
                                // 获得错误string
                                JsonPrimitive errorStringObj = root
                                        .getAsJsonPrimitive("errorString");

                                int errorCode = errorCodeObj.getAsInt();
                                String errorString = errorStringObj
                                        .getAsString();

                                callBack.onError(errorCode, errorString);
                            }
                        }
                    }
                } else {
                    if (callBack != null) {
                        callBack.onError(HMError.ERROR_SERVER, "服务器异常");
                    }
                }

            }

            @Override
            public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                if (callBack != null) {
                    callBack.onError(HMError.ERROR_SERVER, "服务器异常 : "
                            + throwable.getMessage());
                }
            }
        };
    }

    /**
     * 添加连接监听
     *
     * @param listener
     */
    public void addConnectionListener(HMConnectListener listener) {
        if (!connectListeners.contains(listener)) {
            connectListeners.add(listener);
        }
    }

    /**
     * 移除连接监听
     *
     * @param listener
     */
    public void removeConnectionListener(HMConnectListener listener) {
        if (connectListeners.contains(listener)) {
            connectListeners.remove(listener);
        }
    }

    /**
     * 添加消息推送监听
     *
     * @param listener
     */
    public void setPushListener(OnPushListener listener) {
        this.pushListener = listener;
    }

    public interface HMConnectListener {
        /**
         * 正在连接
         */
        void onConnecting();

        /**
         * 已经连接
         */
        void onConnected();

        /**
         * 已经断开连接
         */
        void onDisconnected();

        /**
         * 正在重试连接
         */
        void onReconnecting();

        /**
         * 用户认证失败
         */
        void onAuthFailed();
    }

    public interface OnPushListener {
        boolean onPush(String type, Map<String, Object> data);
    }
}
