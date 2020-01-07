package com.amt.indiaiptv.home;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.FocusHighlight;
import android.support.v17.leanback.widget.FocusHighlightHelper;
import android.support.v17.leanback.widget.HorizontalGridView;
import android.support.v17.leanback.widget.ItemBridgeAdapter;
import android.view.KeyEvent;

import com.amt.indiaiptv.R;
import com.amt.indiaiptv.detail.DetailActivity;
import com.amt.indiaiptv.mvp.MVPBaseActivity;
import com.amt.indiaiptv.utils.DialogCallback;
import com.amt.indiaiptv.utils.LogUtils;
import com.amt.indiaiptv.utils.bean.ViewPagerHolder;
import com.amt.indiaiptv.utils.customizeview.MyVideoView;
import com.amt.indiaiptv.utils.horizontalgridview.TitleModel;
import com.amt.indiaiptv.utils.horizontalgridview.TitlePresenter;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;



/**
 * MVPPlugin
 */

public class HomeActivity extends MVPBaseActivity<HomeContract.View, HomePresenter> implements HomeContract.View,DialogCallback {
    private int[] ids ={R.mipmap.b3,R.mipmap.b3,R.mipmap.b3,R.mipmap.b3,R.mipmap.b3,R.mipmap.b3,R.mipmap.b3};
    private MyVideoView videoview;
    private MZBannerView mMZBannerView;
    HorizontalGridView horizontalGridView;
    TitlePresenter titlePresenter;
    ArrayObjectAdapter arrayObjectAdapter ;
    ItemBridgeAdapter itemBridgeAdapter;
    private static double DOUBLE_CLICK_TIME = 0L; //防止一直按着不放
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mPresenter.init("4b52aa86460d4ef6ad99cbbe6e0471d9");

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
        //播放
        //设置为静音
        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
                mediaPlayer.setVolume(0f, 0f);
            }
        });
        videoview.start();
    }

    @Override
    public void getDataFail() {

    }

    @Override
    public void initView() {

        horizontalGridView = findViewById(R.id.hg_title);
        horizontalGridView.setHorizontalSpacing(132);
        titlePresenter =new TitlePresenter();
        arrayObjectAdapter = new ArrayObjectAdapter(titlePresenter);
        itemBridgeAdapter = new ItemBridgeAdapter(arrayObjectAdapter);


        horizontalGridView.setAdapter(itemBridgeAdapter);
        arrayObjectAdapter.addAll(0, TitleModel.getTitleList());
        horizontalGridView.requestFocus();
        horizontalGridView.setSelectedPosition(mPresenter.position);
        horizontalGridView.postDelayed(new Runnable() {
            @Override
            public void run() {
                horizontalGridView.requestFocus();
                horizontalGridView.requestFocusFromTouch();
            }
        },1000);

        FocusHighlightHelper.setupBrowseItemFocusHighlight(itemBridgeAdapter, FocusHighlight.ZOOM_FACTOR_LARGE, false);
    }




    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode==KeyEvent.KEYCODE_DPAD_RIGHT){

            if((System.currentTimeMillis() - DOUBLE_CLICK_TIME)>80){
                mMZBannerView.viewpageMove(false);
                mPresenter.position=horizontalGridView.getSelectedPosition();
                mPresenter.showDirection(this);
                if (mPresenter.position==6){
                    itemBridgeAdapter.notifyItemChanged(6);
                    horizontalGridView.scrollToPosition(0);
                }

            }else{
               return true;
            }

            DOUBLE_CLICK_TIME = System.currentTimeMillis();

        }else if (keyCode==KeyEvent.KEYCODE_DPAD_LEFT){
            if((System.currentTimeMillis() - DOUBLE_CLICK_TIME)>80) {
                mPresenter.position = horizontalGridView.getSelectedPosition();
                mPresenter.showDirection(this);
                mMZBannerView.viewpageMove(true);
                if (mPresenter.position == 0) {
                    horizontalGridView.scrollToPosition(6);
                }
            }else {
                return  true;
            }
            DOUBLE_CLICK_TIME = System.currentTimeMillis();
        }else if (keyCode==KeyEvent.KEYCODE_DPAD_UP){
            Intent intent = new Intent(HomeActivity.this, DetailActivity.class);
            startActivity(intent);

            return true;
        }else if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
            Intent intent = new Intent(HomeActivity.this, DetailActivity.class);
            startActivity(intent);
            return true;
        }else if (keyCode == KeyEvent.KEYCODE_BACK) {

            if((System.currentTimeMillis() - DOUBLE_CLICK_TIME)<500) {
                LogUtils.showDialog(this,"是否退出",this);
            }
            DOUBLE_CLICK_TIME = System.currentTimeMillis();
            mPresenter.showVideo(this);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (videoview!=null)
        videoview.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (videoview!=null)
        videoview.pause();

    }

    @Override
    public void clickSure() {
        this.finish();
    }
}
