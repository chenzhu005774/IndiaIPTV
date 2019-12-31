package com.amt.indiaiptv.home;


import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HorizontalGridView;
import android.support.v17.leanback.widget.ItemBridgeAdapter;
import android.view.KeyEvent;
import android.widget.Toast;

import com.amt.indiaiptv.R;
import com.amt.indiaiptv.mvp.MVPBaseActivity;
import com.amt.indiaiptv.utils.bean.ViewPagerHolder;
import com.amt.indiaiptv.utils.customizeview.MyVideoView;
import com.amt.indiaiptv.utils.horizontalgridview.TitleModel;
import com.amt.indiaiptv.utils.horizontalgridview.TitlePresenter;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;


/**
 * MVPPlugin
 */

public class HomeActivity extends MVPBaseActivity<HomeContract.View, HomePresenter> implements HomeContract.View {
    private int[] ids ={R.mipmap.b3,R.mipmap.b3,R.mipmap.b3,R.mipmap.b3,R.mipmap.b3,R.mipmap.b3,R.mipmap.b3,R.mipmap.b3};
    private MyVideoView videoview;
    private MZBannerView mMZBannerView;
    HorizontalGridView horizontalGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mPresenter.init();

    }
    @Override
    public void initBaner(){
        mMZBannerView = findViewById(R.id.mz_view_pager);
        mMZBannerView.setPages(mPresenter.mockData(ids), new MZHolderCreator<ViewPagerHolder>() {
            @Override
            public ViewPagerHolder createViewHolder() {
                return new ViewPagerHolder();
            }
        });
        mMZBannerView.setFocusable(false);
    }
    @Override
    public void setVideo() {
        //设置播放加载路径
        videoview =findViewById(R.id.videoview);
        videoview.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.bg));
        //        videoview.setVideoURI(Uri.parse("http://192.168.2.40:9000/fnxn2.mp4"));
        //播放
        videoview.start();
        //设置为静音
        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
                mediaPlayer.setVolume(0f, 0f);
            }
        });
    }
    @Override
    public void initView() {
        horizontalGridView = findViewById(R.id.hg_title);
        horizontalGridView.setHorizontalSpacing(132);
        horizontalGridView.setSelectedPosition(0);
        ArrayObjectAdapter arrayObjectAdapter = new ArrayObjectAdapter(new TitlePresenter());
        ItemBridgeAdapter itemBridgeAdapter = new ItemBridgeAdapter(arrayObjectAdapter);
        //        FocusHighlightHelper.setupBrowseItemFocusHighlight(itemBridgeAdapter, FocusHighlight.ZOOM_FACTOR_LARGE, true);
        horizontalGridView.setAdapter(itemBridgeAdapter);
        arrayObjectAdapter.addAll(0, TitleModel.getTitleList());
        horizontalGridView.requestFocus();
        horizontalGridView.setSelectedPosition(mPresenter.position);
         //        horizontalGridView.setFocusScrollStrategy(FOCUS_SCROLL_PAGE);
        horizontalGridView.postDelayed(new Runnable() {
            @Override
            public void run() {
                horizontalGridView.requestFocus();
                horizontalGridView.requestFocusFromTouch();
            }
        },1000);

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        mPresenter.position=horizontalGridView.getSelectedPosition();
        mPresenter.showDirection(this);
        if (keyCode==KeyEvent.KEYCODE_DPAD_RIGHT){
            mMZBannerView.viewpageMove(false);
        }else if (keyCode==KeyEvent.KEYCODE_DPAD_LEFT){
            mMZBannerView.viewpageMove(true);
        }else if (keyCode==KeyEvent.KEYCODE_DPAD_UP){
            Toast.makeText(this, horizontalGridView.getSelectedPosition()+"", Toast.LENGTH_SHORT).show();
            return true;
        }else if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
            Toast.makeText(this, horizontalGridView.getSelectedPosition()+"", Toast.LENGTH_SHORT).show();
            return true;
        }else if (keyCode == KeyEvent.KEYCODE_BACK) {
            mPresenter.showVideo(this);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


}
