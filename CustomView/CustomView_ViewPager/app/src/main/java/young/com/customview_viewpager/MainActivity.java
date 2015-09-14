package young.com.customview_viewpager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    LinearLayout ll_dot;
    List<AdInfo> ads = new ArrayList<AdInfo>();
    private TextView desc;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            handler.sendEmptyMessageDelayed(0,4000);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initListener();
        updateDesc(viewPager.getCurrentItem());
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        desc = (TextView)findViewById(R.id.desc);
        ll_dot = (LinearLayout)findViewById(R.id.ll_dot);


    }
    private void initDot(LinearLayout ll) {
        for (int i = 0;i<ads.size();i++) {
            View view = new View(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
            params.setMargins(5, 5, 5, 5);
            view.setLayoutParams(params);
            view.setBackgroundResource(R.drawable.dot_selector);
            ll.addView(view);
        }

    }
    private void initListener() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                updateDesc(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void updateDesc(int position) {
        AdInfo adInfo = ads.get(position%ads.size());
        desc.setText(adInfo.getDesc());
        for (int i = 0;i<ll_dot.getChildCount();i++) {
            View view = ll_dot.getChildAt(i);
            view.setEnabled(i == (position%ads.size()));
        }
    }
    private void initData() {
        ads.add(new AdInfo(R.drawable.a,"巩丽不低俗，我就不低俗"));
        ads.add(new AdInfo(R.drawable.b,"我爱玩大咖"));
        ads.add(new AdInfo(R.drawable.c,"电影黑板报"));
        ads.add(new AdInfo(R.drawable.d, "乐视网TV版大放送"));
        ads.add(new AdInfo(R.drawable.e, "劳动节"));
        initDot(ll_dot);
        viewPager.setAdapter(new MyPagerAdapter());
        int item = Integer.MAX_VALUE / 2;
        int value = item%ads.size();
        viewPager.setCurrentItem(item - value);

        handler.sendEmptyMessageDelayed(0,4000);
    }

    class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = (View)LayoutInflater.from(MainActivity.this).inflate(R.layout.ads_item,container,false);
            ImageView image = (ImageView)view.findViewById(R.id.iv_image);
            AdInfo adInfo = ads.get(position%ads.size());
            image.setImageResource(adInfo.getImageId());
            container.addView(view);
            return view;
            //            return super.instantiateItem(container, position);
        }
    }
}
