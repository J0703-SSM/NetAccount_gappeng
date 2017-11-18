package com.lanou.util;

import java.util.Map;

/**
 * Created by dllo on 17/11/14.
 */
public class AjaxResult<T> {
    private int count;
    private Map<String,Object> maps;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }
}
