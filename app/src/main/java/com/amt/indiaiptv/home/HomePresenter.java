package com.amt.indiaiptv.home;

import android.app.Activity;
import android.content.Context;
import android.support.v17.leanback.widget.HorizontalGridView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.amt.indiaiptv.R;
import com.amt.indiaiptv.mvp.BasePresenterImpl;
import com.amt.indiaiptv.utils.bean.DataEntry;
import com.amt.indiaiptv.utils.customizeview.MyVideoView;
import com.zhouwei.mzbanner.MZBannerView;

import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 */

public class HomePresenter extends BasePresenterImpl<HomeContract.View> implements HomeContract.Presenter{

    int position=0;
    @Override
    public void init() {
        mView.initBaner();
        mView.initView();
        mView.setVideo();
    }

    @Override
    public List<DataEntry> mockData(int[] ids){
        List<DataEntry> list = new ArrayList<>();
        DataEntry dataEntry = null;
        for(int i=0;i<ids.length;i++){
            dataEntry = new DataEntry();
            dataEntry.resId = ids[i];
            dataEntry.desc = "这是描述"+i;
            list.add(dataEntry);
        }

        return list;
    }

    @Override
    public void showVideo(Activity activity) {
      MZBannerView mMZBannerView =activity.findViewById(R.id.mz_view_pager);
      ImageView arr_left =activity.findViewById(R.id.arr_left);
      ImageView arr_right =activity.findViewById(R.id.arr_right);
      RelativeLayout info =activity.findViewById(R.id.info);
      final HorizontalGridView hg_title = activity.findViewById(R.id.hg_title);

      if (info.getVisibility()== View.VISIBLE){
          mMZBannerView.setVisibility(View.GONE);
          arr_left.setVisibility(View.GONE);
          arr_right.setVisibility(View.GONE);
          info.setVisibility(View.GONE);
          hg_title.setVisibility(View.GONE);
          position = hg_title.getSelectedPosition();
      }else {
          mMZBannerView.setVisibility(View.VISIBLE);
          arr_left.setVisibility(View.VISIBLE);
          arr_right.setVisibility(View.VISIBLE);
          info.setVisibility(View.VISIBLE);
          hg_title.setVisibility(View.VISIBLE);
          hg_title.postDelayed(new Runnable() {
              @Override
              public void run() {
                  hg_title.requestFocus();
                  hg_title.requestFocusFromTouch();
              }
          },100);
          hg_title.setSelectedPosition(position);

      }

    }

    @Override
    public void showDirection(Activity activity) {
        System.out.println("position：：："+position);
        ImageView arr_left =activity.findViewById(R.id.arr_left);
        ImageView arr_right =activity.findViewById(R.id.arr_right);
        if (position<1){
            arr_left.setImageResource(R.drawable.arrowleftnl);
            arr_left.setAlpha(0.5f);
        }else if (position>5){
            arr_right.setImageResource(R.drawable.arrowrightnl);
            arr_right.setAlpha(0.5f);
        }else {
            arr_left.setImageResource(R.drawable.arrowleft);
            arr_left.setAlpha(1f);
            arr_right.setImageResource(R.drawable.arrowright);
            arr_right.setAlpha(1f);
        }
    }


}
