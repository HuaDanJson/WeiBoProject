package cool.weiboproject.android.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cool.weiboproject.android.R;
import cool.weiboproject.android.adapter.WeiBoAdapter;
import cool.weiboproject.android.base.BaseActivity;
import cool.weiboproject.android.bean.WeiBoBean;
import cool.weiboproject.android.utils.ActivityUtil;
import cool.weiboproject.android.utils.ToastHelper;

public class SearchWeiBoActivity extends BaseActivity {

    @BindView(R.id.ivSeeOthersLocationActivityBack)
    ImageView mBack;
    @BindView(R.id.edtSeeOthersLocationActivity)
    EditText mEditText;
    @BindView(R.id.tvSeeOthersLocationActivityChaXun)
    TextView mSearchBtn;
    @BindView(R.id.rlv_search)
    RecyclerView mRecyclerView;

    private List<WeiBoBean> mWeiBoBeanList = new ArrayList<>();
    private WeiBoAdapter mWeiBoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_wei_bo);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.ivSeeOthersLocationActivityBack)
    public void backClicked() {
        this.finish();
    }

    @OnClick(R.id.tvSeeOthersLocationActivityChaXun)
    public void searchClicked() {
        String searchValue = mEditText.getText().toString();
        if (TextUtils.isEmpty(searchValue)) {
            ToastHelper.showShortMessage("请输入信息后再查询");
        } else {
            BmobQuery<WeiBoBean> query = new BmobQuery<WeiBoBean>();
            query.addWhereEqualTo("title", searchValue);
            query.setLimit(80);
            query.findObjects(new FindListener<WeiBoBean>() {
                @Override
                public void done(List<WeiBoBean> list, BmobException e) {
                    if (e == null) {
                        LogUtils.d("WeiBoFragment BmobQuery success:" + list);
                        mWeiBoBeanList = list;
                        if (list == null || list.isEmpty()) {
                            ToastHelper.showShortMessage("未搜索到匹配信息");
                        }
                        if (mWeiBoAdapter == null) {
                            mRecyclerView.setLayoutManager(new LinearLayoutManager(SearchWeiBoActivity.this));
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
    }

    private AdapterView.OnItemClickListener mBookClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
            if (mWeiBoAdapter == null) { return; }
            WeiBoBean weiBoBean = mWeiBoAdapter.getItem(position);
            ActivityUtil.startReaderActivity(SearchWeiBoActivity.this, weiBoBean);
        }
    };
}
