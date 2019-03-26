package cool.weiboproject.android.base;

import android.app.Activity;
import android.support.multidex.MultiDexApplication;

import com.blankj.utilcode.util.Utils;

import cool.weiboproject.android.utils.ToastHelper;
import cool.weiboproject.android.utils.WeiBoDaoUtils;


public class CCApplication extends MultiDexApplication {

    private static final String TAG = CCApplication.class.getSimpleName();

    private static CCApplication INSTANCE;
    private volatile Activity mCurrentActivity;
    private boolean mIsInForeground = false;

    public static CCApplication getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        Utils.init(this);
        ToastHelper.init(this);
        WeiBoDaoUtils.Init(this);
    }
}