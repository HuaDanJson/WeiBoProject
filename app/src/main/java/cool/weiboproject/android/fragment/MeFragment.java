package cool.weiboproject.android.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bmob.v3.BmobUser;
import cool.weiboproject.android.R;
import cool.weiboproject.android.activity.ChangePwdActivity;
import cool.weiboproject.android.activity.EditPetInfoActivity;
import cool.weiboproject.android.activity.LoginActivity;
import cool.weiboproject.android.bean.CurrentUser;
import cool.weiboproject.android.utils.CurrentUserHelper;
import de.hdodenhof.circleimageview.CircleImageView;


public class MeFragment extends Fragment implements View.OnTouchListener {

    @BindView(R.id.tv_logout) TextView mLogout;
    @BindView(R.id.tv_change_pwd) TextView mChangePwd;
    @BindView(R.id.tv_title) TextView mUserName;
    @BindView(R.id.tv_change_pet_info) TextView mChangePetInfo;

    @BindView(R.id.cir_avatar) CircleImageView cirAvatar;
    @BindView(R.id.tv_pet_name) TextView tvPetName;
    @BindView(R.id.edt_pet_kind) TextView edtPetKind;
    @BindView(R.id.edt_pet_age) TextView edtPetAge;
    @BindView(R.id.edt_pet_weight) TextView edtPetWeight;

    private CurrentUser mCurrentUser;
    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setOnTouchListener(this);

    }

    public void initInfo() {
        mCurrentUser = CurrentUserHelper.getInstance().getCurrentUser();
        if (mCurrentUser != null) {
            mUserName.setText("用户：" + mCurrentUser.getUsername());
            if (TextUtils.isEmpty(mCurrentUser.getPetName())) {
                tvPetName.setVisibility(View.GONE);
                edtPetKind.setVisibility(View.GONE);
                edtPetAge.setVisibility(View.GONE);
                edtPetWeight.setVisibility(View.GONE);
            } else {
                tvPetName.setVisibility(View.VISIBLE);
                edtPetKind.setVisibility(View.VISIBLE);
                edtPetAge.setVisibility(View.VISIBLE);
                edtPetWeight.setVisibility(View.VISIBLE);
                tvPetName.setText("宠物名：" + mCurrentUser.getPetName());
                edtPetKind.setText("宠物种类：" + mCurrentUser.getPetKind());
                edtPetAge.setText("宠物年龄：" + mCurrentUser.getPetAge());
                edtPetWeight.setText("宠物体重：" + mCurrentUser.getPetWeight());
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initInfo();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.tv_change_pwd)
    public void changePwdClicked() {
        startActivity(new Intent(getActivity(), ChangePwdActivity.class));
    }

    @OnClick(R.id.tv_change_pet_info)
    public void changePetInfoClicked() {
        startActivity(new Intent(getActivity(), EditPetInfoActivity.class));
    }

    @OnClick(R.id.tv_logout)
    public void logoutClicked() {
        BmobUser.logOut();
        CurrentUserHelper.getInstance().updateCurrentUser(null);
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();
    }
}
