package young.com.customview_togglebutton;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by yw on 2015/9/12.
 */
public class ToggleButton extends View {
    private Bitmap swichBackground;
    private Bitmap slideBackground;
    private ToggleState state;
    private float currentX;
    private boolean isSliding = false;

    public OnStateChangeListener mStateListener;

    public ToggleButton(Context context) {
        super(context);
    }

    public enum ToggleState {
        Open, Close;
    }

    public ToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setSwichBackgroundFormSourceID(int id) {
        swichBackground = BitmapFactory.decodeResource(getResources(), id);
    }

    public void setSlideBackgroundFormSourceID(int id) {
        slideBackground = BitmapFactory.decodeResource(getResources(), id);
    }

    public void setState(ToggleState state) {
        this.state = state;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(swichBackground.getWidth(), swichBackground.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(swichBackground, 0, 0, null);

        if (isSliding) {
            canvas.drawBitmap(slideBackground, currentX, 0, null);
        } else {
            if (state == ToggleState.Open) {
                float x = swichBackground.getWidth() - slideBackground.getWidth();
                canvas.drawBitmap(slideBackground, x, 0, null);

            } else {
                canvas.drawBitmap(slideBackground, 0, 0, null);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
                isSliding = true;
                currentX = event.getX() - slideBackground.getWidth() / 2;
                if (currentX <= 0) {
                    currentX = 0;
                }
                if (currentX >= swichBackground.getWidth() - slideBackground.getWidth()) {
                    currentX = swichBackground.getWidth() - slideBackground.getWidth();
                }
                break;
            case MotionEvent.ACTION_UP:
                isSliding = false;
                if (currentX < (swichBackground.getWidth() - slideBackground.getWidth()) / 2) {
                    currentX = 0;
                    state = ToggleState.Close;
                } else {
                    currentX = swichBackground.getWidth() - slideBackground.getWidth();
                    state = ToggleState.Open;
                }
                mStateListener.onStateChange(state);
                break;
        }
        invalidate();
        return true;

    }

    public void setOnStateChangeListener(OnStateChangeListener listener) {
        mStateListener = listener;
    }

    public interface OnStateChangeListener {
        public void onStateChange(ToggleState state);
    }
}
