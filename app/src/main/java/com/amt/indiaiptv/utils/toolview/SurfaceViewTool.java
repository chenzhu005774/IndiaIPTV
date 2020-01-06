package com.amt.indiaiptv.utils.toolview;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.amt.indiaiptv.R;
import com.amt.indiaiptv.utils.Constant;
import com.amt.indiaiptv.utils.LogUtils;
import com.amt.indiaiptv.utils.commonbean.CommonBean;

import java.io.IOException;

/**
 * setOnPreparedListener
 * Created by Administrator on 2019/4/3.
 * https://blog.csdn.net/pingqingbo/article/details/24922071
 */
final public class SurfaceViewTool implements MediaPlayer.OnErrorListener,MediaPlayer.OnPreparedListener {
   Context context  =null;
    SurfaceView surfaceView;
    MediaPlayer player;
    SurfaceHolder holder;
    public void creatview(VideoViewToolBean videoViewToolBean,CommonBean commonBean){
          context =commonBean.getContext();
        RelativeLayout rootlayout = new RelativeLayout(commonBean.getContext());
        rootlayout.setOnFocusChangeListener(commonBean.getFocusChangeListener());
        rootlayout.requestFocus();
        rootlayout.setFocusable(videoViewToolBean.focus);
        if (videoViewToolBean.focus){
            rootlayout.setBackgroundResource(R.drawable.bgseletor);
        }
        RelativeLayout.LayoutParams params =  new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        if (videoViewToolBean.focus){
            params.setMargins(videoViewToolBean.getMarleft()- Constant.margin,videoViewToolBean.getMartop()- Constant.margin,0,0);
        }else {
            params.setMargins(videoViewToolBean.getMarleft(),videoViewToolBean.getMartop(),0,0);
        }

        rootlayout.setLayoutParams(params);
        rootlayout.setTag(commonBean.getTag());
        rootlayout.setOnClickListener(commonBean.getOnClickListener());

        RelativeLayout.LayoutParams videopar = new RelativeLayout.LayoutParams(videoViewToolBean.getWidth(), videoViewToolBean.getHeigh());
        videopar.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);

        surfaceView = new SurfaceView(commonBean.getContext());
        surfaceView.setLayoutParams(videopar);
        player=new MediaPlayer();
        try {
            LogUtils.toast(commonBean.getContext(),"使用的是surfaceview...");
            player.setDataSource(commonBean.getContext(), Uri.parse(videoViewToolBean.getUrl()));
            player.prepare();
            player.setOnErrorListener(this);
            player.setOnPreparedListener(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        holder=surfaceView.getHolder();
        holder.addCallback(new MyCallBack());

        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                player.start();
                player.setLooping(true);
            }
        });

        rootlayout.addView(surfaceView);
        rootlayout.clearAnimation();
        commonBean.getLayout().addView(rootlayout);

    }


    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        mediaPlayer.stop();
        LogUtils.toast(context,"视频地址有误...");
        return true;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        LogUtils.toast(context,"准备完毕...");
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }

    private class MyCallBack implements SurfaceHolder.Callback {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            player.setDisplay(holder);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }
}
