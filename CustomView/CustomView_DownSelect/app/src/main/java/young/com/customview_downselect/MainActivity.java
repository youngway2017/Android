package young.com.customview_downselect;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView xiala;
    private ArrayList<String> colors;
    private ListView listView;
    private EditText et_colors;
    private PopupWindow popupWindow;
    private boolean isShowPopupWindow = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initView();
        initData();
        initListener();
    }



    private void initView() {
        xiala = (ImageView) findViewById(R.id.xiala);
        et_colors = (EditText) findViewById(R.id.et_colors);
        listView = new ListView(this);
        listView.setBackgroundResource(R.drawable.edit_backgroud);
        listView.setVerticalScrollBarEnabled(false);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                et_colors.setText(colors.get(position));
            }
        });
    }

    private void initData() {
        colors = new ArrayList<String>();
        for (int i = 0;i < 10; i++) {
            colors.add("颜色2200115"+i);
        }

        ColorAdapter adapter = new ColorAdapter();
        listView.setAdapter(adapter);
    }

    private void initListener() {
        xiala.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == xiala) {
            showPopupWindow();
        }
    }

    public void showPopupWindow() {
        if (popupWindow == null) {
            popupWindow = new PopupWindow(listView,et_colors.getWidth(),400);
            popupWindow.setFocusable(true);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            popupWindow.setOutsideTouchable(true);
        }
        if (!isShowPopupWindow) {
            popupWindow.showAsDropDown(et_colors, 0, 0);
        } else {
            popupWindow.dismiss();
        }

        isShowPopupWindow = !isShowPopupWindow;
    }

    class ColorAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return colors.size();
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            final View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.color_item, null);
            TextView item_tv = (TextView)view.findViewById(R.id.item_tv);
            item_tv.setText(colors.get(position));

            ImageView delete = (ImageView)view.findViewById(R.id.delete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    colors.remove(position);
                    notifyDataSetChanged();

                    int height = view.getHeight()*colors.size();

                    popupWindow.update(et_colors.getWidth(),height > popupWindow.getHeight()
                            ? popupWindow.getHeight():height);

                    if (colors.size() == 0) {
                        popupWindow.dismiss();
                        xiala.setVisibility(View.GONE);
                    }
                }
            });
            return view;
        }
    }
}






