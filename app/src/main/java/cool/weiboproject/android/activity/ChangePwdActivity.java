package cool.weiboproject.android.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cool.weiboproject.android.R;
import cool.weiboproject.android.base.BaseActivity;
import cool.weiboproject.android.utils.ToastHelper;


public class ChangePwdActivity extends BaseActivity {

    @BindView(R.id.edt_user_name) EditText oldPwdEditText;
    @BindView(R.id.edt_pwd) EditText newPwdEditText;
    @BindView(R.id.btn_change_pwd) Button mRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_change_pwd)
    public void changePwdClicked() {
        String oldPwd = oldPwdEditText.getText().toString();
        String newPwd = newPwdEditText.getText().toString();
        if (TextUtils.isEmpty(oldPwd) || TextUtils.isEmpty(newPwd)) {
            ToastHelper.showShortMessage("新旧密码不能为空");
        } else {
            BmobUser.updateCurrentUserPassword(oldPwd, newPwd, new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        ToastHelper.showShortMessage("修改密码成功");
                        finish();
                    } else {
                        ToastHelper.showShortMessage("修改密码失败 ： " + e);
                    }
                }
            });
        }
    }
}
