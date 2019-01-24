package com.rastargame.rick.rickivbrowse.view;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rastargame.rick.rickivbrowse.R;
import com.rastargame.rick.rickivbrowse.module.RickSpec;
import com.rastargame.rick.rickivbrowse.util.RickCode;

import java.util.ArrayList;

/**
 *
 * the main activity of RickIVBrowse
 * Author: Rick
 * Email: zhiyuanfeng@rastar.com
 * Date: 2019/1/10
 */
public class RickIvBrowseActivity extends AppCompatActivity implements View.OnClickListener {
    private RickViewPager mRickViewPager;
    private TextView mRickTitle;
    private LinearLayout mLlTitle;
    private TextView mRickPosition;
    private ImageView mIvClose;
    private RickViewPagerAdapter mRickViewPagerAdapter;
    private boolean isTitleShow = true;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rick_browse);
        mRickViewPager = findViewById(R.id.rick_viewpager);
        mRickTitle = findViewById(R.id.rick_tv_title);
        mRickPosition = findViewById(R.id.rick_tv_position);
        mLlTitle = findViewById(R.id.rick_ll_title);
        mIvClose = findViewById(R.id.rick_iv_close);
        mIvClose.setOnClickListener(this);
        //viewpager
        setAdapter(RickSpec.getInstance().getContentType());
        showTitle();
    }


    /**
     * 设置ViewPager 适配器
     * @param contentType
     */
    private void setAdapter(int contentType) {
        switch (contentType) {
            case RickCode.RICK_CONTENT_TYPE_RES:
                ArrayList<Integer> res = new ArrayList<>();
                res.add(RickSpec.getInstance().getRes());
                mRickViewPagerAdapter = new RickViewPagerAdapter(this, null, res);
                break;
            case RickCode.RICK_CONTENT_TYPE_RES_LIST:
                ArrayList<Integer> resList = new ArrayList<>();
                resList.addAll(RickSpec.getInstance().getResList());
                mRickViewPagerAdapter = new RickViewPagerAdapter(this, null, resList);
                break;
            case RickCode.RICK_CONTENT_TYPE_URL:
                ArrayList<String> url = new ArrayList<>();
                url.add(RickSpec.getInstance().getUrl());
                mRickViewPagerAdapter = new RickViewPagerAdapter(this, url, null);
                break;
            case RickCode.RICK_CONTENT_TYPE_URL_LiST:
                ArrayList<String> urlList = new ArrayList<>();
                urlList.addAll(RickSpec.getInstance().getUrlList());
                mRickViewPagerAdapter = new RickViewPagerAdapter(this, urlList, null);
                break;
        }
        mRickViewPager.setAdapter(mRickViewPagerAdapter);
        mRickViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (RickSpec.getInstance().getContentType() == RickCode.RICK_CONTENT_TYPE_RES_LIST || RickSpec.getInstance().getContentType() == RickCode.RICK_CONTENT_TYPE_URL_LiST) {
                    /**
                     * 多张图片
                     */
                    if (RickSpec.getInstance().getTitleList().size() >= (position + 1)) {
                        /**
                         * 是否有标题
                         */
                        mRickTitle.setText(RickSpec.getInstance().getTitleList().get(position));
                    } else {
                        mRickTitle.setText("");
                    }
                    mRickPosition.setText((position+1)+"/"+(RickSpec.getInstance().getUrlList().size() > 0 ? RickSpec.getInstance().getUrlList().size(): RickSpec.getInstance().getResList().size()));
                } else {
                    /**
                     * 单张图片
                     */
                    if (RickSpec.getInstance().getTitle() != null) {
                        mRickTitle.setText(RickSpec.getInstance().getTitle());
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mRickViewPager.setCurrentItem(RickSpec.getInstance().getPosition());
    }

    /**
     * 设置标题
     */
    public void showTitle() {
        if(isTitleShow) {
            mLlTitle.setVisibility(View.GONE);
            mRickTitle.setVisibility(View.GONE);
            mRickPosition.setVisibility(View.GONE);
            isTitleShow = false;
        } else {
            mLlTitle.setVisibility(View.VISIBLE);
            mRickTitle.setVisibility(View.VISIBLE);
            mRickPosition.setVisibility(View.VISIBLE);
            isTitleShow = true;
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.rick_iv_close) {
            RickSpec.getInstance().clear(); //清空数据
            finish();
        }
    }
}
