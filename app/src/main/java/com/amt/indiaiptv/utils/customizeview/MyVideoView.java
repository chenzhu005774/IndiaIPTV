package com.amt.indiaiptv.utils.customizeview;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.VideoView;

import javax.net.ssl.HttpsURLConnection;

public class MyVideoView extends VideoView{
    public MyVideoView(Context context) {
        super(context);
    }

    public MyVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyVideoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getDefaultSize(getWidth(), widthMeasureSpec);
        int height = getDefaultSize(getHeight(), heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    public void setVideoURI(Uri uri) {
        super.setVideoURI(uri);
        try {
            HttpsURLConnection.setDefaultSSLSocketFactory(SSlUtiles.createSSLSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new SSlUtiles.TrustAllHostnameVerifier());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
