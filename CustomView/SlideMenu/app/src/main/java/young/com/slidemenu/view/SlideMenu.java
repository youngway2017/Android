package young.com.slidemenu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2015-09-15.
 */
public class SlideMenu extends ViewGroup {


    private View menuView,mainView;
    private int menuViewWidth = 0;

    private int downX;
    public SlideMenu(Context context) {
        super(context);
    }

    public SlideMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        menuView = getChildAt(0);
        mainView = getChildAt(1);
        menuViewWidth = menuView.getLayoutParams().width;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        menuView.measure(widthMeasureSpec, heightMeasureSpec);
        mainView.measure(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        menuView.layout(-menuViewWidth,0,0,b);
        mainView.layout(l,t,r,b);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int)event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) event.getX();
                int deltaX = (int) ( moveX- downX);

                int newScrollX = getScrollX() - deltaX;

//                Log.i("youngway","newScrollX-->" + "" + newScrollX);
                if (newScrollX < -menuViewWidth) {
                    newScrollX = -menuViewWidth;
                }

                if (newScrollX > 0) {
                    newScrollX = 0;
                }
                Log.i("youngway", "deltaX-->" + "" + deltaX);
                scrollTo(newScrollX,0);
//                Log.i("youngway", "getScrollX()-->" + "" + getScrollX());
                downX = moveX;
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}
