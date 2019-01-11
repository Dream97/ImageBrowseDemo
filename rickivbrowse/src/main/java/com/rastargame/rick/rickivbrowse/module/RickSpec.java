package com.rastargame.rick.rickivbrowse.module;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification
 * Author: Rick
 * Email: zhiyuanfeng@rastar.com
 * Date: 2019/1/10
 */
public class RickSpec {
    private int contentType = 0;
    private boolean isCycle;
    private List<String> urlList = new ArrayList<>(); //图片链接
    private List<Integer> resList = new ArrayList<>(); //R资源链接
    private List<String> titleList = new ArrayList<>(); //图片标题
    private String title; //标题
    private String url; //链接
    private int res; //单独R资源

    private static RickSpec mRickSpec;
    public static RickSpec getInstance() {
        if (mRickSpec == null) {
            synchronized (RickSpec.class) {
                if (mRickSpec == null) {
                    mRickSpec = new RickSpec();
                }
            }
        }

        return mRickSpec;
    }
    private RickSpec() {}

    public List<String> getTitleList() {
        return titleList;
    }

    public void setTitleList(List<String> titleList) {
        this.titleList = titleList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public boolean isCycle() {
        return isCycle;
    }

    public void setCycle(boolean cycle) {
        isCycle = cycle;
    }

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }

    public List<Integer> getResList() {
        return resList;
    }

    public void setResList(List<Integer> resList) {
        this.resList = resList;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }
}
