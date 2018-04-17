package com.example.hyan.jni;

public class JniTest {

    static {
        System.loadLibrary("my_jni_test");
    }

    public native int nativeMain();
}
