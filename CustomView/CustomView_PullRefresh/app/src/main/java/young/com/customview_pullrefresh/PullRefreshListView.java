package young.com.customview_pullrefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yw on 2015/9/13.
 */
public class PullRefreshListView extends ListView implements AbsListView.OnScrollListener{

    private View header;
    private int headerHeight;
    private ImageView arrow;
    private ProgressBar progress;
    private int downY;
    private final int PULL_REFRESH = 0;
    private final int UP_REFRESH = 1;
    private final int REFRESHING = 2;
    private int currentState = PULL_REFRESH;
    private TextView title;
    private TextView time;
    Context context;
    private RotateAnimation rotateAnimation;
    private RotateAnimation rotateAnimation1;

    private boolean loadingMore = false;
    public RefreshListener mRefreshListener;
    private View footer;
    private int footerHeight;

    public PullRefreshListView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        initHeaderView(context);
        initFooter(context);
        setOnScrollListener(this);
    }

    public PullRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void initHeaderView(Context context) {
        this.context = context;
        header = LayoutInflater.from(context).inflate(R.layout.listview_head, null);
        title = (TextView) header.findViewById(R.id.headtitle);
        time = (TextView) header.findViewById(R.id.time);
        arrow = (ImageView) header.findViewById(R.id.arrow);
        progress = (ProgressBar) header.findViewById(R.id.progrssBar);
        progress.setVisibility(View.INVISIBLE);
        header.measure(0, 0);
        headerHeight = header.getMeasuredHeight();
        header.setPadding(0, -headerHeight, 0, 0);
        addHeaderView(header);
        initAnim();
    }

    private void initFooter(Context context) {
        this.context = context;
        footer = LayoutInflater.from(context).inflate(R.layout.layout_footer, null);
        footer.measure(0, 0);
        footerHeight = footer.getMeasuredHeight();
        footer.setPadding(0, -footerHeight, 0, 0);
        addFooterView(footer);
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (currentState == REFRESHING) {
                    break;
                }
                int deltaY = (int) ev.getY() - downY;
                int paddingTop = deltaY - headerHeight;

                if (paddingTop > -headerHeight && getFirstVisiblePosition() == 0) {
                    if (paddingTop <= 0 && currentState == UP_REFRESH) {
                        currentState = PULL_REFRESH;
                        refreshListView();
                    } else if (paddingTop > 0 && currentState == PULL_REFRESH) {
                        currentState = UP_REFRESH;
                        refreshListView();
                    }

                    header.setPadding(0, paddingTop, 0, 0);
                    return true;
                }

                break;
            case MotionEvent.ACTION_UP:
                if (currentState == PULL_REFRESH) {
                    header.setPadding(0, -headerHeight, 0, 0);
                } else if (currentState == UP_REFRESH) {
                    header.setPadding(0, 0, 0, 0);
                    currentState = REFRESHING;
                    refreshListView();
                    if (mRefreshListener != null) {
                        mRefreshListener.refresh();
                    }
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    public void refreshListView() {
        switch (currentState) {
            case PULL_REFRESH:
                title.setText("下拉刷新");
                arrow.startAnimation(rotateAnimation1);
                break;
            case UP_REFRESH:
                title.setText("松开刷新");
                arrow.clearAnimation();
                arrow.startAnimation(rotateAnimation);

                //                time.setText();
                break;
            case REFRESHING:
                title.setText("正在刷新...");
                arrow.clearAnimation();
                arrow.setVisibility(View.INVISIBLE);
                progress.setVisibility(View.VISIBLE);

                break;
        }
    }

    public static String getSystemTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        //获取当前时间
        return formatter.format(curDate);
    }

    public void completeRefresh() {
        if (loadingMore) {
            loadingMore = false;
            footer.setPadding(0,-footerHeight,0,0);
        } else {
            header.setPadding(0, -headerHeight, 0, 0);
            currentState = PULL_REFRESH;
            progress.setVisibility(View.INVISIBLE);
            arrow.setVisibility(VISIBLE);
            title.setText("下拉刷新");
            time.setText("最后更新:" + getSystemTime());
        }

    }

    public void initAnim() {
        rotateAnimation = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(300);
        rotateAnimation.setFillAfter(true);

        rotateAnimation1 = new RotateAnimation(-180, -360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation1.setDuration(300);
        rotateAnimation1.setFillAfter(true);

    }

    public void setRefreshListenner(RefreshListener listenner) {
        this.mRefreshListener = listenner;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (getLastVisiblePosition() == (getCount()-1)
                && scrollState == OnScrollListener.SCROLL_STATE_IDLE
                && !loadingMore) {
            footer.setPadding(0,0,0,0);
            loadingMore = true;
            setSelection(getCount());

            if (mRefreshListener != null) {
                mRefreshListener.loadingMore();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    public interface RefreshListener {
        public void refresh();

        public void loadingMore();
    }

}
