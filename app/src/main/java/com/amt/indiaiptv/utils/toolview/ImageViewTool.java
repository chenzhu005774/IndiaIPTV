package com.amt.indiaiptv.utils.toolview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.amt.indiaiptv.R;
import com.amt.indiaiptv.utils.Constant;
import com.amt.indiaiptv.utils.commonbean.CommonBean;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by Administrator on 2019/4/3.
 * 一般的图片焦点控件
 * 布局中包含一个图片
 * 焦点在view布局上
 */
final public class ImageViewTool  {
    public void creatView(final ImageViewToolBean imageViewToolBean , CommonBean commonBean ){
        RelativeLayout rootlayout = new RelativeLayout(commonBean.getContext());
//        rootlayout.setOnFocusChangeListener(commonBean.getFocusChangeListener());

        rootlayout.setFocusable(imageViewToolBean.focus);
        if (imageViewToolBean.focus) {
            rootlayout.setBackgroundResource(R.drawable.bgseletor);
        }

        RelativeLayout.LayoutParams params =  new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        if (imageViewToolBean.focus) {
            //有背景框的话 就减去背景框的长度
            params.setMargins(imageViewToolBean.getMarleft()- Constant.margin,imageViewToolBean.getMartop()- Constant.margin,0,0);
        }else{
            params.setMargins(imageViewToolBean.getMarleft() ,imageViewToolBean.getMartop() ,0,0);
        }

        rootlayout.setLayoutParams(params);
        rootlayout.setTag(commonBean.getTag());
        rootlayout.setOnClickListener(commonBean.getOnClickListener());

        RelativeLayout.LayoutParams imgparams =  new RelativeLayout.LayoutParams(imageViewToolBean.getWidth(),imageViewToolBean.getHeigh());
        imgparams.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
        final ImageView imageView = new ImageView(commonBean.getContext());
        imageView.setImageResource(R.mipmap.bg_main);
        imageView.setTag(commonBean.getTag());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(imgparams);
        if (null!=imageViewToolBean.getUrl()&&imageViewToolBean.getUrl().contains("http")) {
            ImageLoader.getInstance().displayImage(imageViewToolBean.getUrl(), imageView);
            System.out.println("imageViewToolBean.getUrl():"+imageViewToolBean.getUrl());
        }else {
            byte[] decodedString = Base64.decode(imageViewToolBean.getUrl().split(",")[1], Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imageView.setImageBitmap(decodedByte);
        }

        rootlayout.addView(imageView);
        rootlayout.requestFocus();
        commonBean.getLayout().addView(rootlayout);
        rootlayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
              ImageViewToolBean  tagbean = (ImageViewToolBean) view.getTag();

                if (b){
                    if (Integer.valueOf(tagbean.getFocustype())==2) {
                        view.setBackgroundResource(0); //去掉默认的焦点框 不要发光焦点框
                        ImageLoader.getInstance().displayImage(tagbean.getFocuspicurl(), imageView);
                    }
                }else {
                    if (Integer.valueOf(tagbean.getFocustype())==2) {
                        ImageLoader.getInstance().displayImage(tagbean.getUrl(), imageView);
                    }
                }
            }
        });
    }


}
