package com.amt.indiaiptv.utils.annima;

import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by Administrator on 2019/5/30.
 */
public class RotationAnimation {
    public void startRotation(View view, float start, float end) {
        // 计算中心点
        final float centerX = view.getWidth() / 2.0f;
        final float centerY = view.getHeight() / 2.0f;
        Log.d("chenzhu", "centerX="+centerX+", centerY="+centerY);
        if(centerX==0||centerY==0){
            return;
        }

        //Z轴的缩放为0
        Rotate3dAnimation rotation =new Rotate3dAnimation(start, end, centerX, centerY, 0f, true);
        rotation.setDuration(1500);
        rotation.setFillAfter(true);
        //rotation.setInterpolator(new AccelerateInterpolator());
        //匀速旋转
        rotation.setInterpolator(new LinearInterpolator());
        view.startAnimation(rotation);
    }
}
