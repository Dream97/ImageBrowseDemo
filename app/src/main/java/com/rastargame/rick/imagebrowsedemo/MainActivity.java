package com.rastargame.rick.imagebrowsedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rastargame.rick.rickivbrowse.api.RickIvBrowse;
import com.rastargame.rick.rickivbrowse.api.RickPicker;
import com.rastargame.rick.rickivbrowse.module.RickContent;
import com.rastargame.rick.rickivbrowse.module.RickResContent;
import com.rastargame.rick.rickivbrowse.module.RickUrlContent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<String> mUrlList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();
    private List<Integer> mResList = new ArrayList<>();
    private Button mBtnResOne;
    private Button mBtnResMore;
    private Button mBtnUrlOne;
    private Button mBtnUrlMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnResMore = findViewById(R.id.bt_res_more);
        mBtnResOne = findViewById(R.id.bt_res_one);
        mBtnUrlMore = findViewById(R.id.bt_url_more);
        mBtnUrlOne = findViewById(R.id.bt_url_one);
        mBtnUrlOne.setOnClickListener(this);
        mBtnUrlMore.setOnClickListener(this);
        mBtnResOne.setOnClickListener(this);
        mBtnResMore.setOnClickListener(this);

        mUrlList.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1917712929,4122611779&fm=200&gp=0.jpg");
        mUrlList.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1152129865,4050335444&fm=200&gp=0.jpg");
        mUrlList.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1167419250,1102971362&fm=26&gp=0.jpg");
        mUrlList.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3457846633,1987170978&fm=26&gp=0.jpg");
        mUrlList.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3012736669,152220344&fm=26&gp=0.jpg");

        mTitleList.add("第1张图片");
        mTitleList.add("第2张图片");
        mTitleList.add("第3张图片");
//        mTitleList.add("第4张图片");
//        mTitleList.add("第5张图片");

        mResList.add(R.drawable.iv_image_1);
        mResList.add(R.drawable.iv_image_2);
        mResList.add(R.drawable.iv_image_3);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_res_one:
                RickResContent rickResContent1 = new RickResContent.Builder()
                        .addRes(mResList.get(0))
                        .addTiTle(mTitleList.get(0))
                        .build();
                RickIvBrowse.with(this)
                        .content(rickResContent1)
                        .position(0)
                        .start();
                break;
            case R.id.bt_res_more:

                break;
            case R.id.bt_url_one:  /** 显示单张图片 **/
                RickUrlContent rickContent1 = new RickUrlContent.Builder()
                        .addUrl(mUrlList.get(0))
                        .addTiTle(mTitleList.get(0))
                        .build();
                RickIvBrowse.with(this)
                        .content(rickContent1)
                        .position(0)
                        .start();
                break;
            case R.id.bt_url_more: /** 显示多张图片 **/
                RickUrlContent rickContent2 = new RickUrlContent.Builder()
                        .addUrl(mUrlList)
                        .addTiTle(mTitleList)
                        .build();
                RickIvBrowse.with(this)
                        .content(rickContent2)
                        .position(3)
                        .start();
                break;
        }
    }
}
