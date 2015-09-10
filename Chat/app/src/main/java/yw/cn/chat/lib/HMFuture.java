package yw.cn.chat.lib;

/**
 * Created by Administrator on 2015-09-10.
 */
public interface HMFuture {
    boolean isCancelled();
    boolean cancel(boolean mayInterruptIfRunning);
    boolean isFinished();
}
