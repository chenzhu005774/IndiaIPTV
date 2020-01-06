package com.amt.indiaiptv.utils.toolview;


import com.amt.indiaiptv.utils.Constant;

/**
 * Created by Administrator on 2019/5/17.
 */
public class MarqueeTextViewToolBean {
    int width;
    int heigh;
    int martop;
    int marleft;
    boolean focus; //是否获取焦点

    String  text;
    int  textsize;
    String textviewbgurl;
    String textcolor;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeigh() {
        return heigh;
    }

    public void setHeigh(int heigh) {
        this.heigh = heigh;
    }

    public int getMartop() {
        return martop;
    }

    public void setMartop(int martop) {
        this.martop = martop;
    }

    public int getMarleft() {
        return marleft;
    }

    public void setMarleft(int marleft) {
        this.marleft = marleft;
    }



    public String getText() {
        return text;
    }

    public void setText(String text) {

        this.text = text;
    }

    public int getTextsize() {
        return textsize;
    }

    public void setTextsize(int textsize) {
        this.textsize = textsize;
    }

    public boolean isFocus() {
        return focus;
    }

    public void setFocus(boolean focus) {
        this.focus = focus;
    }


    public String getTextviewbgurl() {
        return textviewbgurl;
    }

    public void setTextviewbgurl(String textviewbgurl) {
        this.textviewbgurl = Constant.pichttp+ textviewbgurl;
    }

    public String getTextcolor() {
        return textcolor;
    }

    public void setTextcolor(String textcolor) {
        this.textcolor = textcolor;
    }
}
