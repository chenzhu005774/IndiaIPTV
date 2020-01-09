package com.amt.indiaiptv.detail;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.amt.indiaiptv.R;
import com.amt.indiaiptv.mvp.MVPBaseActivity;
import com.amt.indiaiptv.utils.DialogCallback;
import com.amt.indiaiptv.utils.LogUtils;
import com.amt.indiaiptv.utils.img.ViewbgLoader;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DetailActivity extends MVPBaseActivity<DetailContract.View, DetailPresenter> implements DetailContract.View , View.OnClickListener,View.OnFocusChangeListener ,DialogCallback {

    RelativeLayout parentlayout;
    String code="";
    String backUrl ="";
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        parentlayout = findViewById(R.id.root);
        code =getIntent().getStringExtra("code");
        backUrl=getIntent().getStringExtra("backUrl");
        if (code==null){
          LogUtils.showDialog(this,"code为空");
            return;
        }

        if (backUrl!=null){
            ViewbgLoader.getInstance().setBg(DetailActivity.this, backUrl,parentlayout);
        }

        mPresenter.getData(code);
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
        mPresenter.getData(code);
    }



}
