package young.com.customview_youkumenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    RelativeLayout level1,level2,level3;
    private boolean isShowLevel2,isShowLevel3;
    private boolean isShowMenu = true;
    ImageView iv_home,iv_menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initEvent();
    }

    public void initView() {
        isShowLevel2 = true;
        isShowLevel3 = true;
        setContentView(R.layout.activity_main);
        level1 = (RelativeLayout)findViewById(R.id.level1);
        level2 = (RelativeLayout)findViewById(R.id.level2);
        level3 = (RelativeLayout)findViewById(R.id.level3);

        iv_home = (ImageView)findViewById(R.id.iv_home);
        iv_menu = (ImageView)findViewById(R.id.iv_menu);

    }

    public void initEvent() {
        iv_home.setOnClickListener(this);
        iv_menu.setOnClickListener(this);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            if (AnimUtils.animCount != 0 ){
                return true;
            }
            int startOffSet = 0;
            if (isShowMenu) {
                if (isShowLevel3) {
                    AnimUtils.closeMenu(level3,startOffSet);
                    startOffSet += 300;
                    isShowLevel3 = !isShowLevel3;
                }
                if (isShowLevel2) {
                    AnimUtils.closeMenu(level2,startOffSet);
                    startOffSet += 300;
                    isShowLevel2 = !isShowLevel2;
                }
                AnimUtils.closeMenu(level1,startOffSet);

            } else {
                startOffSet = 0;
                AnimUtils.showMenu(level1, startOffSet);
                if (!isShowLevel2) {
                    startOffSet += 300;
                    AnimUtils.showMenu(level2, startOffSet);
                    isShowLevel2 = !isShowLevel2;
                }

                if (!isShowLevel3) {
                    startOffSet += 300;
                    AnimUtils.showMenu(level3,startOffSet);
                    isShowLevel3 = !isShowLevel3;
                }
            }
            isShowMenu = !isShowMenu;
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        if (AnimUtils.animCount != 0 ){
            return;
        }
        switch (v.getId()) {
            case R.id.iv_home:
                int startOffSet = 0;
                if (isShowLevel2) {
                    if (isShowLevel3) {
                        AnimUtils.closeMenu(level3,startOffSet);
                        isShowLevel3 = !isShowLevel3;
                        startOffSet +=300;
                    }
                        AnimUtils.closeMenu(level2,startOffSet);

                } else {
                    AnimUtils.showMenu(level2,0);
                    if (!isShowLevel3) {
                        AnimUtils.showMenu(level3,500);
                        isShowLevel3 = !isShowLevel3;
                    }
                }
                isShowLevel2 = !isShowLevel2;
                break;
            case R.id.iv_menu:
                if (isShowLevel3) {
                    AnimUtils.closeMenu(level3,0);
                } else {
                    AnimUtils.showMenu(level3,0);
                }
                isShowLevel3 = !isShowLevel3;
                break;
        }
    }
}
