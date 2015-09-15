package young.com.slidemenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import young.com.slidemenu.view.SlideMenu;

public class MainActivity extends Activity {
    private ImageView backMain;
    private SlideMenu slideMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        backMain = (ImageView)findViewById(R.id.backMain);
        slideMenu = (SlideMenu)findViewById(R.id.slideMenu);
        backMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideMenu.swithMenu();
            }
        });
    }


}
