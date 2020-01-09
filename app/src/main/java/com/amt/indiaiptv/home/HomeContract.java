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
        void  initView(List<DataEntry> list);
        void  initBaner(List<DataEntry> list);
        void  setVideo();
        void  getDataFail();
        void reGetDataSuccess(String data);
    }

    interface  Presenter extends BasePresenter<View> {
        void init(String code);
        void reinit(String code);
        List<DataEntry> mockData(int[] ids);
        void showVideo(Activity activity);
        void showDirection(Activity activity );
    }
}
