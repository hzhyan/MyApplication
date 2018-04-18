package com.example.hyan.opengl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.hyan.R;

public class ActivityOpenGL extends Activity {

    private GLSurfaceView mGLSurfaceView;
    private GLSurfaceView.Renderer mRenderer;

    public static void launch(Context context) {
        Intent intent = new Intent(context, ActivityOpenGL.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_gl);
        mGLSurfaceView = findViewById(R.id.gl_surface_view);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mGLSurfaceView != null) {
            mGLSurfaceView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mGLSurfaceView != null) {
            mGLSurfaceView.onPause();
        }
    }

    private void initView() {
        mRenderer = new GLRenderer();
        mGLSurfaceView.setRenderer(mRenderer);
    }
}
