package com.amt.indiaiptv.utils.toolview;


import android.util.TypedValue;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amt.indiaiptv.R;
import com.amt.indiaiptv.utils.Constant;
import com.amt.indiaiptv.utils.commonbean.CommonBean;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;


/**
 * Created by Administrator on 2019/4/3.
 * 一般的图片焦点控件
 * 布局中包含一个图片
 * 焦点在view布局上
 */
final public class TextViewTool {
    public void creatView(TextViewToolBean textViewToolBean,CommonBean commonBean ){
        RelativeLayout rootlayout = new RelativeLayout(commonBean.getContext());
        rootlayout.setOnFocusChangeListener(commonBean.getFocusChangeListener());
        rootlayout.setFocusable(textViewToolBean.focus);
        rootlayout.requestFocus();
        if (textViewToolBean.focus) {
            rootlayout.setBackgroundResource(R.drawable.bgseletor);
        }

        RelativeLayout.LayoutParams params =  new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        if (textViewToolBean.focus) {
            //有背景框的话 就减去背景框的长度
            params.setMargins(textViewToolBean.getMarleft()- Constant.margin,textViewToolBean.getMartop()-Constant.margin,0,0);
        }else{
            params.setMargins(textViewToolBean.getMarleft() ,textViewToolBean.getMartop() ,0,0);
        }

        rootlayout.setLayoutParams(params);
        rootlayout.setTag(commonBean.getTag());
        rootlayout.setOnClickListener(commonBean.getOnClickListener());

        RelativeLayout.LayoutParams imgparams =  new RelativeLayout.LayoutParams(textViewToolBean.getWidth(),textViewToolBean.getHeigh());
        imgparams.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);//居中显示
        TextView textView = new TextView(commonBean.getContext());
        textView.setWidth(textViewToolBean.getWidth());
        textView.setHeight(textViewToolBean.getHeigh());
        textView.setGravity(Gravity.CENTER);
        textView.setText(textViewToolBean.getText());
//        TypedValue.COMPLEX_UNIT_PX : Pixels
//        TypedValue.COMPLEX_UNIT_SP : Scaled Pixels
//        TypedValue.COMPLEX_UNIT_DIP : Device Independent Pixels
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,textViewToolBean.getTextsize());
        textView.setTextColor(commonBean.getContext().getResources().getColor(R.color.white));
        rootlayout.addView(textView);
        commonBean.getLayout().addView(rootlayout);

    }

}
