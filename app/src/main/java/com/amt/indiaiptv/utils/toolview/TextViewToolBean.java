package com.amt.indiaiptv.utils.toolview;

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
}
