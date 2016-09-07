package com.example.john.mvptest.presenter;

import android.text.TextUtils;

import com.example.john.mvptest.MainActivity;
import com.example.john.mvptest.bean.User;
import com.example.john.mvptest.net.UserLoginNet;

/**
 * Created by ZheWei on 2016/9/7.
 * 业务逻辑---->P
 */
public class UserLoginPresenter {
    private UserLogin view;

    public UserLoginPresenter(UserLogin view) {
        this.view = view;
    }

    /**
     * 检测是否为空
     */
    public boolean checkUserInfo(User user) {
        return !(TextUtils.isEmpty(user.userName) || TextUtils.isEmpty(user.passWord));
    }

    /**
     * 进行登录操作
     */
    public void login(final User user) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                UserLoginNet loginNet = new UserLoginNet();
                boolean b = loginNet.sendUrlLoginInfo(user);
                if (b) {
                    //登陆成功
                    view.success();
                } else {
                    //登录失败
                    view.failed();
                }
            }
        }).start();
    }


}
