package young.com.customview_youkumenu;

import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

/**
 * Created by yw on 2015/9/10.
 */
public class AnimUtils {
    public static int animCount = 0;
    public static void closeMenu(RelativeLayout rl,long startOffSet) {
        for (int i = 0;i<rl.getChildCount();i++) {
            rl.getChildAt(i).setEnabled(false);
        }
        RotateAnimation rotateAnimation = new RotateAnimation(0,-180, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,1);
        rotateAnimation.setDuration(500);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setAnimationListener(new AnimCountListener());
        rotateAnimation.setStartOffset(startOffSet);
        rl.startAnimation(rotateAnimation);
    }

    public static void showMenu(RelativeLayout rl,long startOffSet) {
        for (int i = 0;i<rl.getChildCount();i++) {
            rl.getChildAt(i).setEnabled(true);
        }
        RotateAnimation rotateAnimation = new RotateAnimation(-180,0, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,1);
        rotateAnimation.setDuration(500);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setAnimationListener(new AnimCountListener());
        rotateAnimation.setStartOffset(startOffSet);
        rl.startAnimation(rotateAnimation);
    }

    static class AnimCountListener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
            animCount++;
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            animCount--;
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
