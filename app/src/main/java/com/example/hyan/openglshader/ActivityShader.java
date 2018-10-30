package com.example.hyan.openglshader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.hyan.R;

/**
 * Created by huangyan on 2018/10/12.
 */
public class ActivityShader extends Activity {

    private GLSurfaceView mSurfaceView;
    private GLShaderRender mRender;

    public static void launch(Context context) {
        Intent intent = new Intent(context, ActivityShader.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shader);
        mSurfaceView = findViewById(R.id.gl_surface_view);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mSurfaceView != null) {
            mSurfaceView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mSurfaceView != null) {
            mSurfaceView.onPause();
        }
    }

    private void initView() {
        mRender = new GLShaderRender(this);
        mSurfaceView.setEGLContextClientVersion(2);
        mSurfaceView.setRenderer(mRender);
    }
}
