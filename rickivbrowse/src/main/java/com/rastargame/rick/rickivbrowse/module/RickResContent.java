package com.rastargame.rick.rickivbrowse.module;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用自有资源展示数据容器
 * Author: Rick
 * Email: zhiyuanfeng@rastar.com
 * Date: 2019/1/10
 */
public class RickResContent implements RickContent {
    private List<Integer> mResList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();
    private String mTitle;
    private Integer mRes ;

    public List<Integer> getmResList() {
        return mResList;
    }

    public void setmResList(List<Integer> mResList) {
        this.mResList = mResList;
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

    public int getmRes() {
        return mRes;
    }

    public void setmRes(Integer mRes) {
        this.mRes = mRes;
    }

    private RickResContent(Builder buider) {
        this.mResList = buider.mResList;
        this.mRes = buider.mRes;
        this.mTitleList = buider.mTitleList;
        this.mTitle = buider.mTitle;
    }

    /**
     * 建造者模式
     */
    public static class Builder {
        Integer mRes ;
        List<Integer> mResList = new ArrayList<>();
        private List<String> mTitleList = new ArrayList<>();
        private String mTitle;

        public Builder addRes(List<Integer> resList) {
            mResList.addAll(resList);
            return this;
        }

        public Builder addRes(Integer res) {
            mRes = res;
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

        public RickResContent build() {
            return new RickResContent(this);
        }
    }
}
