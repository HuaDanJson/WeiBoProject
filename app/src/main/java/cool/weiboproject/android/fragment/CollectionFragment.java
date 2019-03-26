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
import cool.weiboproject.android.adapter.NoteAdapter;
import cool.weiboproject.android.bean.Note;


public class CollectionFragment extends Fragment implements View.OnTouchListener {

    @BindView(R.id.tv_title_note_activity) TextView mTitle;
    @BindView(R.id.tv_send_note_activity) TextView mSendNote;
    @BindView(R.id.rv_note_activity) RecyclerView mRecyclerView;
    @BindView(R.id.tv_empty_note_activity) TextView tvEmptyNoteActivity;

    List<Note> noteList = new ArrayList<>();

    private NoteAdapter noteAdapter;

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
    }

    @Override
    public void onResume() {
        super.onResume();
        getMediaData();
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

    public void getMediaData() {
        BmobQuery<Note> query = new BmobQuery<Note>();
        // 按时间降序查询
        query.order("-createdAt");
        query.setLimit(80);
        //从服务器获取衣服图片 集合 Arraylist
        query.findObjects(new FindListener<Note>() {
            @Override
            public void done(List<Note> list, BmobException e) {
                if (e == null) {
                    noteList = list;
                    if (noteList.size() == 0) {
                        if (tvEmptyNoteActivity == null) {return;}
                        tvEmptyNoteActivity.setText("还未有编写的帖子");
                        tvEmptyNoteActivity.setVisibility(View.VISIBLE);
                    } else {
                        if (mRecyclerView != null) {
                            tvEmptyNoteActivity.setVisibility(View.GONE);
                            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            noteAdapter = new NoteAdapter(noteList, getActivity());
                            mRecyclerView.setAdapter(noteAdapter);
                        }
                    }
                } else {
                    if (tvEmptyNoteActivity == null) {return;}
                    LogUtils.e("NoteActivity e =" + e);
                    tvEmptyNoteActivity.setText("获取数据失败");
                    tvEmptyNoteActivity.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    @OnClick(R.id.tv_send_note_activity)
    public void senNote() {
        startActivity(new Intent(getActivity(), SendWeiBoActivity.class));
    }
}
