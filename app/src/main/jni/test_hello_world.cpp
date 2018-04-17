//
// Created by HuangYan on 2018/4/14.
//
# include<iostream>
#include "jni.h"

using namespace std;

extern "C" JNIEXPORT
int JNICALL Java_com_example_hyan_jni_JniTest_nativeMain()
{
//    cout << "Hello World !!" << endl;
    return 100;
}
