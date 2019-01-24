package com.rastargame.rick.rickivbrowse.util;

import android.content.Context;
import android.os.Build;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * 图片加载器
 * Author: Rick
 * Email: zhiyuanfeng@rastar.com
 * Date: 2019/1/10
 */
public class RickImageLoader {
    public static void display(Context context, String url, ImageView imageView) {
        Picasso.get()
                .load(url)
                .into(imageView);
    }

    public static void display(Context context, int res, ImageView imageView) {
        if (Build.VERSION.SDK_INT > 23) {
            Picasso.get()
                    .load(res)
                    .into(imageView);

        } else {
            imageView.setImageResource(res);
        }
    }
}
