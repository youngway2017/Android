package yw.cn.chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RadioButton rbchat;
    RadioButton rbchat1;
    RadioButton rbchat2;
    RadioButton rbchat3;

    TextView tvchat;
    TextView tvchat1;
    TextView tvchat2;
    TextView tvchat3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setDefaultState();
        rbchat.setChecked(true);
    }

    public void init() {
        rbchat = (RadioButton)findViewById(R.id.chat);
        rbchat1 = (RadioButton)findViewById(R.id.chat1);
        rbchat2 = (RadioButton)findViewById(R.id.chat2);
        rbchat3 = (RadioButton)findViewById(R.id.chat3);

        tvchat = (TextView)findViewById(R.id.tvchat);
        tvchat1 = (TextView)findViewById(R.id.tvchat1);
        tvchat2 = (TextView)findViewById(R.id.tvchat2);
        tvchat3 = (TextView)findViewById(R.id.tvchat3);

        rbchat.setOnClickListener(new RadioOnClickListener());
        rbchat1.setOnClickListener(new RadioOnClickListener());
        rbchat2.setOnClickListener(new RadioOnClickListener());
        rbchat3.setOnClickListener(new RadioOnClickListener());
    }

    public void setDefaultState() {
        rbchat.setChecked(false);
        rbchat1.setChecked(false);
        rbchat2.setChecked(false);
        rbchat3.setChecked(false);
    }

    public class RadioOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            setDefaultState();
            switch (v.getId()) {
                case R.id.chat:
                    rbchat.setChecked(true);
                    break;
                case R.id.chat1:
                    rbchat1.setChecked(true);
                    break;
                case R.id.chat2:
                    rbchat2.setChecked(true);
                    break;
                case R.id.chat3:
                    rbchat3.setChecked(true);
                    break;
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
