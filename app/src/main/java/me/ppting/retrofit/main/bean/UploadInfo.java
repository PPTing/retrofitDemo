package me.ppting.retrofit.main.bean;

import java.util.List;

/**
 * Created by PPTing on 2016/10/18.
 */

public class UploadInfo {

    /**
     * ret : 200
     * msg : 有心课堂,传递给你的不仅仅是技术✈️
     * data : [{"url":"uploads/icon_refresh.png","filename":"icon_refresh.png"},{"url":"uploads/icon_bath.png","filename":"icon_bath.png"}]
     */

    private int ret;
    private String msg;
    /**
     * url : uploads/icon_refresh.png
     * filename : icon_refresh.png
     */

    private List<DataBean> data;


    public int getRet() { return ret;}


    public void setRet(int ret) { this.ret = ret;}


    public String getMsg() { return msg;}


    public void setMsg(String msg) { this.msg = msg;}


    public List<DataBean> getData() { return data;}


    public void setData(List<DataBean> data) { this.data = data;}


    public static class DataBean {
        private String url;
        private String filename;


        public String getUrl() { return url;}


        public void setUrl(String url) { this.url = url;}


        public String getFilename() { return filename;}


        public void setFilename(String filename) { this.filename = filename;}
    }
}
