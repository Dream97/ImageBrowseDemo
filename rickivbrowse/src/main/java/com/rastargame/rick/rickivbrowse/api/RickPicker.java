package com.rastargame.rick.rickivbrowse.api;

import android.app.Activity;
import android.content.Intent;

import com.rastargame.rick.rickivbrowse.module.RickContent;
import com.rastargame.rick.rickivbrowse.module.RickResContent;
import com.rastargame.rick.rickivbrowse.module.RickSpec;
import com.rastargame.rick.rickivbrowse.module.RickUrlContent;
import com.rastargame.rick.rickivbrowse.util.RickCode;
import com.rastargame.rick.rickivbrowse.view.RickIvBrowseActivity;

/**
 * Pick Data
 * Author: Rick
 * Email: zhiyuanfeng@rastar.com
 * Date: 2019/1/10
 */
public final class RickPicker {
    private Boolean mIsCycle;
    private RickIvBrowse mRickIvBrowse;

    public RickPicker(RickIvBrowse rickIvBrowse, boolean isCycle) {
        this.mIsCycle = isCycle;
        this.mRickIvBrowse = rickIvBrowse;
    }


    public RickPicker(RickIvBrowse rickIvBrowse) {
        this.mRickIvBrowse = rickIvBrowse;
    }

    /**
     * 添加图片资源
     * @param rickContent 内容载体
     * @return 建造者模式 返回本身
     */
    public RickPicker content(RickContent rickContent) {
        if (rickContent instanceof RickUrlContent) { //使用Url查看图片
            RickUrlContent rickUrlContent = (RickUrlContent) rickContent;
            if (rickUrlContent.getmUrlList().size() == 0) { //单独一张照片
                RickSpec.getInstance().setContentType(RickCode.RICK_CONTENT_TYPE_URL);
                RickSpec.getInstance().setUrl(rickUrlContent.getmUrl());
                RickSpec.getInstance().setTitle(rickUrlContent.getmTitle());
            } else { //传入List
                RickSpec.getInstance().setContentType(RickCode.RICK_CONTENT_TYPE_URL_LiST);
                RickSpec.getInstance().setUrlList(rickUrlContent.getmUrlList());
                RickSpec.getInstance().setTitleList(rickUrlContent.getmTitleList());
            }
        } else if (rickContent instanceof RickResContent) { //使用Res
            RickResContent rickResContent = (RickResContent) rickContent;
            if (rickResContent.getmResList().size() == 0) { //单独一张照片
                RickSpec.getInstance().setContentType(RickCode.RICK_CONTENT_TYPE_RES);
                RickSpec.getInstance().setRes(rickResContent.getmRes());
                RickSpec.getInstance().setTitle(rickResContent.getmTitle());
            } else { //传入List
                RickSpec.getInstance().setContentType(RickCode.RICK_CONTENT_TYPE_RES_LIST);
                RickSpec.getInstance().setResList(rickResContent.getmResList());
                RickSpec.getInstance().setTitleList(rickResContent.getmTitleList());
            }
        }
        return this;
    }

    /**
     * 设置当前浏览图片
     * @param position 当前位子
     * @return 建造者模式，返回本身
     */
    public RickPicker position(int position) {
        RickSpec.getInstance().setPosition(position);
        return this;
    }

    public void start() {
        Activity activity = mRickIvBrowse.getActivity();
        Intent intent = new Intent(activity, RickIvBrowseActivity.class);
        activity.startActivity(intent);
    }
}
