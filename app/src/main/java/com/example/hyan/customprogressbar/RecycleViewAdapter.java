package com.example.hyan.customprogressbar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hyan.R;

/**
 * Created by huangyan on 2018/3/3.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter {

    private Context mContext;

    public RecycleViewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(mContext);
        MyHolder holder = new MyHolder(imageView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder) {
            ((MyHolder) holder).mView.setImageResource(R.mipmap.all_ic_error);
        }
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class MyHolder extends RecyclerView.ViewHolder {

        ImageView mView;

        public MyHolder(View itemView) {
            super(itemView);
            mView = (ImageView) itemView;
        }
    }
}
