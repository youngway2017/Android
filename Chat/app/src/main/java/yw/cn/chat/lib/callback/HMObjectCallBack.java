package yw.cn.chat.lib.callback;

import java.lang.reflect.ParameterizedType;

/**
 * Created by Administrator on 2015-09-10.
 */
public abstract class HMObjectCallBack<T> {
    private Class<T> clazz;

    public HMObjectCallBack() {
        ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
        this.clazz = (Class<T>)type.getActualTypeArguments()[0];
    }

    public abstract void onSuccess(T t);

    public abstract void onError(int error,String msg);

    public Class<T> getClazz() {
        return clazz;
    }
}
