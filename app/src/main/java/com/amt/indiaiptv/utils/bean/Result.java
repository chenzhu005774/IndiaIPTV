package com.amt.indiaiptv.utils.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/4/4.
 */
public class Result {
    String error;
    List<MeiZi> results;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<MeiZi> getResults() {
        return results;
    }

    public void setResults(List<MeiZi> results) {
        this.results = results;
    }
}
