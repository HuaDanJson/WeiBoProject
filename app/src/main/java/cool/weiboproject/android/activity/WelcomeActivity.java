package cool.weiboproject.android.activity;

import android.os.Bundle;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cool.weiboproject.android.R;
import cool.weiboproject.android.base.BaseActivity;
import cool.weiboproject.android.bean.CurrentUser;
import cool.weiboproject.android.utils.CurrentUserHelper;


public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //第一：默认初始化
        Bmob.initialize(this, "361b8d2729d5873bcc087cb265399155");
        if (BmobUser.isLogin()) {
            CurrentUser currentUser = BmobUser.getCurrentUser(CurrentUser.class);
            CurrentUserHelper.getInstance().updateCurrentUser(currentUser);
            toActivity(MainActivity.class);
        } else {
            toActivity(LoginActivity.class);
            finish();
        }
    }
}
