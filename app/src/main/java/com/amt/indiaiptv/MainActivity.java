package com.amt.indiaiptv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amt.indiaiptv.home.HomeActivity;


public class MainActivity extends AppCompatActivity  {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, HomeActivity.class));
    }


}
//    static {
//        System.loadLibrary("native-lib");
//    }
