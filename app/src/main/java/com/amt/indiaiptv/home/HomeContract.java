package com.amt.indiaiptv.home;


import android.app.Activity;
import com.amt.indiaiptv.mvp.BasePresenter;
import com.amt.indiaiptv.mvp.BaseView;
import com.amt.indiaiptv.utils.bean.DataEntry;

import java.util.List;

/**
 * MVPPlugin
 */

public class HomeContract {
    interface View extends BaseView {
        void  initView();
        void  initBaner();
        void  setVideo();
        void  getDataFail();
    }

    interface  Presenter extends BasePresenter<View> {
        void init(String code);
        List<DataEntry> mockData(int[] ids);
        void showVideo(Activity activity);
        void showDirection(Activity activity );
    }
}
