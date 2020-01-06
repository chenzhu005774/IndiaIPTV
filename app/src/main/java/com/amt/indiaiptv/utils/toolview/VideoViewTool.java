package com.amt.indiaiptv.utils.toolview;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.amt.indiaiptv.R;
import com.amt.indiaiptv.utils.Constant;
import com.amt.indiaiptv.utils.LogUtils;
import com.amt.indiaiptv.utils.commonbean.CommonBean;
import com.amt.indiaiptv.utils.customizeview.MyVideoView;


/**
 * setOnPreparedListener
 * Created by Administrator on 2019/4/3.
 * https://blog.csdn.net/pingqingbo/article/details/24922071
 */
final public class VideoViewTool implements MediaPlayer.OnErrorListener,MediaPlayer.OnPreparedListener,OnFocusChangeListener {
    Context context  =null;
    MyVideoView videoView =null;
    public void creatview(VideoViewToolBean videoViewToolBean,CommonBean commonBean){
        context =commonBean.getContext();
        RelativeLayout rootlayout = new RelativeLayout(commonBean.getContext());
        rootlayout.setOnFocusChangeListener(this);

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
        RelativeLayout.LayoutParams videopar = new RelativeLayout.LayoutParams(videoViewToolBean.getWidth(),videoViewToolBean.getHeigh());
        videopar.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
        videoView = new MyVideoView(commonBean.getContext());
        videoView.setLayoutParams(videopar);

        videoView.setFocusable(false);
        videoView.setId(R.id.videoview);
        rootlayout.addView(videoView);
        rootlayout.clearAnimation();
        commonBean.getLayout().addView(rootlayout);

        //设置视频路径
        videoView.setVideoURI(Uri.parse(videoViewToolBean.getUrl()));
        videoView.setOnErrorListener(this);
        rootlayout.requestFocus();
        videoView.setOnPreparedListener(this);

    }


    public void pause(){
        try {
             videoView.pause();
        }catch (Exception e){

        }
    }
    public void start(){
        try {
            videoView.start();
        }catch (Exception e){

        }
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        videoView.stopPlayback();
        LogUtils.toast(context,"视频地址有误...");
        return true;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }

    @Override
    public void onFocusChange(View view, boolean b) {
//        设置动画
//        if (b){
//                rotationAnimation.startRotation(view, 0, 360);
//        }else {
//            view.clearAnimation();
//        }
    }
}
