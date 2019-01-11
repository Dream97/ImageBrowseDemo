package com.rastargame.rick.rickivbrowse.module;

import com.rastargame.rick.rickivbrowse.BuildConfig;
import com.rastargame.rick.rickivbrowse.util.RickCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用res展示数据容器
 * Author: Rick
 * Email: zhiyuanfeng@rastar.com
 * Date: 2019/1/10
 */
public class RickUrlContent implements RickContent{
    private List<String> mUrlList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();
    private String mTitle;
    private String mUrl ;

    private RickUrlContent(Builder buider) {
        this.mUrlList = buider.mUrlList;
        this.mUrl = buider.mUrl;
        this.mTitleList = buider.mTitleList;
        this.mTitle = buider.mTitle;
    }

    public List<String> getmUrlList() {
        return mUrlList;
    }

    public void setmUrlList(List<String> mUrlList) {
        this.mUrlList = mUrlList;
    }

    public List<String> getmTitleList() {
        return mTitleList;
    }

    public void setmTitleList(List<String> mTitleList) {
        this.mTitleList = mTitleList;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    /**
     * 建造者模式
     */
    public static class Builder {
        String mUrl ;
        List<String> mUrlList = new ArrayList<>();
        private List<String> mTitleList = new ArrayList<>();
        private String mTitle;

        public Builder addUrl(List<String> urlList) {
            mUrlList.addAll(urlList);
            return this;
        }

        public Builder addUrl(String url) {
            mUrl = url;
            return this;
        }
        public Builder addTiTle(List<String> titleList) {
            mTitleList.addAll(titleList);
            return this;
        }
        
        public Builder addTiTle(String title) {
            mTitle = title;
            return this;
        }

        public RickUrlContent build() {
            return new RickUrlContent(this);
        }
    }
}
