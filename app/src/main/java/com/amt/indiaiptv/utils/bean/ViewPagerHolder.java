package com.amt.indiaiptv.utils.bean;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amt.indiaiptv.R;
import com.amt.indiaiptv.utils.Constant;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.zhouwei.mzbanner.holder.MZViewHolder;

public   class ViewPagerHolder implements MZViewHolder<DataEntry> {
        private ImageView mImageView;
        private TextView mTitle;
        private TextView mDesc;
        @Override
        public View createView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.normal_banner_item,null);
            mImageView =  view.findViewById(R.id.normal_banner_image);
            mDesc =  view.findViewById(R.id.page_desc);

            return view;
        }

        @Override
        public void onBind(Context context, int position, DataEntry data) {
//            mImageView.setImageResource(data.resId);
            if(data.resId!=0){
                mImageView.setImageResource(data.resId);
            }else {
//                ImageLoader.getInstance().displayImage(data.picUrl, mImageView);

                DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(R.mipmap.bg_main) // 设置图片在下载期间显示的图片
                        .showImageForEmptyUri(R.mipmap.bg_main)// 设置图片Uri为空或是错误的时候显示的图片
                        .showImageOnFail(R.mipmap.bg_main) // 设置图片加载/解码过程中错误时候显示的图片
                        .cacheInMemory(true)// 设置下载的图片是否缓存在内存中
                        .cacheOnDisk(true)// 设置下载的图片是否缓存在SD卡中
                        .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)// 设置图片以如何的编码方式显示
                        .bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型//
                        .resetViewBeforeLoading(true).build();// 设置图片在下载前是否重置，复位


                ImageLoader.getInstance().displayImage(data.picUrl, mImageView,options, new ImageLoadingListener() {

                    @Override
                    public void onLoadingStarted(String arg0, View arg1) {
                        // TODO Auto-generated method stub
                        mDesc.setVisibility(View.VISIBLE);
                        mDesc.setText("");
                    }

                    @Override
                    public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
                        // TODO Auto-generated method stub
                        mDesc.setVisibility(View.VISIBLE);
                        mDesc.setText("图片加载失败");
                    }

                    @Override
                    public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
                        // TODO Auto-generated method stub
                        mDesc.setVisibility(View.GONE);
                    }

                    @Override
                    public void onLoadingCancelled(String arg0, View arg1) {
                        // TODO Auto-generated method stub
                        mDesc.setVisibility(View.GONE);
                    }
                },new ImageLoadingProgressListener (){

                    @Override
                    public void onProgressUpdate(String imageUri, View view, int current, int total) {
                        int a =Math.round(100.0f * current / total);
                        mDesc.setText(a+"%");
                    }
                });


            }

        }
    }