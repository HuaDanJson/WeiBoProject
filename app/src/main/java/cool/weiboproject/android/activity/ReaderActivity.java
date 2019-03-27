package cool.weiboproject.android.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cool.weiboproject.android.R;
import cool.weiboproject.android.base.BaseActivity;
import cool.weiboproject.android.bean.CurrentUser;
import cool.weiboproject.android.bean.WeiBoBean;
import cool.weiboproject.android.constants.AppConstant;
import cool.weiboproject.android.dialog.CommentDialog;
import cool.weiboproject.android.share.AndroidShare;
import cool.weiboproject.android.utils.CurrentUserHelper;
import cool.weiboproject.android.utils.ResourceUtil;
import cool.weiboproject.android.utils.ToastHelper;
import cool.weiboproject.android.utils.WeiBoDaoUtils;


public class ReaderActivity extends BaseActivity {

    @BindView(R.id.tv_text_value_read_book_activity) TextView mBookValue;
    @BindView(R.id.btn_chang_night_read_book_activity) Button mChangNight;
    @BindView(R.id.btn_chang_day_read_book_activity) Button mChangDay;
    @BindView(R.id.rv_book_reader) RelativeLayout rvBookReader;
    @BindView(R.id.tv_title_read_book_activity) TextView mTitle;
    @BindView(R.id.tv_writer_read_book_activity) TextView mWriter;
    @BindView(R.id.btn_chang_bg_color_read_book_activity) Button btnChangBgColorReadBookActivity;
    @BindView(R.id.btn_collection) Button mCollection;
    @BindView(R.id.btn_share) Button mShare;
    @BindView(R.id.btn_delete) Button mDelete;
    @BindView(R.id.btn_comment) Button mComment;
    @BindView(R.id.btn_forward) Button mForward;
    private WeiBoBean mWeiBoBean;
    private int changBGClickCount;
    private CommentDialog mCommentDialog;
    private CurrentUser mCurrentUsr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);
        ButterKnife.bind(this);
        mWeiBoBean = (WeiBoBean) getIntent().getSerializableExtra(AppConstant.IntentKey.EXTRA_DATA);
        LogUtils.d("ReaderActivity  mWeiBoBean : " + mWeiBoBean);
        mCurrentUsr = CurrentUserHelper.getInstance().getCurrentUser();
        if (mWeiBoBean == null || mCurrentUsr == null) {
            onBackPressed();
            return;
        }
        mTitle.setText(mWeiBoBean.getTitle());
        mBookValue.setText(mWeiBoBean.getValue());
        mBookValue.setMovementMethod(ScrollingMovementMethod.getInstance());
        String sendUserName = mWeiBoBean.getSendUserName();
        if (TextUtils.isEmpty(sendUserName)) {
            mDelete.setVisibility(View.GONE);
            mForward.setVisibility(View.VISIBLE);
        } else {
            if (sendUserName.equals(mCurrentUsr.getUsername())) {
                mForward.setVisibility(View.GONE);
                mDelete.setVisibility(View.VISIBLE);
            } else {
                mDelete.setVisibility(View.GONE);
                mForward.setVisibility(View.VISIBLE);
            }
        }
        WeiBoBean weiBoBean = WeiBoDaoUtils.getInstance().queryOneData(mWeiBoBean.getCreatTime());
        if (weiBoBean == null) {
            mCollection.setText("收藏");
        } else {
            mCollection.setText("取消收藏");
        }
    }


    @OnClick(R.id.btn_chang_night_read_book_activity)
    public void changNight() {
        rvBookReader.setBackgroundColor(ResourceUtil.getColor(R.color.black60));
    }

    @OnClick(R.id.btn_chang_day_read_book_activity)
    public void changDay() {
        rvBookReader.setBackgroundColor(ResourceUtil.getColor(R.color.white));
    }

    @OnClick(R.id.btn_chang_bg_color_read_book_activity)
    public void changBGColorClicked() {
        if (changBGClickCount == 0) {
            rvBookReader.setBackgroundColor(ResourceUtil.getColor(R.color.video_manage_activity_select_all_text_color));
            changBGClickCount++;
        } else if (changBGClickCount == 1) {
            rvBookReader.setBackgroundColor(ResourceUtil.getColor(R.color.green3));
            changBGClickCount++;
        } else if (changBGClickCount == 2) {
            rvBookReader.setBackgroundColor(ResourceUtil.getColor(R.color.attention_others_activity_log_in_text_color));
            changBGClickCount++;
        } else if (changBGClickCount == 3) {
            rvBookReader.setBackground(ResourceUtil.getDrawable(R.drawable.welecome_bg));
            changBGClickCount++;
        } else if (changBGClickCount == 4) {
            rvBookReader.setBackgroundColor(ResourceUtil.getColor(R.color.white));
            changBGClickCount = 0;
        }
    }

    @OnClick(R.id.btn_collection)
    public void collectionClicked() {
        String text = mCollection.getText().toString();
        if (TextUtils.isEmpty(text)) {return;}
        if ("收藏".equals(text)) {
            mWeiBoBean.setCollectionUserName(mCurrentUsr.getUsername());
            WeiBoDaoUtils.getInstance().insertOneData(mWeiBoBean);
            mCollection.setText("取消收藏");
            ToastHelper.showShortMessage("收藏成功");
        } else {
            WeiBoDaoUtils.getInstance().deleteOneData(mWeiBoBean);
            mCollection.setText("收藏");
            ToastHelper.showShortMessage("取消收藏成功");
        }
    }

    @OnClick(R.id.btn_share)
    public void shareClicked() {
        AndroidShare as = new AndroidShare(this, "YangWeiBo 分享：" + mWeiBoBean.toString(), "");
        as.show();
    }

    @OnClick(R.id.btn_comment)
    public void commentClicked() {
        showCommentDialog();
    }

    @OnClick(R.id.btn_forward)
    public void forwardClicked() {
        if (mWeiBoBean == null || mCurrentUsr == null) {return;}
        WeiBoBean weiBoBean = new WeiBoBean();
        weiBoBean.setTitle(mWeiBoBean.getTitle());
        weiBoBean.setIntroduce(mWeiBoBean.getIntroduce());
        weiBoBean.setValue(mWeiBoBean.getValue());
        weiBoBean.setCreatTime(System.currentTimeMillis());
        weiBoBean.setSendUserName(mCurrentUsr.getUsername());
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

    @OnClick(R.id.btn_delete)
    public void deleteClicked() {
        mWeiBoBean.setObjectId(mWeiBoBean.getObjectId());
        mWeiBoBean.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    ToastHelper.showShortMessage("删除成功");
                    finish();
                } else {
                    ToastHelper.showShortMessage("删除失败");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.exit_stop_original_place, R.anim.exit_to_right);
    }

    public void showCommentDialog() {
        if (mCommentDialog == null) {
            mCommentDialog = new CommentDialog();
        }
        mCommentDialog.setData(mWeiBoBean);
        mCommentDialog.tryShow(getSupportFragmentManager());
    }
}
