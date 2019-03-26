package cool.weiboproject.android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cool.weiboproject.android.R;
import cool.weiboproject.android.base.BaseRVAdapter;
import cool.weiboproject.android.base.IViewHolder;
import cool.weiboproject.android.bean.CommentBean;
import cool.weiboproject.android.utils.TimeUtils;


public class CommentAdapter extends BaseRVAdapter<CommentBean, CommentAdapter.CommentAdapterHolder> {

    @Override
    protected CommentAdapterHolder doCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new CommentAdapterHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_comment_adapter, viewGroup, false));
    }

    @Override
    protected void bindItemData(CommentAdapterHolder viewHolder, CommentBean commentBean, int position) {
        viewHolder.bindView(commentBean, position);
    }

    public class CommentAdapterHolder extends RecyclerView.ViewHolder implements IViewHolder<CommentBean> {

        @BindView(R.id.tv_comment_value)
        TextView mCommentValue;
        @BindView(R.id.tv_comment_time)
        TextView tvCommentTime;
        @BindView(R.id.tv_comment_sender)
        TextView tvCommentSender;

        public CommentAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindView(CommentBean commentBean, int position) {
            mCommentValue.setText(commentBean.getValue());
            tvCommentSender.setText(commentBean.getSendUserName());
            tvCommentTime.setText(TimeUtils.stampToDate(commentBean.getCommentTime()));
        }
    }
}

