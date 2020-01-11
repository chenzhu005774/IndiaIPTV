package com.amt.indiaiptv.detail;

import android.content.Context;
import android.widget.RelativeLayout;

import com.amt.indiaiptv.mvp.BasePresenter;
import com.amt.indiaiptv.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DetailContract {
    interface View extends BaseView {
       void getDataSuccess(String data);
       void getDataFail();
       void parserViewSuccess();
    }

    interface  Presenter extends BasePresenter<View> {
        void getData(String code);

        void parserView(String data, RelativeLayout parentlayout, android.view.View.OnFocusChangeListener onFocusChangeListener, android.view.View.OnClickListener onClickListener);
    }
}
