package me.ppting.okhttpdemo.http;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by PPTing on 16/9/8.
 */
public class RequestParams {
    String key;
    Object value;
    Map<String,Object> paramsMap;
    public RequestParams(){
        paramsMap = new HashMap<>();
    }
    public void addRequestParams(String key,Object value){
        this.key = key;
        this.value = value;
        paramsMap.put(key,value);
    }

    public Map<String,Object> getMap(){
        return paramsMap;
    }
}
