package com.amt.indiaiptv.utils.commonbean;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2019/5/17.
 */
public final class CommonBean {

    View.OnFocusChangeListener focusChangeListener;
    View.OnClickListener onClickListener;
    Context context;
    RelativeLayout layout; //父布局
    Object tag;//view 视图的tag

    public View.OnFocusChangeListener getFocusChangeListener() {
        return focusChangeListener;
    }

    public void setFocusChangeListener(View.OnFocusChangeListener focusChangeListener) {
        this.focusChangeListener = focusChangeListener;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    public RelativeLayout getLayout() {
        return layout;
    }

    public void setLayout(RelativeLayout layout) {
        this.layout = layout;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }
}
