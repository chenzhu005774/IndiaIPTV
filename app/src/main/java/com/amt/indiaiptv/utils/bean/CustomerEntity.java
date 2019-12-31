package com.amt.indiaiptv.utils.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/4/9.
 */
public class CustomerEntity implements Serializable {
    private  String  name ;
    private  String  pwd;
    private  String  mac;
    private  String  flag;
    private  String  code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
