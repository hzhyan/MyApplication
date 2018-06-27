package com.example.hyan.openglstl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.example.hyan.R;

public class ActivityOpenGLSTL extends Activity {

    private GLSurfaceView mSurfaceView;
    private GLRenderer mGLRenderer;

    private int mRotateDegree;
    private Handler mHandler;
    private Runnable mRotateRunnable;

    public static void launch(Context context) {
        Intent intent = new Intent(context, ActivityOpenGLSTL.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_gl_stl);
        mSurfaceView = findViewById(R.id.gl_surface_view);
        initView();
    }

    private void initView() {
        mGLRenderer = new GLRenderer(this);
        mSurfaceView.setRenderer(mGLRenderer);
        mHandler = new Handler();
        mRotateRunnable = () -> {
            mRotateDegree = mRotateDegree + 5;
            mGLRenderer.rotate(mRotateDegree);
            mHandler.postDelayed(mRotateRunnable, 100);
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mSurfaceView != null) {
            mSurfaceView.onResume();
            mHandler.postDelayed(mRotateRunnable, 100);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mSurfaceView != null) {
            mSurfaceView.onPause();
            mHandler.removeCallbacksAndMessages(null);
        }
    }
}
