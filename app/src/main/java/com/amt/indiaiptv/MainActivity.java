package com.amt.indiaiptv;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.amt.indiaiptv.detail.DetailActivity;
import com.amt.indiaiptv.home.HomeActivity;
import com.amt.indiaiptv.utils.Constant;
import com.amt.indiaiptv.utils.LogUtils;
import com.amt.indiaiptv.utils.bean.DataEntry;
import com.amt.indiaiptv.utils.bean.ViewPagerHolder;
import com.amt.indiaiptv.utils.customizeview.RoundProgressBar;
import com.amt.indiaiptv.utils.net.Api;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity  {

    private int[] ids ={R.mipmap.hotel1,R.mipmap.hotel2,R.mipmap.hotel3,R.mipmap.hotel4,R.mipmap.hotel5};
    Handler handler = new Handler();
    Runnable updateProgress;
    RoundProgressBar roundProgressBar;
    MZBannerView mMZBannerView;
    RelativeLayout relativelayout;

    String data ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getHomedata(Constant.ProjectCode);

        roundProgressBar = findViewById(R.id.roundProgressBar);
        mMZBannerView = findViewById(R.id.banner_normal);
        relativelayout = findViewById(R.id.relativelayout);
        roundProgressBar.setProgress(0);
        roundProgressBar.setTextSize(27);
        roundProgressBar.setRoundWidth(5);
        roundProgressBar.setTextColor(Color.WHITE);
        roundProgressBar.setCricleColor(Color.parseColor("#708090"));
        roundProgressBar.setCricleProgressColor(Color.parseColor("#F8F8FF"));
        mMZBannerView.setOpenMZEffect(false);
        mMZBannerView.setFocusable(false);
        relativelayout.setFocusable(true);
        relativelayout.requestFocus();
        mMZBannerView.setPages(mockData(ids) , new MZHolderCreator<ViewPagerHolder>() {
            @Override
            public ViewPagerHolder createViewHolder() {
                return new ViewPagerHolder();
            }
        });


        updateProgress = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this,25);
                if (roundProgressBar.getProgress()>=100){
                    roundProgressBar.setProgress(0);
                    mMZBannerView.viewpageMove(false);
                    relativelayout.requestFocus();
                }
                roundProgressBar.setProgress(roundProgressBar.getProgress()+1);
            }
        };

        handler.post(updateProgress);

    }

    public String  getHomedata(String code){
        Api.getDefault().getHomePage(code,999,1).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result  = response.body().string();
                    JSONObject jSONObject = new JSONObject(result);
                     data = jSONObject.getString("data");
                } catch ( Exception e) {
                    LogUtils.toast(MainActivity.this,"数据预加载失败!!!");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                LogUtils.i("get data ecxception:"+t.getMessage());
            }
        });



        return data;
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
            Intent intent = new Intent(this, HomeActivity.class)  ;
//            intent.putExtra("data",data);
            startActivity(intent);
            this.finish();
            return true;
        }else if (keyCode==KeyEvent.KEYCODE_DPAD_LEFT){
            Toast.makeText(this, "LEFT", Toast.LENGTH_SHORT).show();
            return true;
        }else if (keyCode==KeyEvent.KEYCODE_DPAD_RIGHT){
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public List<DataEntry> mockData(int[] ids){
        List<DataEntry> list = new ArrayList<>();
        DataEntry dataEntry = null;
        for(int i=0;i<ids.length;i++){
            dataEntry = new DataEntry();
            dataEntry.resId = ids[i];
            dataEntry.desc = "";
            list.add(dataEntry);
        }

        return list;
    }


    @Override
    protected void onDestroy() {
        if (updateProgress!=null)
            handler.removeCallbacks(updateProgress);
        super.onDestroy();
    }
}




//    static {
//        System.loadLibrary("native-lib");
//    }
