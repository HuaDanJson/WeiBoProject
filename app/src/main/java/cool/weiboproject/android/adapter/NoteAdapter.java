package cool.weiboproject.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cool.weiboproject.android.R;
import cool.weiboproject.android.bean.Note;


public class NoteAdapter extends RecyclerView.Adapter {

    private List<Note> noteList = new ArrayList<>();
    private Context mContext;

    public NoteAdapter(List<Note> noteList, Context mContext) {
        this.mContext = mContext;
        this.noteList = noteList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_note, parent, false);
        return new NoteAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        ((NoteAdapterViewHolder) viewHolder).tvTitleNoteAdapter.setText("标题：" + noteList.get(position).getNoteTitle());
        ((NoteAdapterViewHolder) viewHolder).tvSenderNoteAdapter.setText("发布者名字：" + noteList.get(position).getSendUserName());
        ((NoteAdapterViewHolder) viewHolder).tvValueNoteAdapter.setText("内容：" + noteList.get(position).getNoteContent());
    }


    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public static class NoteAdapterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title_note_adapter) TextView tvTitleNoteAdapter;
        @BindView(R.id.tv_sender_note_adapter) TextView tvSenderNoteAdapter;
        @BindView(R.id.tv_value_note_adapter) TextView tvValueNoteAdapter;

        public NoteAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
