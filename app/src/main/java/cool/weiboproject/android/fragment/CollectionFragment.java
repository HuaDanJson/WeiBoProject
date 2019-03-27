package cool.weiboproject.android.fragment;


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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cool.weiboproject.android.R;
import cool.weiboproject.android.adapter.WeiBoAdapter;
import cool.weiboproject.android.bean.CurrentUser;
import cool.weiboproject.android.bean.WeiBoBean;
import cool.weiboproject.android.utils.ActivityUtil;
import cool.weiboproject.android.utils.CurrentUserHelper;
import cool.weiboproject.android.utils.WeiBoDaoUtils;


public class CollectionFragment extends Fragment implements View.OnTouchListener {

    @BindView(R.id.rv_note_activity) RecyclerView mRecyclerView;

    private List<WeiBoBean> mWeiBoBeanList = new ArrayList<>();
    private WeiBoAdapter mWeiBoAdapter;
    private CurrentUser mCurrentUser;

    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setOnTouchListener(this);
        mCurrentUser = CurrentUserHelper.getInstance().getCurrentUser();
    }

    @Override
    public void onResume() {
        super.onResume();
        getCollectionData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            getCollectionData();
        }
    }

    public void getCollectionData() {
        if (mCurrentUser == null) {
            mCurrentUser = CurrentUserHelper.getInstance().getCurrentUser();
        }
        if (mCurrentUser != null) {
            mWeiBoBeanList = WeiBoDaoUtils.getInstance().queryCurrentCollectionData(mCurrentUser.getUsername());
            if (mWeiBoAdapter == null) {
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                mWeiBoAdapter = new WeiBoAdapter();
                mWeiBoAdapter.setOnItemClickListener(mBookClickListener);
                mWeiBoAdapter.setDataSilently(mWeiBoBeanList);
                mRecyclerView.setAdapter(mWeiBoAdapter);
            } else {
                mWeiBoAdapter.setData(mWeiBoBeanList);
            }
        }
    }

    private AdapterView.OnItemClickListener mBookClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
            if (mWeiBoAdapter == null) { return; }
            WeiBoBean weiBoBean = mWeiBoAdapter.getItem(position);
            ActivityUtil.startReaderActivity(CollectionFragment.this, weiBoBean);
        }
    };
}
