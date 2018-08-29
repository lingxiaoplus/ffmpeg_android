package com.ffmpeg.lingxiao.ffmpegdemo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.ffmpeg.lingxiao.playerlibrary.PlayerSdk;

public class MainActivity extends AppCompatActivity {

    private SurfaceView mSurfaceView;
    private String mPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/nubia.mp4";
    private SurfaceHolder mHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        mSurfaceView = findViewById(R.id.surfaceView);
        mHolder = mSurfaceView.getHolder();
        mHolder.addCallback(new SurfaceCallback());


    }

    private class SurfaceCallback implements SurfaceHolder.Callback{

        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    PlayerSdk sdk = new PlayerSdk();
                    sdk.playLocalVideo(mPath,mHolder.getSurface());
                }
            }).start();
        }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        }
    }
}
