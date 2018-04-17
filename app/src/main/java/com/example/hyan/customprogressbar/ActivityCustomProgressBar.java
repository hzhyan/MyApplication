package com.example.hyan.customprogressbar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.hyan.R;

/**
 * Created by huangyan on 2018/3/3.
 */

public class ActivityCustomProgressBar extends Activity {

    private static final String TAG = "CustomProgressBar";
    private RecyclerView mRecyclerView;
    private RecycleViewAdapter mAdapter;
    private int mStart;
    private int mLast;

    public static void launch(Context context) {
        Intent intent = new Intent(context, ActivityCustomProgressBar.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_progress_bar);
        mRecyclerView = findViewById(R.id.test_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecycleViewAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                statisticsScroll();
            }
        });

    }

    private void statisticsScroll() {
        RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
            int firstItemPosition = linearManager.findFirstVisibleItemPosition();
            int lastItemPosition = linearManager.findLastVisibleItemPosition();
            int start = firstItemPosition < 0 ? 0 : firstItemPosition;
            int last = lastItemPosition < 20 ? lastItemPosition : 19;
            if (mStart == 0 && mLast == 0) {
                mStart = start;
                mLast = last;
            } else if (mStart == start && mLast == last) {
                // 未变化
            } else if (mStart > mLast) {
                // 非法数据
            } else if (start > last) {
                // 非法数据
            } else if (mStart > start || mLast > last) { // 向上滑
                load(start, mStart);
                cancel(last, mLast);
                mStart = start;
                mLast = last;
            } else if (mStart < start || mLast < last) { // 向下滑
                load(mLast, last);
                cancel(mStart, start);
                mStart = start;
                mLast = last;
            }
        }
    }

    private void load(int start, int end) {
        for (; start <= end; start++) {
            Log.d(TAG, "load " + start);
        }
    }

    private void cancel(int start, int end) {
        for (; start <= end; start++) {
            Log.d(TAG, "cancel " + start);
        }
    }
}
