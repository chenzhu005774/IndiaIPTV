package com.amt.indiaiptv.utils.customizeview;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;

/**
 * 滚动textview
 */


public class MarqueeTextView extends AppCompatTextView {
    /** 滚动次数 */
    private int marqueeNum = -1;//-1为永久循环。大于0是循环次数。`
    public void setMarqueeNum(int marqueeNum) {
        this.marqueeNum = marqueeNum;
    }
    public MarqueeTextView(Context context) {
        super(context);
        setAttr();
    }
    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttr();
    }
    public MarqueeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setAttr();
    }
    /**
     * 始终获取焦点
     * 跑马灯在TextView处于焦点状态的时候才会滚动
     * @return
     */
    @Override
    public boolean isFocused() {
        return true;
    }
    /**
     * 设置相关属性
     */
    private void setAttr(){
        this.setEllipsize(TextUtils.TruncateAt.MARQUEE);//设置跑马等效果
        this.setMarqueeRepeatLimit(marqueeNum);//设置跑马灯重复次数
        this.setSingleLine(true);//设置单行
    }
}
//<com.hejunlin.tvsample.view.MarqueeTextView
//        android:layout_centerInParent="true"
//        android:layout_centerVertical="true"
//        android:textSize="30sp"
//        android:textColor="@color/white"
//        android:gravity="center"
//        android:ellipsize="marquee"
//        android:focusable="true"
//        android:singleLine="true"
//        android:marqueeRepeatLimit="marquee_forever"
//        android:focusableInTouchMode="true"
//
//        android:text="华西附二医院点播直播公告请大家在春节期间不要吃油腻视屏 ,关于对信息的描述以及
//        华西附二医院关于新版发布公告测试测试 "
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content" />