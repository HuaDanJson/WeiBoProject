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
import cool.weiboproject.android.bean.WeiBoBean;
import cool.weiboproject.android.utils.TimeUtils;


public class WeiBoAdapter extends BaseRVAdapter<WeiBoBean, WeiBoAdapter.ReaderAdapterHolder> {

    @Override
    protected ReaderAdapterHolder doCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ReaderAdapterHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_book_reader_adapter, viewGroup, false));
    }

    @Override
    protected void bindItemData(ReaderAdapterHolder viewHolder, WeiBoBean weiBoBean, int position) {
        viewHolder.bindView(weiBoBean, position);
    }

    public class ReaderAdapterHolder extends RecyclerView.ViewHolder implements IViewHolder<WeiBoBean> {

        @BindView(R.id.tv_title_item_book_reader)
        TextView mTitle;
        @BindView(R.id.tv_time_item_book_reader)
        TextView mTime;
        @BindView(R.id.tv_introduce_item_book_reader)
        TextView mIntroduce;

        public ReaderAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindView(WeiBoBean weiBoBean, int position) {
            mTitle.setText(weiBoBean.getTitle());
            mIntroduce.setText(weiBoBean.getIntroduce());
            mTime.setText(TimeUtils.stampToDate(weiBoBean.getCreatTime()));
        }
    }
}

