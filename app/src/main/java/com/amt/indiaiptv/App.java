package com.amt.indiaiptv;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.amt.indiaiptv.utils.LogUtils;
import com.amt.indiaiptv.utils.ProperTies;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.Properties;

/**
 * Created by Administrator on 2019/4/3.
 */
public class App extends Application {
    public static Properties proper ;
    public static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
//        SharedPreferences pres = getSharedPreferences("config", Context.MODE_PRIVATE);
//        // 得到共享配置对象保存配置信息
//        SharedPreferences.Editor editor = pres.edit();
//        editor.putBoolean("isinstall", false);
//        // 调用commit方法保存配置信息
//        editor.commit();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory().diskCacheFileNameGenerator(new Md5FileNameGenerator()).diskCacheSize(50 * 1024 * 1024) //缓存 50 Mb
                .tasksProcessingOrder(QueueProcessingType.LIFO).writeDebugLogs() // Remove for release app
                .build();
        ImageLoader.getInstance().init(config);

        proper = ProperTies.getProperties(getApplicationContext());
        String magin = App.proper.getProperty("magin");
        LogUtils.i("magin:"+magin);
    }



}
