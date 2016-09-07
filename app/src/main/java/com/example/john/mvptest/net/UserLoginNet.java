package com.example.john.mvptest.net;

import android.os.SystemClock;

import com.example.john.mvptest.bean.User;

/**
 * Created by ZheWei on 2016/9/7.
 * 用户登录网络数据请求--->M
 */
public class UserLoginNet {


    public boolean sendUrlLoginInfo(User user) {
        SystemClock.sleep(2000);
        if ("john".equals(user.userName) && "456".equals(user.passWord)) {
            return true;
        } else {
            return false;
        }
//        return ("john".equals(user.userName) && "456".equals(user.passWord));
    }
}
