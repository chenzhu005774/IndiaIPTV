package com.amt.indiaiptv.utils.customizeview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.SeekBar;


/**
 * 继承 SeekBar 实现自己的SeekBar
 * 主要是 在tv上 你焦点移到thumb上的时候
 * 左右滑动的时候会导致onkeydown不响应
 * @author cz
*/
@SuppressLint("AppCompatCustomView")
public class MySeekBar extends SeekBar {
        public MySeekBar(Context context) {
                super(context);
        }

        public MySeekBar(Context context, AttributeSet attrs) {
                this(context, attrs, android.R.attr.seekBarStyle);
        }

        public MySeekBar(Context context, AttributeSet attrs, int defStyle) {
                super(context, attrs, defStyle);
        }

        /**
         * onTouchEvent 是在 SeekBar 继承的抽象类 AbsSeekBar 里
         * 你可以看下他们的继承关系
         */
        @Override
        public boolean onTouchEvent(MotionEvent event) {
                return false;
        }

        @Override
        public boolean onKeyDown(int keyCode, KeyEvent event) {
                return false;
        }
}