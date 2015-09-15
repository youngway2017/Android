package young.com.slidemenu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Scroller;

/**
 * Created by Administrator on 2015-09-15.
 */
public class SlideMenu extends ViewGroup {

    private Scroller scroller;
    private View menuView, mainView;
    private int menuViewWidth = 0;

    private int downX;

    public SlideMenu(Context context) {
        super(context);
        scroller = new Scroller(getContext());

    }

    public SlideMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(getContext());
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
        mainView.measure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        menuView.layout(-menuViewWidth, 0, 0, b);
        mainView.layout(l, t, r, b);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) event.getX();
                int deltaX = (int) (moveX - downX);

                int newScrollX = getScrollX() - deltaX;

                //                Log.i("youngway","newScrollX-->" + "" + newScrollX);
                if (newScrollX < -menuViewWidth) {
                    newScrollX = -menuViewWidth;
                }

                if (newScrollX > 0) {
                    newScrollX = 0;
                }
                Log.i("youngway", "deltaX-->" + "" + deltaX);
                scrollTo(newScrollX, 0);
                //                Log.i("youngway", "getScrollX()-->" + "" + getScrollX());
                downX = moveX;
                break;
            case MotionEvent.ACTION_UP:

                //                if (getScrollX() < -menuViewWidth/2 ) {
                //                    ScrollAnim anim = new ScrollAnim(this,-menuViewWidth);
                //                    startAnimation(anim);
                ////                    scrollTo(-menuViewWidth,0);
                //                } else {
                ////                    scrollTo(0,0);
                //                    ScrollAnim anim = new ScrollAnim(this,0);
                //                    startAnimation(anim);
                //                }

                if (getScrollX() < -menuViewWidth / 2) {
                    scroller.startScroll(getScrollX(), 0, -menuViewWidth - getScrollX(), 0, 400);
                    invalidate();
//                    scrollTo(-menuViewWidth, 0);
                } else {
//                    scrollTo(0, 0);
                    scroller.startScroll(getScrollX(),0,-getScrollX(),0,400);
                    invalidate();
                }

                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(),0);
            invalidate();
        }

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) ev.getX();
                int deltaX = (int) (moveX - downX);
                if (Math.abs(deltaX) > 8) return true;
                break;
            case MotionEvent.ACTION_UP:

                if (getScrollX() < -menuViewWidth / 2) {
                    scroller.startScroll(getScrollX(), 0, -menuViewWidth - getScrollX(), 0, 400);
                    invalidate();
                    //                    scrollTo(-menuViewWidth, 0);
                } else {
                    //                    scrollTo(0, 0);
                    scroller.startScroll(getScrollX(), 0, -getScrollX(), 0, 400);
                    invalidate();
                }

                break;
        }
        return false;
    }

    public void swithMenu() {
        if (getScrollX() == 0) {
            scroller.startScroll(getScrollX(), 0, -menuViewWidth - getScrollX(), 0, 400);
            invalidate();
        } else {
            scroller.startScroll(getScrollX(), 0, -getScrollX(), 0, 400);
            invalidate();
        }
    }

    class ScrollAnim extends Animation {
        private View view;
        private int targetX;
        private int startX;
        private int totleX;

        public ScrollAnim(View v, int targetX) {
            this.view = v;
            this.targetX = targetX;
            startX = v.getScrollX();
            totleX = targetX - startX;
            int time = Math.abs(totleX);
            setDuration(totleX * 2);
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            int currentX = (int) (startX + totleX * interpolatedTime);
            view.scrollTo(currentX, 0);
        }
    }


}
