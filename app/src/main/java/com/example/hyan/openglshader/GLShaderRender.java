package com.example.hyan.openglshader;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by huangyan on 2018/10/12.
 */
public class GLShaderRender implements GLSurfaceView.Renderer {

    private static final String TAG = "GLShaderRender";
    private float[] mTriangleArray = {
            0f, 1f, -0f,
            -1f, -1f, -0f,
            1f, -1f, -0f
    };

    private float[] mColor = new float[]{
            1f, 0f, 0f, 1f,
            0f, 1f, 0f, 1f,
            0f, 0f, 1f, 1f
    };

    private String mVertexShader;
    private String mFragmentShader;
    private FloatBuffer mTriangleBuffer;
    private FloatBuffer mColorBuffer;
    private int mProgramId;
    private int mGLAttribPosition;
    private int mGLAttribColor;

    public GLShaderRender(Context context) {
        try {
            InputStream vsis = context.getAssets().open("vertex_shader.glsl");
            byte[] vsbytes = new byte[vsis.available()];
            vsis.read(vsbytes);
            mVertexShader = new String(vsbytes);

            InputStream fsis = context.getAssets().open("fragment_shader.glsl");
            byte[] fsbytes = new byte[fsis.available()];
            fsis.read(fsbytes);
            mFragmentShader = new String(fsbytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(mTriangleArray.length * 4);
        byteBuffer.order(ByteOrder.nativeOrder());
        mTriangleBuffer = byteBuffer.asFloatBuffer();
        mTriangleBuffer.put(mTriangleArray);
        mTriangleBuffer.position(0);

        ByteBuffer bb2 = ByteBuffer.allocateDirect(mColor.length * 4);
        bb2.order(ByteOrder.nativeOrder());
        mColorBuffer = bb2.asFloatBuffer();
        mColorBuffer.put(mColor);
        mColorBuffer.position(0);

    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        Log.d("GLShaderRender", "onSurfaceCreated");

        int vertexShaderId = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
        Log.d(TAG, GLES20.glGetShaderInfoLog(vertexShaderId) + ", " + GLES20.glGetError());

        int[] compileId = new int[1];



        GLES20.glShaderSource(vertexShaderId, mVertexShader);
        GLES20.glCompileShader(vertexShaderId);
        GLES20.glGetShaderiv(vertexShaderId, GLES20.GL_COMPILE_STATUS, compileId, 0);
        if (compileId[0] <= 0) {
            Log.e(TAG, "compile vertexShader failed : " + GLES20.glGetShaderInfoLog(vertexShaderId));
        }

        int fragmentShaderId = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
        GLES20.glShaderSource(fragmentShaderId, mFragmentShader);
        GLES20.glCompileShader(fragmentShaderId);
        int[] FSCompileId = new int[1];
        GLES20.glGetShaderiv(fragmentShaderId, GLES20.GL_COMPILE_STATUS, FSCompileId, 0);
        if (FSCompileId[0] <= 0) {
            Log.e(TAG, "compile Fragment failed : " + GLES20.glGetShaderInfoLog(fragmentShaderId));
        }

        mProgramId = GLES20.glCreateProgram();

        GLES20.glAttachShader(mProgramId, vertexShaderId);

        GLES20.glAttachShader(mProgramId, fragmentShaderId);

        GLES20.glLinkProgram(mProgramId);

        int[] proCompileId = new int[1];
        GLES20.glGetProgramiv(mProgramId, GLES20.GL_LINK_STATUS, proCompileId, 0);
        if (proCompileId[0] <= 0) {
            Log.e(TAG, "shader link failed : " + GLES20.glGetProgramInfoLog(fragmentShaderId));
        }

        gl.glClearColor(1,1,1,1);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // 设置OpenGL场景的大小,(0,0)表示窗口内部视口的左下角，(w,h)指定了视口的大小
        GLES20.glViewport(0, (height - width) / 2 , width, width);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glUseProgram(mProgramId);

        GLES20.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        mGLAttribPosition = GLES20.glGetAttribLocation(mProgramId, "position");

        mGLAttribColor = GLES20.glGetAttribLocation(mProgramId, "color");


        // 设置三角形
        GLES20.glEnableVertexAttribArray(mGLAttribPosition);
        GLES20.glVertexAttribPointer(mGLAttribPosition, 3, GL10.GL_FLOAT, false, 3 * 4, mTriangleBuffer);

        GLES20.glEnableVertexAttribArray(mGLAttribColor);
        GLES20.glVertexAttribPointer(mGLAttribColor, 4, GLES20.GL_FLOAT, false, 4 * 4, mColorBuffer);

        // 绘制三角形
        GLES20.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);

        GLES20.glDisableVertexAttribArray(mGLAttribPosition);

        //绘制结束
        GLES20.glFinish();

    }
}
