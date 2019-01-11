package com.rastargame.rick.rickivbrowse.view;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
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
public class RickIvBrowseActivity extends AppCompatActivity {
    private ViewPager mRickViewPager;
    private TextView mRickTitle;
    private TextView mRickPosition;
    private RickViewPagerAdapter mRickViewPagerAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rick_browse);
        mRickViewPager = findViewById(R.id.rick_viewpager);
        mRickTitle = findViewById(R.id.rick_tv_title);
        mRickPosition = findViewById(R.id.rick_tv_position);
        //viewpager
        setAdapter(RickSpec.getInstance().getContentType());
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
                    if (RickSpec.getInstance().getTitleList().size() > 0) {
                        mRickTitle.setText(RickSpec.getInstance().getTitleList().get(position));
                    }
                    mRickPosition.setText((position+1)+"/"+(RickSpec.getInstance().getUrlList().size() > 0 ? RickSpec.getInstance().getUrlList().size(): RickSpec.getInstance().getResList().size()));
                } else {
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
    }
}
