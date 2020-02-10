package com.amt.indiaiptv.utils.toolview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.amt.indiaiptv.R;
import com.amt.indiaiptv.utils.Constant;
import com.amt.indiaiptv.utils.commonbean.CommonBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

/**
 * Created by Administrator on 2019/4/3.
 * 一般的图片焦点控件
 * 布局中包含一个图片
 * 焦点在view布局上
 *---------------有焦点的逻辑------------
 * 外层margin  =图片的margin
 * 外层宽高 =warp_content              --
 *                                    --
 * 焦点图片宽高 = 接口获取实际宽高       --
 * 焦点图片margin = 如果 margintop>0   --
 *                 那么 margintop=焦点 --
 *                 图片偏移量          --
 *                                    --
 *                                    --
 * 图片宽高 = 接口获取实际宽高的         --
 * 图片margin = 焦点图片的偏移量        --
 *----------以上逻辑已经废弃--------------
 *
 *
 */
final public class ImageViewTool  {
    public void creatView(final ImageViewToolBean imageViewToolBean , CommonBean commonBean ){
        RelativeLayout.LayoutParams imgparams =  new RelativeLayout.LayoutParams(imageViewToolBean.getWidth(),imageViewToolBean.getHeigh());
        final ImageView imageView = new ImageView(commonBean.getContext());
        final ImageView focus = new ImageView(commonBean.getContext());
        if (imageViewToolBean.focus&&imageViewToolBean.getFocustype()==1){
            imgparams.setMargins(imageViewToolBean.getMarleft(),  imageViewToolBean.getMartop(), 0, 0);
            //添加一个焦点背景图
            RelativeLayout.LayoutParams focusparams =  new RelativeLayout.LayoutParams(imageViewToolBean.getFocuswidth(),imageViewToolBean.getFocusheigh());
            focusparams.setMargins(imageViewToolBean.getMarleft()+imageViewToolBean.getFoculeft(),
                                   imageViewToolBean.getMartop()+imageViewToolBean.getFocustop(),
                                       0,0);

            focus.setScaleType(ImageView.ScaleType.FIT_XY);
            focus.setFocusable(false);
            focus.setLayoutParams(focusparams);
            focus.setVisibility(View.INVISIBLE);
            DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(R.mipmap.bg_main) // 设置图片在下载期间显示的图片
                    .showImageForEmptyUri(R.mipmap.bg_main)// 设置图片Uri为空或是错误的时候显示的图片
                    .showImageOnFail(R.mipmap.bg_main) // 设置图片加载/解码过程中错误时候显示的图片
                    .cacheInMemory(true)// 设置下载的图片是否缓存在内存中
                    .cacheOnDisk(true)// 设置下载的图片是否缓存在SD卡中
                    .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)// 设置图片以如何的编码方式显示
                    .bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型//
                    .resetViewBeforeLoading(true).build();// 设置图片在下载前是否重置，复位
            ImageLoader.getInstance().displayImage(imageViewToolBean.getFocuspicurl(), focus,options);
            commonBean.getLayout().addView(focus);

        }else if(imageViewToolBean.focus&&imageViewToolBean.getFocustype()==0) {
            imgparams.setMargins(imageViewToolBean.getMarleft()-Constant.margin,  imageViewToolBean.getMartop()-Constant.margin, 0, 0);
            imageView.setBackgroundResource(R.drawable.bgseletor);
        }else if (imageViewToolBean.focus&&imageViewToolBean.getFocustype()==2){
                // TODO 暂时不用 图片替换
            imgparams.setMargins(imageViewToolBean.getMarleft(),  imageViewToolBean.getMartop(), 0, 0);

        }else {
            imgparams.setMargins(imageViewToolBean.getMarleft(),  imageViewToolBean.getMartop(), 0, 0);
        }
        imageView.setFocusable(imageViewToolBean.focus);
        imageView.setFocusableInTouchMode(imageViewToolBean.focus);
        imageView.setTag(commonBean.getTag());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(imgparams);
        if (null!=imageViewToolBean.getUrl()&&imageViewToolBean.getUrl().contains("http")) {

            DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(R.mipmap.bg_main) // 设置图片在下载期间显示的图片
                    .showImageForEmptyUri(R.mipmap.bg_main)// 设置图片Uri为空或是错误的时候显示的图片
                    .showImageOnFail(R.mipmap.bg_main) // 设置图片加载/解码过程中错误时候显示的图片
                    .cacheInMemory(true)// 设置下载的图片是否缓存在内存中
                    .cacheOnDisk(true)// 设置下载的图片是否缓存在SD卡中
                    .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)// 设置图片以如何的编码方式显示
                    .bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型//
                    .resetViewBeforeLoading(true).build();// 设置图片在下载前是否重置，复位
            ImageLoader.getInstance().displayImage(imageViewToolBean.getUrl(), imageView,options);


            System.out.println("imageViewToolBean.getUrl():"+imageViewToolBean.getUrl());
        }else {
            byte[] decodedString = Base64.decode(imageViewToolBean.getUrl().split(",")[1], Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imageView.setImageBitmap(decodedByte);
        }

        if (imageViewToolBean.focus){
            imageView.requestFocus();
            imageView.requestFocusFromTouch();
            imageView.setFocusable(true);
            imageView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    imageView.requestFocus();
                    imageView.requestFocusFromTouch();
                }
            },100);
        }

        commonBean.getLayout().addView(imageView);
        imageView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
              ImageViewToolBean  tagbean = (ImageViewToolBean) view.getTag();

                if (b){
                    if (Integer.valueOf(tagbean.getFocustype())==2) {//0是默认焦点 1 是显示焦点图片 2是图片替换
                        ImageLoader.getInstance().displayImage(imageViewToolBean.getFocuspicurl(), imageView);
                        imageView.requestFocus();
                    }else if (Integer.valueOf(tagbean.getFocustype())==1){
                        focus.setVisibility(View.VISIBLE);
                        imageView.requestFocus();
                    }
                }else {
                    if (Integer.valueOf(tagbean.getFocustype())==2) {

                        ImageLoader.getInstance().displayImage(imageViewToolBean.getUrl(), imageView);

                    }else if (Integer.valueOf(tagbean.getFocustype())==1){
                        focus.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });


    }


}
