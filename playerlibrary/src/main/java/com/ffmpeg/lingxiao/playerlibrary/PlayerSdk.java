package com.ffmpeg.lingxiao.playerlibrary;

import android.view.Surface;
import android.view.SurfaceHolder;

public class PlayerSdk {
    static {
        System.loadLibrary("native-lib");
        System.loadLibrary("avcodec");
        System.loadLibrary("avdevice");
        System.loadLibrary("avfilter");
        System.loadLibrary("avformat");
        System.loadLibrary("avutil");
        System.loadLibrary("swresample");
        System.loadLibrary("swscale");
    }

    public native String stringFromJNI();

    public native int playLocalVideo(String path, Surface surface);

}
