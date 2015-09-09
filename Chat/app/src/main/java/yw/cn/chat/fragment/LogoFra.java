package yw.cn.chat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yw.cn.chat.R;
import yw.cn.chat.activity.LoginActivity;

/**
 * Created by Administrator on 2015-09-09.
 */
public class LogoFra extends Fragment implements View.OnClickListener {

    private View btnSignIn;
    private View btnSignUp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.frag_logo,container,false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        btnSignIn = v.findViewById(R.id.btn_sign_in);
        btnSignUp = v.findViewById(R.id.btn_sign_up);
    }

    private void initEvent() {
        btnSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnSignIn) {
            signIn();
        }
        if (view == btnSignUp) {
            signUp();
        }
    }

    private void signUp() {
        FragmentActivity activity = this.getActivity();
        if (activity != null) {
            ((LoginActivity)activity).signIn();
        }
    }

    private void signIn() {

    }
}
