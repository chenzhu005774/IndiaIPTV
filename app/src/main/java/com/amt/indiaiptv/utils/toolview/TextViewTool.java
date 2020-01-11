package com.amt.indiaiptv.utils.toolview;


import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amt.indiaiptv.R;
import com.amt.indiaiptv.utils.Constant;
import com.amt.indiaiptv.utils.commonbean.CommonBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;


/**
 * Created by Administrator on 2019/4/3.
 * 一般的图片焦点控件
 * 布局中包含一个图片
 * 焦点在view布局上
 *外层margin  =文字margin+ 焦点图片偏移(<0) --
 * 外层宽高 =warp_content                  --
 *                                        --
 * 焦点图片宽高 = 接口获取实际宽高           --
 * 焦点图片margin = 无                     --
 *                                        --
 * 文字宽高 = 接口获取实际宽高的             --
 * 文字margin = 焦点图片的偏移量            --
 *----------以上逻辑已经废弃-----------------
 */
final public class TextViewTool {
    public void creatView(TextViewToolBean textViewToolBean,CommonBean commonBean ){
    RelativeLayout.LayoutParams txtparams =  new RelativeLayout.LayoutParams(textViewToolBean.getWidth(),textViewToolBean.getHeigh());

        final ImageView focus = new ImageView(commonBean.getContext());
       
         
        if (textViewToolBean.focus&&textViewToolBean.getFocustype()==0) {
            //有背景框的话 就减去背景框的长度
            txtparams.setMargins(textViewToolBean.getMarleft()-Constant.margin,textViewToolBean.getMartop()-Constant.margin,0,0);
        }else  if (textViewToolBean.focus&&textViewToolBean.getFocustype()==1) {
            //有背景框的话 就减去背景框的长度
            txtparams.setMargins(textViewToolBean.getMarleft(),textViewToolBean.getMartop(),0,0);

            //添加一个焦点背景图
            RelativeLayout.LayoutParams focusparams =  new RelativeLayout.LayoutParams(textViewToolBean.getFocuswidth(),textViewToolBean.getFocusheigh());
            focusparams.setMargins(textViewToolBean.getMarleft()+textViewToolBean.getFoculeft(),
                    textViewToolBean.getMartop()+textViewToolBean.getFocustop(),
                    0,0);

            focus.setScaleType(ImageView.ScaleType.FIT_XY);
            focus.setFocusable(false);
            focus.setLayoutParams(focusparams);
            focus.setVisibility(View.INVISIBLE);
            ImageLoader.getInstance().displayImage(textViewToolBean.getFocuspicurl(), focus);
            commonBean.getLayout().addView(focus);
            
        }else if (textViewToolBean.focus&&textViewToolBean.getFocustype()==2) {

        } else {
            txtparams.setMargins(textViewToolBean.getMarleft(),textViewToolBean.getMartop(),0,0);
        }

        txtparams.addRule(Gravity.CENTER,RelativeLayout.TRUE);//居中显示
        final TextView textView = new TextView(commonBean.getContext());
        textView.setFocusable(textViewToolBean.focus);
        textView.setFocusableInTouchMode(textViewToolBean.focus); 
        textView.setLayoutParams(txtparams);
        textView.setGravity(Gravity.CENTER);
        textView.setTag(commonBean.getTag());
        textView.setText(textViewToolBean.getText());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,textViewToolBean.getTextsize());
        textView.setTextColor(commonBean.getContext().getResources().getColor(R.color.white));
        if (textViewToolBean.focus){
            textView.requestFocus();
            textView.requestFocusFromTouch();
            textView.setFocusable(true);
            textView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    textView.requestFocus();
                    textView.requestFocusFromTouch();
                }
            },100);
        }
        commonBean.getLayout().addView(textView);
        textView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                TextViewToolBean  tagbean = (TextViewToolBean) view.getTag();

                if (b){
                    if (Integer.valueOf(tagbean.getFocustype())==2) {//0是默认焦点 1 是显示焦点图片 2是图片替换

                    }else if (Integer.valueOf(tagbean.getFocustype())==1){
                        focus.setVisibility(View.VISIBLE);
                    }
                }else {
                    if (Integer.valueOf(tagbean.getFocustype())==2) {

                    }else if (Integer.valueOf(tagbean.getFocustype())==1){
                        focus.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });


    }

}
