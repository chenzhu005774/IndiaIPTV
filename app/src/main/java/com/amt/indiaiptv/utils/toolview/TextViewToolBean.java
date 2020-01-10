package com.amt.indiaiptv.utils.toolview;

import com.amt.indiaiptv.utils.Constant;

/**
 * Created by Administrator on 2019/4/10.
 */
public class TextViewToolBean {

    int width;
    int heigh;
    int martop;
    int marleft;
    String  text;
    int  textsize;
    boolean focus;


    String url;//图片地址
    boolean requesfocus =false;
    int focustype = 0;
    String focuspicurl;
    int focuswidth;
    int focusheigh;
    int focustop;
    int foculeft;


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

    public boolean isFocus() {
        return focus;
    }

    public void setFocus(boolean focus) {
        this.focus = focus;
    }


    public String getUrl() {
        return Constant.pichttp+url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isRequesfocus() {
        return requesfocus;
    }

    public void setRequesfocus(boolean requesfocus) {
        this.requesfocus = requesfocus;
    }

    public int getFocustype() {
        return focustype;
    }

    public void setFocustype(int focustype) {
        this.focustype = focustype;
    }

    public String getFocuspicurl() {
        return Constant.pichttp+focuspicurl;
    }

    public void setFocuspicurl(String focuspicurl) {
        this.focuspicurl = focuspicurl;
    }

    public int getFocuswidth() {
        return focuswidth;
    }

    public void setFocuswidth(int focuswidth) {
        this.focuswidth = focuswidth;
    }

    public int getFocusheigh() {
        return focusheigh;
    }

    public void setFocusheigh(int focusheigh) {
        this.focusheigh = focusheigh;
    }

    public int getFocustop() {
        return focustop;
    }

    public void setFocustop(int focustop) {
        this.focustop = focustop;
    }

    public int getFoculeft() {
        return foculeft;
    }

    public void setFoculeft(int foculeft) {
        this.foculeft = foculeft;
    }
}
