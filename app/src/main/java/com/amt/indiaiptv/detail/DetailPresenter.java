package com.amt.indiaiptv.detail;

import android.content.Context;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.RelativeLayout;

import com.amt.indiaiptv.mvp.BasePresenterImpl;
import com.amt.indiaiptv.utils.LogUtils;
import com.amt.indiaiptv.utils.commonbean.CommonBean;
import com.amt.indiaiptv.utils.net.Api;
import com.amt.indiaiptv.utils.toolview.ImageViewTool;
import com.amt.indiaiptv.utils.toolview.ImageViewToolBean;
import com.amt.indiaiptv.utils.toolview.MarqueeTextViewTool;
import com.amt.indiaiptv.utils.toolview.MarqueeTextViewToolBean;
import com.amt.indiaiptv.utils.toolview.TextClockViewTool;
import com.amt.indiaiptv.utils.toolview.TextClockViewToolBean;
import com.amt.indiaiptv.utils.toolview.TextViewTool;
import com.amt.indiaiptv.utils.toolview.TextViewToolBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DetailPresenter extends BasePresenterImpl<DetailContract.View> implements DetailContract.Presenter{

    RelativeLayout parentlayout;
    CommonBean commonBean = new CommonBean();
    ImageViewTool imageViewTool = new ImageViewTool();//图片控件
    MarqueeTextViewTool marqueeTextViewTool = new MarqueeTextViewTool();//跑马灯控件
    TextViewTool textViewTool = new TextViewTool();  //文字控件
    TextClockViewTool textClockViewTool  = new TextClockViewTool ();//时间控件

    @Override
    public void getData(String code) {

        Api.getDefault().getPage(code,999,1).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result  = response.body().string();
                    JSONObject jSONObject = new JSONObject(result);
                    String data = jSONObject.getString("data");


                    LogUtils.i("get data :success");
                    if (data==null||data.equals("")||data.equals("null")){
                        mView.getDataFail(1);
                        LogUtils.i("get data  exception:");
                    }else {
                        mView.getDataSuccess(data);


                    }
                } catch ( Exception e) {

                     mView.getDataFail(2);
                     e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mView.getDataFail(3);
                LogUtils.i("get data ecxception:"+t.getMessage());
            }
        });


    }

    @RequiresApi(api = 28)
    @Override
    public void parserView(String data, RelativeLayout parentlayout,
                           View.OnFocusChangeListener onFocusChangeListener, View.OnClickListener onClickListener) {
        this.parentlayout  =  parentlayout;
        // 公共属性
        commonBean .setContext(mView.getContext());
        commonBean.setFocusChangeListener(onFocusChangeListener);
        commonBean.setOnClickListener(onClickListener);
        commonBean.setLayout(parentlayout);
        JSONObject jSONObject = null;
        try {

            jSONObject = new JSONObject(data);
            JSONArray jsonArray = jSONObject.getJSONArray("pageRecords");

            for (int a =0 ;a<jsonArray.length();a++) {
                JSONObject itemjson = jsonArray.getJSONObject(a);
                String itemcomponents = itemjson.getString("modelJson").replaceAll("\\\\", "");
                JSONObject itemcJson = new JSONObject(itemcomponents);
                String type = itemjson.getString("typeCode");
                LogUtils.i("chenzhu---->itemcJson" + type + "--" + itemcJson+"itemcomponents:"+itemcomponents);
                switch (type) {
                    case "s_img":
                        ImageViewToolBean imageViewToolBean_nf = new ImageViewToolBean();
                        //没有焦点图片
                        if (!itemcJson.getJSONObject("pic").isNull("pic")) {
                            imageViewToolBean_nf.setUrl(itemcJson.getJSONObject("pic").getString("pic"));
                        } else {
                            imageViewToolBean_nf.setUrl("");
                        }
                        imageViewToolBean_nf.setHeigh(itemcJson.getJSONObject("base").getInt("height"));
                        imageViewToolBean_nf.setWidth(itemcJson.getJSONObject("base").getInt("width"));
                        imageViewToolBean_nf.setMarleft(itemcJson.getJSONObject("base").getInt("x"));
                        imageViewToolBean_nf.setMartop(itemcJson.getJSONObject("base").getInt("y"));
                        imageViewToolBean_nf.setFocus(false);
                        commonBean.setTag(imageViewToolBean_nf);
                        imageViewTool.creatView(imageViewToolBean_nf, commonBean);

                        break;

                    case "s_text":
                        TextViewToolBean textViewToolBean = new TextViewToolBean();
                        //没有焦点文字
                        textViewToolBean.setFocus(false);
                        textViewToolBean.setHeigh(itemcJson.getJSONObject("base").getInt("height"));
                        textViewToolBean.setWidth(itemcJson.getJSONObject("base").getInt("width"));
                        textViewToolBean.setMarleft(itemcJson.getJSONObject("base").getInt("x"));
                        textViewToolBean.setMartop(itemcJson.getJSONObject("base").getInt("y"));
                        textViewToolBean.setTextsize(itemcJson.getJSONObject("text").getInt("fontSize"));

                        textViewToolBean.setText(itemcJson.getJSONObject("text").getString("text"));
                        textViewToolBean.setFocus(false);
                        //textViewToolBean.setTextCorol(itemcJson.getString("color")); textAlign
                        commonBean.setTag(textViewToolBean);
                        textViewTool.creatView(textViewToolBean, commonBean);

                        break;
                    case "s_vas_text":
                        TextViewToolBean textViewToolBean_f = new TextViewToolBean();
                        //没有焦点文字
                        textViewToolBean_f.setFocus(false);
                        textViewToolBean_f.setHeigh(itemcJson.getJSONObject("base").getInt("height"));
                        textViewToolBean_f.setWidth(itemcJson.getJSONObject("base").getInt("width"));
                        textViewToolBean_f.setMarleft(itemcJson.getJSONObject("base").getInt("x"));
                        textViewToolBean_f.setMartop(itemcJson.getJSONObject("base").getInt("y"));
                        textViewToolBean_f.setTextsize(itemcJson.getJSONObject("text").getInt("fontSize"));
                        textViewToolBean_f.setLineHight(itemcJson.getJSONObject("text").getInt("lineHeight"));
                        textViewToolBean_f.setColor(itemcJson.getJSONObject("text").getString("color"));


                        textViewToolBean_f.setText(itemcJson.getJSONObject("text").getString("text"));
                        textViewToolBean_f.setFocus(true);
                        //textViewToolBean.setTextCorol(itemcJson.getString("color")); textAlign
                        textViewToolBean_f.setFocustype( itemcJson.getJSONObject("focus").getInt("type"));
                        if (textViewToolBean_f.getFocustype()==1) {
                            textViewToolBean_f.setFocuswidth(itemcJson.getJSONObject("focus").getInt("width"));
                            textViewToolBean_f.setFocusheigh(itemcJson.getJSONObject("focus").getInt("height"));
                            textViewToolBean_f.setFoculeft(itemcJson.getJSONObject("focus").getInt("offsetX"));
                            textViewToolBean_f.setFocustop(itemcJson.getJSONObject("focus").getInt("offsetY"));
                            textViewToolBean_f.setFocuspicurl(itemcJson.getJSONObject("focus").getString("pic"));
                        }
                        commonBean.setTag(textViewToolBean_f);
                        textViewTool.creatView(textViewToolBean_f, commonBean);

                        break;
                    case "s_time":
                        TextClockViewToolBean textClockViewToolBean = new TextClockViewToolBean();
                        textClockViewToolBean.setFocus(false);
                        textClockViewToolBean.setHeigh(itemcJson.getJSONObject("base").getInt("height"));
                        textClockViewToolBean.setWidth(itemcJson.getJSONObject("base").getInt("width"));
                        textClockViewToolBean.setMarleft(itemcJson.getJSONObject("base").getInt("x"));
                        textClockViewToolBean.setMartop(itemcJson.getJSONObject("base").getInt("y"));
                        textClockViewToolBean.setTextsize(itemcJson.getJSONObject("time").getInt("fontSize"));
                        textClockViewToolBean.setFormattype(itemcJson.getJSONObject("time").getString("format"));
                        commonBean.setTag("s_time");
                        textClockViewTool.creatView(textClockViewToolBean, commonBean);

                        //没有焦点

                        break;
                    case "s_vas_img":
                        //有焦点图片
                        ImageViewToolBean imageViewToolBean_f = new ImageViewToolBean();
                        if (!itemcJson.getJSONObject("pic").isNull("pic")) {
                            imageViewToolBean_f.setUrl(itemcJson.getJSONObject("pic").getString("pic"));
                        } else {
                            imageViewToolBean_f.setUrl("");
                        }
                        imageViewToolBean_f.setHeigh(itemcJson.getJSONObject("base").getInt("height"));
                        imageViewToolBean_f.setWidth(itemcJson.getJSONObject("base").getInt("width"));
                        imageViewToolBean_f.setMarleft(itemcJson.getJSONObject("base").getInt("x"));
                        imageViewToolBean_f.setMartop(itemcJson.getJSONObject("base").getInt("y"));
                        imageViewToolBean_f.setFocustype(itemcJson.getJSONObject("focus").getInt("type"));
                        if (!itemcJson.getJSONObject("focus").isNull("pic")) {
                            imageViewToolBean_f.setFocuspicurl(itemcJson.getJSONObject("focus").getString("pic"));

                            if (imageViewToolBean_f.getFocustype()==1) {
                                imageViewToolBean_f.setFocuswidth(itemcJson.getJSONObject("focus").getInt("width"));
                                imageViewToolBean_f.setFocusheigh(itemcJson.getJSONObject("focus").getInt("height"));
                                imageViewToolBean_f.setFoculeft(itemcJson.getJSONObject("focus").getInt("offsetX"));
                                imageViewToolBean_f.setFocustop(itemcJson.getJSONObject("focus").getInt("offsetY"));
                            }
                        } else {
                            imageViewToolBean_f.setFocuspicurl("");
                        }
                        imageViewToolBean_f.setJumpurl("");

                        imageViewToolBean_f.setFocus(true);
                        commonBean.setTag(imageViewToolBean_f);
                        imageViewTool.creatView(imageViewToolBean_f, commonBean);
                        break;
                    case "s_marquee":
                        MarqueeTextViewToolBean marqueeTextViewToolBean = new MarqueeTextViewToolBean();
                        //跑马灯
                        //没有焦点
                        commonBean.setTag("s_marquee");

                        marqueeTextViewToolBean.setTextviewbgurl("");
                        marqueeTextViewToolBean.setFocus(false);
                        marqueeTextViewToolBean.setHeigh(itemcJson.getJSONObject("base").getInt("height"));
                        marqueeTextViewToolBean.setWidth(itemcJson.getJSONObject("base").getInt("width"));
                        marqueeTextViewToolBean.setMarleft(itemcJson.getJSONObject("base").getInt("x"));
                        marqueeTextViewToolBean.setMartop(itemcJson.getJSONObject("base").getInt("y"));
                        marqueeTextViewToolBean.setTextsize(itemcJson.getJSONObject("title").getInt("fontSize"));
                        marqueeTextViewToolBean.setText(itemcJson.getJSONObject("title").getString("text"));
                        marqueeTextViewTool.creatView(marqueeTextViewToolBean, commonBean);

                        break;

                }
            }




    } catch (JSONException e) {
            e.printStackTrace();

        }


    }
}
