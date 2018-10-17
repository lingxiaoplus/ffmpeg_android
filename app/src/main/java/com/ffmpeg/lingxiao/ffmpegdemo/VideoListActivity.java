package com.ffmpeg.lingxiao.ffmpegdemo;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class VideoListActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private static final String TAG = VideoListActivity.class.getSimpleName();
    private List<VideoModel> listImage = new ArrayList<>();
    private VideoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);

        RecyclerView recyclerView = findViewById(R.id.recycerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        mAdapter = new VideoAdapter(R.layout.video_item,listImage);
        recyclerView.setAdapter(mAdapter);
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(this, perms)) {
            EasyPermissions.requestPermissions(this, "需要权限",100, perms);
        }else {
            String[] projection = { MediaStore.Video.Media._ID,
                    MediaStore.Video.Media.DISPLAY_NAME,
                    MediaStore.Video.Media.SIZE,
                    MediaStore.Video.Media.DATA};
            String orderBy = MediaStore.Video.Media.DISPLAY_NAME;
            Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            getContentProvider(uri,projection, orderBy);
        }
        
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.d(TAG, "onItemClick: ");
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("path",listImage.get(position).getPath());
                startActivity(intent);
            }
        });
    }


    /**
     * 获取ContentProvider
     * @param projection
     * @param orderBy
     */
    public void getContentProvider(Uri uri, String[] projection, String orderBy) {
        // TODO Auto-generated method stub

        Cursor cursor = getContentResolver().query(uri, projection, null,
                null, orderBy);
        if (null == cursor) {
            return;
        }
        if (cursor.moveToFirst()){
            do {
                VideoModel model = new VideoModel();
                model.setId(cursor.getLong(0));
                model.setName(cursor.getString(1));
                model.setSize(cursor.getInt(2));
                model.setPath(cursor.getString(3));
                listImage.add(model);
            }while (cursor.moveToNext());
        }
        cursor.close();
        mAdapter.notifyDataSetChanged();
        Log.d(TAG, "getContentProvider: "+listImage.toString());
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}
