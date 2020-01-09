package com.amt.indiaiptv.utils.bean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amt.indiaiptv.R;
import com.amt.indiaiptv.utils.Constant;
import com.nostra13.universalimageloader.core.ImageLoader;
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
                ImageLoader.getInstance().displayImage(data.picUrl, mImageView);
            }
            mDesc.setText(data.desc);
        }
    }