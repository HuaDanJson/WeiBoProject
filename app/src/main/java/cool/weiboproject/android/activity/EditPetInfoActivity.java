package cool.weiboproject.android.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import cool.weiboproject.android.bean.CurrentUser;
import cool.weiboproject.android.utils.CurrentUserHelper;
import cool.weiboproject.android.utils.ToastHelper;


public class EditPetInfoActivity extends BaseActivity {

    @BindView(R.id.edt_pet_name) EditText edtPetName;
    @BindView(R.id.edt_pet_kind) EditText edtPetKind;
    @BindView(R.id.edt_pet_age) EditText edtPetAge;
    @BindView(R.id.edt_pet_weight) EditText edtPetWeight;
    @BindView(R.id.btn_sure) Button btnSure;

    private CurrentUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pet_info);
        ButterKnife.bind(this);
        mCurrentUser = BmobUser.getCurrentUser(CurrentUser.class);
        if (mCurrentUser == null) {
            finish();
        }
    }

    @OnClick(R.id.btn_sure)
    public void onSureClicked() {
        String petName = edtPetName.getText().toString();
        String petKind = edtPetKind.getText().toString();
        String petAge = edtPetAge.getText().toString();
        String petWeight = edtPetWeight.getText().toString();
        if (TextUtils.isEmpty(petName) || TextUtils.isEmpty(petKind) || TextUtils.isEmpty(petAge) || TextUtils.isEmpty(petWeight)) {
            ToastHelper.showShortMessage("请输入完信息后再点击确认");
        } else {
            mCurrentUser.setPetName(petName);
            mCurrentUser.setPetKind(petKind);
            mCurrentUser.setPetAge(petAge);
            mCurrentUser.setPetWeight(petWeight);
            mCurrentUser.update(new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        ToastHelper.showShortMessage("编辑信息成功");
                        CurrentUserHelper.getInstance().updateCurrentUser(mCurrentUser);
                        finish();
                    } else {
                        ToastHelper.showShortMessage("编辑信息失败");
                        Log.e("error", e.getMessage());
                    }
                }
            });
        }
    }
}
