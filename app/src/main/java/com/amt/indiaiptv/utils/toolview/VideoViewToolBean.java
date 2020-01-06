package com.amt.indiaiptv.utils.toolview;

/**
 * Created by Administrator on 2019/4/10.
 */
public class VideoViewToolBean {
    int width;
    int heigh;
    int martop;
    int marleft;
    boolean focus;
    String url;//视屏地址

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



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isFocus() {
        return focus;
    }

    public void setFocus(boolean focus) {
        this.focus = focus;
    }
}
