package yw.cn.chat.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import yw.cn.chat.R;
import yw.cn.chat.base.BaseActivity;
import yw.cn.chat.fragment.LogoFra;
import yw.cn.chat.fragment.SignInFra;

/**
 * Created by Administrator on 2015-09-09.
 */
public class LoginActivity extends BaseActivity {
    public static final String TAG_LOGO = "logo";
    public static final String TAG_SIGN_IN = "sign_in";
    public static final String TAG_SIGN_UP = "sign_up";
    public static final String TAG_FILL_INFO = "fill_info";

    private FragmentManager fragmentManager;
    private Fragment currentFra;
    private String currentTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        currentTag = TAG_LOGO;
        currentFra = new LogoFra();
        transaction.replace(R.id.login_container,currentFra, currentTag);
        transaction.addToBackStack(currentTag);
        transaction.commit();
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    public void signIn() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        currentTag = TAG_SIGN_IN;
        currentFra = new SignInFra();
        transaction.replace(R.id.login_container,currentFra, currentTag);
        transaction.addToBackStack(currentTag);
        transaction.commit();
    }
}
