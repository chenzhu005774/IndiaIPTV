package com.amt.indiaiptv.utils.img;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * Created by Administrator on 2019/5/28.
 */
public class ViewbgLoader {
    private volatile static ViewbgLoader instance;

    /** Returns singleton class instance */
    public static ViewbgLoader getInstance() {
        if (instance == null) {
            synchronized (ViewbgLoader.class) {
                if (instance == null) {
                    instance = new ViewbgLoader();
                }
            }
        }
        return instance;
    }

    public void  setBg(Context context, String  imgStr , final View view){
        Glide.with(context).load(imgStr).asBitmap().into(new SimpleTarget<Bitmap>() {        //设置宽高
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Drawable drawable = new BitmapDrawable(resource);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    view.setBackground(drawable);    //设置背景
                }
            }
        });
    }
}
