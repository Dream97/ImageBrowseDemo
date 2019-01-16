package com.rastargame.rick.rickivbrowse.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.rastargame.rick.rickivbrowse.util.RickImageLoader;

import java.util.ArrayList;

/**
 * Author: Rick
 * Email: zhiyuanfeng@rastar.com
 * Date: 2019/1/10
 */
public class RickViewPagerAdapter extends PagerAdapter {
    private ArrayList<String> mImgList = new ArrayList<>();
    private ArrayList<Integer> mResList = new ArrayList<>();
    private RickIvBrowseActivity context;

    public RickViewPagerAdapter(RickIvBrowseActivity context, ArrayList<String> imgList,  ArrayList<Integer> resList) {
        if (imgList == null) {
            this.mResList = resList;
        } else {
            this.mImgList = imgList;
        }
        this.context = context;
    }

    @Override
    public int getCount() {
        if (mImgList.size() == 0) {
            return mResList.size();
        } else {
            return mImgList.size();
        }
    }

    //指定复用的判断逻辑，固定写法：view == object
    @Override
    public boolean isViewFromObject(View view, Object object) {
        //当创建新的条目，又反回来，判断view是否可以被复用(即是否存在)
        return view == object;
    }

    //返回要显示的条目内容
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //container  容器  相当于用来存放imageView

        RickBrowseImageView rickBrowseImageView = new RickBrowseImageView(context);

        if (mImgList.size() == 0) {
            RickImageLoader.display(context, mResList.get(position), rickBrowseImageView);
        } else {
            RickImageLoader.display(context, mImgList.get(position), rickBrowseImageView);
        }

        //把图片添加到container中
        container.addView(rickBrowseImageView);
        //把图片返回给框架，用来缓存
        return rickBrowseImageView;
    }

    //销毁条目
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //object:刚才创建的对象，即要销毁的对象
        container.removeView((View) object);
    }

}
