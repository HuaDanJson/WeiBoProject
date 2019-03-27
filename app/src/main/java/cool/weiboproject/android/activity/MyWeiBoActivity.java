package cool.weiboproject.android.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.blankj.utilcode.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cool.weiboproject.android.R;
import cool.weiboproject.android.adapter.WeiBoAdapter;
import cool.weiboproject.android.base.BaseActivity;
import cool.weiboproject.android.bean.CurrentUser;
import cool.weiboproject.android.bean.WeiBoBean;
import cool.weiboproject.android.utils.ActivityUtil;
import cool.weiboproject.android.utils.CurrentUserHelper;

public class MyWeiBoActivity extends BaseActivity {

    @BindView(R.id.rv_my_weibo)
    RecyclerView mRecyclerView;

    private List<WeiBoBean> mWeiBoBeanList = new ArrayList<>();
    private WeiBoAdapter mWeiBoAdapter;
    private CurrentUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wei_bo);
        ButterKnife.bind(this);
        mCurrentUser = CurrentUserHelper.getInstance().getCurrentUser();
        if (mCurrentUser == null) {finish();}
        getMyWeiList();
    }

    public void getMyWeiList() {
        BmobQuery<WeiBoBean> query = new BmobQuery<>();
        query.addWhereEqualTo("sendUserName", mCurrentUser.getUsername());
        query.setLimit(50).order("createdAt")
                .findObjects(new FindListener<WeiBoBean>() {
                    @Override
                    public void done(List<WeiBoBean> weiBoBeanList, BmobException e) {
                        if (e == null) {
                            LogUtils.d("WeiBoFragment BmobQuery success:" + weiBoBeanList);
                            mWeiBoBeanList = weiBoBeanList;
                            if (mWeiBoAdapter == null) {
                                mRecyclerView.setLayoutManager(new LinearLayoutManager(MyWeiBoActivity.this));
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
            ActivityUtil.startReaderActivity(MyWeiBoActivity.this, weiBoBean);
        }
    };
}
