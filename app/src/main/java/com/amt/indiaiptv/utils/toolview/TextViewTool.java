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
 *--------------------------------------
 */
final public class TextViewTool {
    public void creatView(TextViewToolBean textViewToolBean,CommonBean commonBean ){
//        RelativeLayout rootlayout = new RelativeLayout(commonBean.getContext());
//
//        RelativeLayout.LayoutParams params =  new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
//        if (textViewToolBean.focus&&textViewToolBean.getFocustype()==0) {
//            //有背景框的话 就减去背景框的长度
//            params.setMargins(textViewToolBean.getMarleft()- Constant.margin,textViewToolBean.getMartop()-Constant.margin,0,0);
//        }else if (textViewToolBean.focus&&textViewToolBean.getFocustype()==1) {
//            //有背景框的话 就减去背景框的长度
//           if (textViewToolBean.getFocustop()>0) {
//               params.setMargins(textViewToolBean.getMarleft() + textViewToolBean.getFoculeft(), textViewToolBean.getMartop() - textViewToolBean.getFocustop(), 0, 0);
//           }else {
//               params.setMargins(textViewToolBean.getMarleft() + textViewToolBean.getFoculeft(), textViewToolBean.getMartop()+ textViewToolBean.getFocustop(), 0, 0);
//
//           }
//           }else{
//            params.setMargins(textViewToolBean.getMarleft()- Constant.margin,textViewToolBean.getMartop()-Constant.margin,0,0);
//        }
//
//        rootlayout.setLayoutParams(params);
//        rootlayout.setTag(commonBean.getTag());
//        rootlayout.setFocusable(false);
//
//             // 焦点背景图
//        final ImageView focus = new ImageView(commonBean.getContext());
//        if (textViewToolBean.focus) {
//            //实现焦点背景    0是默认焦点 1 是显示焦点图片 2是图片替换
//            rootlayout.setOnClickListener(commonBean.getOnClickListener());
//            rootlayout.setFocusable(true);
//            if (textViewToolBean.focus) {
//                if (Integer.valueOf(textViewToolBean.getFocustype())==0) {
//                    rootlayout.setBackgroundResource(R.drawable.bgseletor);
//                }else  if (Integer.valueOf(textViewToolBean.getFocustype())==1){
//                    RelativeLayout.LayoutParams focusparams =  new RelativeLayout.LayoutParams(textViewToolBean.getFocuswidth(),textViewToolBean.getFocusheigh());
////                    focusparams.setMargins(textViewToolBean.getFoculeft(),textViewToolBean.getFocustop(),0,0);
//                    focus.setTag(commonBean.getTag());
//                    focus.setScaleType(ImageView.ScaleType.FIT_XY);
//                    focus.setLayoutParams(focusparams);
////                    focus.setBackgroundColor(Color.RED);
//                    focus.setVisibility(View.INVISIBLE);
//                    ImageLoader.getInstance().displayImage(textViewToolBean.getFocuspicurl(), focus);
//                    rootlayout.addView(focus);
//                }else if (Integer.valueOf(textViewToolBean.getFocustype())==2){
//
//                }
//            }
//
//
//        }
//
//
//          //文字
//        RelativeLayout.LayoutParams txtparams =  new RelativeLayout.LayoutParams(textViewToolBean.getWidth(),textViewToolBean.getHeigh());
//
//        if (textViewToolBean.focus&&textViewToolBean.getFocustype()==1) {
//            //有背景框的话 就减去背景框的长度
//            txtparams.setMargins(-(textViewToolBean.getFoculeft()),-(textViewToolBean.getFocustop()),0,0);
//        }else {
//            txtparams.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);//居中显示
//        }
//        txtparams.addRule(Gravity.CENTER,RelativeLayout.TRUE);//居中显示
//        TextView textView = new TextView(commonBean.getContext());
//        textView.setLayoutParams(txtparams);
////        textView.setWidth(textViewToolBean.getWidth());
////        textView.setHeight(textViewToolBean.getHeigh());
//        textView.setGravity(Gravity.CENTER);
//        textView.setText(textViewToolBean.getText());
////        TypedValue.COMPLEX_UNIT_PX : Pixels
////        TypedValue.COMPLEX_UNIT_SP : Scaled Pixels
////        TypedValue.COMPLEX_UNIT_DIP : Device Independent Pixels
//        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,textViewToolBean.getTextsize());
//        textView.setTextColor(commonBean.getContext().getResources().getColor(R.color.white));
//        rootlayout.addView(textView);
//        if (textViewToolBean.focus){
//            rootlayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View view, boolean b) {
//                    TextViewToolBean  tagbean = (TextViewToolBean) view.getTag();
//                    if (b){
//                        if (Integer.valueOf(tagbean.getFocustype())==2) {//0是默认焦点 1 是显示焦点图片 2是图片替换
//
//                        }else if (Integer.valueOf(tagbean.getFocustype())==1){
//                            focus.setVisibility(View.VISIBLE);
//                        }
//                    }else {
//                        if (Integer.valueOf(tagbean.getFocustype())==2) {
//
//                        }else if (Integer.valueOf(tagbean.getFocustype())==1){
//                            focus.setVisibility(View.INVISIBLE);
//                        }
//                    }
//                }
//            });
//        }
//
//        commonBean.getLayout().addView(rootlayout);



                  //文字
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
        TextView textView = new TextView(commonBean.getContext());
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
            textView.setFocusable(true);
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
