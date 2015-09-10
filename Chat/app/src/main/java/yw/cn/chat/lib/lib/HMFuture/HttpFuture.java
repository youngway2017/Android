package yw.cn.chat.lib.lib.HMFuture;

import com.loopj.android.http.RequestHandle;

import yw.cn.chat.lib.HMFuture;

/**
 * Created by Administrator on 2015-09-10.
 */
public class HttpFuture implements HMFuture {

    private RequestHandle handle;

    public HttpFuture(RequestHandle handle) {
        this.handle = handle;
    }

    @Override
    public boolean isCancelled() {
        return handle == null || handle.isCancelled();
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return handle == null || handle.cancel(mayInterruptIfRunning);
    }

    @Override
    public boolean isFinished() {
        return handle == null || handle.isFinished();
    }
}
