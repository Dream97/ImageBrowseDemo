package com.rastargame.rick.imagebrowsedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rastargame.rick.rickivbrowse.api.RickIvBrowse;
import com.rastargame.rick.rickivbrowse.api.RickPicker;
import com.rastargame.rick.rickivbrowse.module.RickContent;
import com.rastargame.rick.rickivbrowse.module.RickResContent;
import com.rastargame.rick.rickivbrowse.module.RickUrlContent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> list = new ArrayList<>();
    private List<String> title = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1917712929,4122611779&fm=200&gp=0.jpg");
        list.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1152129865,4050335444&fm=200&gp=0.jpg");
        list.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1167419250,1102971362&fm=26&gp=0.jpg");
        list.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3457846633,1987170978&fm=26&gp=0.jpg");
        list.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3012736669,152220344&fm=26&gp=0.jpg");

        title.add("第1张图片");
        title.add("第2张图片");
//        title.add("第3张图片");
//        title.add("第4张图片");
//        title.add("第5张图片");

        RickUrlContent rickContent = new RickUrlContent.Builder()
                .addUrl(list)
//                .addTiTle(title)
                .build();

        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.iv_floor_22);
        list.add(R.drawable.iv_floor_23);
        RickResContent rickResContent = new RickResContent.Builder()
                .addRes(list)
                .addTiTle(title)
                .build();
        RickIvBrowse.with(this)
                .cycle(true)
                .content(rickResContent)
                .position(0)
                .start();

    }
}
