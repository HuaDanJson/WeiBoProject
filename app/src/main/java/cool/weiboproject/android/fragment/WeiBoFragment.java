package cool.weiboproject.android.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cool.weiboproject.android.R;
import cool.weiboproject.android.activity.SendWeiBoActivity;
import cool.weiboproject.android.adapter.WeiBoAdapter;
import cool.weiboproject.android.bean.WeiBoBean;
import cool.weiboproject.android.utils.ActivityUtil;


public class WeiBoFragment extends Fragment implements View.OnTouchListener {

    @BindView(R.id.rlv_book_reader) RecyclerView mRecyclerView;
    @BindView(R.id.tv_send_note_activity) TextView mSend;

    private List<WeiBoBean> mWeiBoBeanList = new ArrayList<>();
    private WeiBoAdapter mWeiBoAdapter;
    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_moment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setOnTouchListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getBookList();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    public void getBookList() {
        BmobQuery<WeiBoBean> query = new BmobQuery<>();
        query.setLimit(50).order("createdAt")
                .findObjects(new FindListener<WeiBoBean>() {
                    @Override
                    public void done(List<WeiBoBean> weiBoBeanList, BmobException e) {
                        if (e == null) {
                            LogUtils.d("WeiBoFragment BmobQuery success:" + weiBoBeanList);
                            mWeiBoBeanList = weiBoBeanList;
                            if (mWeiBoAdapter == null) {
                                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                mWeiBoAdapter = new WeiBoAdapter();
                                mWeiBoAdapter.setOnItemClickListener(mBookClickListener);
                                mWeiBoAdapter.setDataSilently(mWeiBoBeanList);
                                mRecyclerView.setAdapter(mWeiBoAdapter);
                            } else {
                                mWeiBoAdapter.setData(mWeiBoBeanList);
                            }

                        } else {
                            LogUtils.d("WeiBoFragment BmobQuery failed : " + e);
                        }
                    }
                });
    }

    private AdapterView.OnItemClickListener mBookClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
            if (mWeiBoAdapter == null) { return; }
            WeiBoBean weiBoBean = mWeiBoAdapter.getItem(position);
            ActivityUtil.startReaderActivity(WeiBoFragment.this, weiBoBean);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.tv_send_note_activity)
    public void senNote() {
        startActivity(new Intent(getActivity(), SendWeiBoActivity.class));
    }
}
