package com.rastargame.rick.rickivbrowse.api;

import android.app.Activity;
import android.support.v4.app.Fragment;

import java.lang.ref.WeakReference;

/**
 * the door of RickIvBrowse
 * Author: Rick
 * Email: zhiyuanfeng@rastar.com
 * Date: 2019/1/10
 */
public class RickIvBrowse {
    private WeakReference<Fragment> mFragment;
    private WeakReference<Activity> mActivity;

    private RickIvBrowse(Activity activity, Fragment fragment) {
        this.mActivity = new WeakReference<>(activity);
        this.mFragment = new WeakReference<>(fragment);
    }

    private RickIvBrowse(Activity activity) {
        this(activity, null);
    }

    private RickIvBrowse(Fragment fragment) {
        this(fragment.getActivity(), fragment);
    }

    /**
     * get RickIvBrowse Instance
     * @param activity
     * @return
     */
    public static RickIvBrowse with(Activity activity) {
        return new RickIvBrowse(activity);
    }

    /**
     * get RickIvBrowse Instance
     * @param fragment
     * @return
     */
    public static RickIvBrowse with(Fragment fragment) {
        return new RickIvBrowse(fragment);
    }

    /**
     * Determine whether it is in a cyclic state
     * @param isCycle
     * @return
     */
    public RickPicker cycle(boolean isCycle) {
        return new RickPicker(this, isCycle);
    }

    Activity getActivity() {
        return mActivity.get();
    }

    Fragment getFragment() {
        return mFragment != null ? mFragment.get() : null ;
    }
}
