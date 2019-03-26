package cool.weiboproject.android.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cool.weiboproject.android.R;
import cool.weiboproject.android.base.BaseFragmentDialog;
import cool.weiboproject.android.utils.ActivityUtil;

public class LogoutDialog extends BaseFragmentDialog {

    @BindView(R.id.tv_yes_logout_dialog) TextView mYes;
    @BindView(R.id.tv_cancel_logout_dialog) TextView mCancel;
    @BindView(R.id.rl_log_out_dialog) RelativeLayout mLogoutDialog;
    Unbinder unbinder;

    private boolean mHasSavedInstanceState;

    @Override
    protected boolean hasSavedInstanceState() {
        return mHasSavedInstanceState;
    }

    public void setSavedInstanceState(boolean hasSavedInstanceState) {
        this.mHasSavedInstanceState = hasSavedInstanceState;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.dialog_log_out;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.rl_log_out_dialog)
    public void onHidleClicked(View view) {
        tryHide();
    }

    @OnClick(R.id.tv_yes_logout_dialog)
    public void onOkClicked(View view) {
        ActivityUtil.startLoginActivity(getActivity());
        tryHide();
    }

    @OnClick(R.id.tv_cancel_logout_dialog)
    public void onCancelClicked(View view) {
        tryHide();
    }

}
