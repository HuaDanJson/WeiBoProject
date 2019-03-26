package cool.weiboproject.android.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cool.weiboproject.android.R;
import cool.weiboproject.android.base.BaseActivity;
import cool.weiboproject.android.bean.CurrentUser;
import cool.weiboproject.android.bean.WeiBoBean;
import cool.weiboproject.android.utils.CurrentUserHelper;
import cool.weiboproject.android.utils.ToastHelper;

public class SendWeiBoActivity extends BaseActivity {

    @BindView(R.id.edtFeedbackActivityEmail) EditText edtTitle;
    @BindView(R.id.edtFeedbackActivityFeedback) EditText edtValue;
    @BindView(R.id.textView) TextView textView;
    @BindView(R.id.llFeedbackActivityCommit) LinearLayout llFeedbackActivityCommit;
    @BindView(R.id.edt_introduce) EditText mIntroduceEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_note);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.llFeedbackActivityCommit)
    public void sendNote() {
        String title = edtTitle.getText().toString();
        String introduce = mIntroduceEditText.getText().toString();
        String value = edtValue.getText().toString();
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(introduce) || TextUtils.isEmpty(value)) {
            ToastHelper.showShortMessage("请编辑完信息后再发布");
        } else {
            WeiBoBean weiBoBean = new WeiBoBean();
            weiBoBean.setTitle(title);
            weiBoBean.setIntroduce(introduce);
            weiBoBean.setValue(value);
            weiBoBean.setCreatTime(System.currentTimeMillis());
            CurrentUser currentUser = CurrentUserHelper.getInstance().getCurrentUser();
            if (currentUser != null) {
                weiBoBean.setSendUserName(currentUser.getUsername());
            }
            weiBoBean.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        ToastHelper.showShortMessage("发布成功");
                        finish();
                    } else {
                        ToastHelper.showShortMessage("发布失败");
                    }
                }
            });
        }
    }
}
