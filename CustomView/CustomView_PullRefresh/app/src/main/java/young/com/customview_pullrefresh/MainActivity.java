package young.com.customview_pullrefresh;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {
    PullRefreshListView list;
    ArrayList<String> data = new ArrayList<String>();
    private int headerHeight;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            adapter.notifyDataSetChanged();
            list.completeRefresh();
        }
    };
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (PullRefreshListView) findViewById(R.id.list);

        initData();
    }

    private void initData() {
        for (int i = 0; i < 3; i++) {
            data.add("我是ListView --> " + i );
        }
//        final View v = LayoutInflater.from(this).inflate(R.layout.listview_head, null);
//        v.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                headerHeight = v.getHeight();
//                v.setPadding(0, -headerHeight, 0, 0);
//                list.addHeaderView(v);
//            }
//        });
//        v.measure(0, 0);
//        headerHeight = v.getMeasuredHeight();
//        v.setPadding(0, -headerHeight, 0, 0);
//        list.addHeaderView(v);
        adapter = new MyAdapter();
        list.setAdapter(adapter);
        list.setRefreshListenner(new PullRefreshListView.RefreshListener() {
            @Override
            public void refresh() {
                requestToServer();
            }

            @Override
            public void loadingMore() {
                requestToServerForFooter();
            }


        });
    }

    private void requestToServer() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                data.add(0,"我是ListView Refresh");
                handler.sendEmptyMessage(0);
            }
        }).start();
    }

    private void requestToServerForFooter() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                data.add("我是ListView LodingMore");
                handler.sendEmptyMessage(0);
            }
        }).start();
    }
    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = new TextView(MainActivity.this);
            tv.setTextSize(16);
            tv.setPadding(20, 20, 20, 20);
            tv.setText(data.get(position));
            return tv;
        }
    }
}
