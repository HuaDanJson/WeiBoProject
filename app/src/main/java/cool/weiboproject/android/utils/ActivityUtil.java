package cool.weiboproject.android.utils;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

import cn.bmob.v3.BmobUser;
import cool.weiboproject.android.R;
import cool.weiboproject.android.activity.LoginActivity;
import cool.weiboproject.android.activity.ReaderActivity;
import cool.weiboproject.android.base.CCApplication;
import cool.weiboproject.android.bean.WeiBoBean;
import cool.weiboproject.android.constants.AppConstant;


public class ActivityUtil {

    public static boolean isFinishing(Activity activity) {
        return (activity == null || activity.isFinishing());
    }

    public static void startActivity(Activity activity, Class targetClass) {
        Intent intent = new Intent(activity, targetClass);
        activity.startActivity(intent);
    }

    public static void startReaderActivity(Fragment fragment, WeiBoBean weiBoBean) {
        if (fragment == null || fragment.getActivity() == null) { return; }
        Intent intent = new Intent(fragment.getActivity(), ReaderActivity.class);
        intent.putExtra(AppConstant.IntentKey.EXTRA_DATA, weiBoBean);
        fragment.startActivity(intent);
        fragment.getActivity().overridePendingTransition(R.anim.enter_from_right, R.anim.slide_in_from_middle_to_middle);
    }

    public static void startLoginActivity(Activity activity) {
        BmobUser.logOut();
        CurrentUserHelper.getInstance().updateCurrentUser(null);
        if (activity == null) {
            Intent intent = new Intent(CCApplication.getInstance(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            CCApplication.getInstance().startActivity(intent);
        } else {
            Intent intent = new Intent(activity, LoginActivity.class);
            activity.startActivity(intent);
            activity.finish();
        }
    }
}
