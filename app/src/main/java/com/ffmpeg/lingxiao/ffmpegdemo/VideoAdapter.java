package com.ffmpeg.lingxiao.ffmpegdemo;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class VideoAdapter extends BaseQuickAdapter<VideoModel,BaseViewHolder> {
    public VideoAdapter(int layoutResId, @Nullable List<VideoModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoModel item) {
        int size = item.getSize();
        float sizeF = size /1024f/1024f;
        helper.setText(R.id.tv_name,"名字："+item.getName());
        helper.setText(R.id.tv_size,"大小："+(sizeF+"").substring(0,4)+"M");
        helper.setText(R.id.tv_path,"路径："+item.getPath());
    }
}
