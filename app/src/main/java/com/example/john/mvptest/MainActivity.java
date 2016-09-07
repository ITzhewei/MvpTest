package com.example.john.mvptest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.john.mvptest.bean.User;
import com.example.john.mvptest.presenter.UserLoginPresenter;

/**
 * 这是MVP中的 V --->只用来展示页面
 */
public class MainActivity extends AppCompatActivity {

    private EditText et_userName;
    private EditText et_passWord;
    private Button btn_login;
    private ProgressDialog mDialog;
    private UserLoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_login = (Button) findViewById(R.id.btn_login);
        et_userName = (EditText) findViewById(R.id.et_userName);
        et_passWord = (EditText) findViewById(R.id.et_passWord);
        mDialog = new ProgressDialog(this);
        mPresenter = new UserLoginPresenter(this);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.show();
                User user = new User();
                user.userName = et_userName.getText().toString().trim();
                user.passWord = et_passWord.getText().toString().trim();
                boolean userInfo = mPresenter.checkUserInfo(user);
                if (userInfo) {
                    mPresenter.login(user);
                } else {
                    Toast.makeText(getApplicationContext(), "请重新输入", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 登陆成功
     */
    public void success() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mDialog.dismiss();
                Toast.makeText(getApplicationContext(), "成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 登录失败
     */
    public void failed() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mDialog.dismiss();
                Toast.makeText(getApplicationContext(), "登录失败,密码不正确", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
