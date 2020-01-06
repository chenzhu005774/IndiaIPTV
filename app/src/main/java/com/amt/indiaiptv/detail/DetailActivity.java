package com.amt.indiaiptv.detail;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.amt.indiaiptv.R;
import com.amt.indiaiptv.mvp.MVPBaseActivity;
import com.amt.indiaiptv.utils.DialogCallback;
import com.amt.indiaiptv.utils.LogUtils;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DetailActivity extends MVPBaseActivity<DetailContract.View, DetailPresenter> implements DetailContract.View , View.OnClickListener,View.OnFocusChangeListener ,DialogCallback {

    RelativeLayout parentlayout;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        parentlayout = findViewById(R.id.root);
//        getWindow().setEnterTransition(new Explode().setDuration(1000));
//        getWindow().setExitTransition(new Explode().setDuration(1000));
        mPresenter.getData("ffdab1c1f3a443408d7ed1b52edde537");
    }


    @Override
    public void getDataSuccess(String data) {
        mPresenter.parserView(data,parentlayout,this,this);
    }

    @Override
    public void getDataFail() {
        LogUtils.showDialog(this,"布局失败,点击重试",this);
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

    }

    @Override
    public void clickSure() {
        mPresenter.getData("e2adc9f9ae3c4ffea0986f28e277a84a");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        View rootview = this.getWindow().getDecorView();
        View aaa = rootview.findFocus();
        LogUtils.i("tag", aaa.toString());
        return super.onKeyDown(keyCode, event);

    }
}
