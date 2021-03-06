package com.rastargame.rick.rickivbrowse.view;

import android.content.Context;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;

import com.squareup.picasso.Picasso;

import static android.content.ContentValues.TAG;

/**
 * Author: Rick
 * Email: zhiyuanfeng@rastar.com
 * Date: 2019/1/10
 */
public class RickBrowseImageView extends android.support.v7.widget.AppCompatImageView implements ViewTreeObserver.OnGlobalLayoutListener, ScaleGestureDetector.OnScaleGestureListener, View.OnTouchListener {


    private Matrix mMatrix = new Matrix();
    private Boolean first = true;//判断第一次渲染
    private float mInitScale; //显示正常图片时的缩放比例
    private Float SCALE_MAX = 6f;
    private ScaleGestureDetector mScaleGestureDetector; //多点触控 伸缩手势探测器
    private GestureDetector mGestureDetector; //多点触控探测器 用于监听双击
    private int mDoubleTap;
    private RickIvBrowseActivity mActivity;
    private float mLastX = 0; //记录上一次触摸的点
    private float mLastY = 0;

    /**
     * 用于存放矩阵的9个值
     */
    private final float[] matrixValues = new float[9];

    public RickBrowseImageView(RickIvBrowseActivity context) {
        this(context, null);
    }

    public RickBrowseImageView(RickIvBrowseActivity context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RickBrowseImageView(final RickIvBrowseActivity context, AttributeSet attributeSet, int res) {
        super(context, attributeSet, res);
        super.setScaleType(ScaleType.MATRIX);
        this.mActivity = context;
        mScaleGestureDetector = new ScaleGestureDetector(context, this);
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onDoubleTap(MotionEvent e) { //双击监听
                float x = e.getX();
                float y = e.getY(); //中心点
                float scale = 4; //放大到四倍
                if (mInitScale < getCurrentScale()) { //如果当前缩放值大于初始 则缩小
                    scale(mInitScale, x, y);
                } else {
                    scale(scale, x, y);
                }
                return true;
            }
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                mActivity.showTitle();
                return true;
            }
        });
        mDoubleTap = ViewConfiguration.get(context).getScaledDoubleTapSlop();
        this.setOnTouchListener(this);
        Log.d(TAG, "RickBrowseImageView: ------------------------------------------------");
    }

    /**
     * 在onResume后调用的方法
     */
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }

    /**
     * 在onAttachedToWindow注册监听后加载图片
     */
    @Override
    public void onGlobalLayout() {
        getParent().requestDisallowInterceptTouchEvent(true);
        if (first) { //防止渲染多次
            Drawable drawable = getDrawable();//获取当前图片
            if (drawable == null) {
                return;
            }

            int width = getWidth();//获取当前控件长度
            int height = getHeight();//当前控件高度

            int drawableWidth = drawable.getIntrinsicWidth();
            int drawableHeight = drawable.getIntrinsicHeight();
            float scale = 1.0f;
            /**
             * 图片刚好适应控件大小
             */
//            if (drawableWidth > width || drawableHeight > height) { //如果图片宽度大于控件宽度
                scale = Math.min(width * 1.0f / drawableWidth, height * 1.0f / drawableHeight);
//            }

            mInitScale = scale;
            SCALE_MAX = mInitScale * 6;
            // 图片移动至屏幕中心
            mMatrix.postScale(scale, scale);//, drawableWidth*scale/2, drawableHeight*scale/2);
            mMatrix.postTranslate((width - drawableWidth * scale) / 2, (height - drawableHeight * scale) / 2);
            setImageMatrix(mMatrix);
            first = false;
        }

    }

    /**
     * 多点触控 缩放 在 onTouch注册监听后使用
     * 缩放控制
     * @param detector 伸缩手势探测器
     * @return
     */
    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        mMatrix.getValues(matrixValues);
        float scale = matrixValues[Matrix.MSCALE_X]; //当前缩放值
        float scaleFactor = detector.getScaleFactor();
        scale(scaleFactor, detector.getFocusX(), detector.getFocusY());
        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }

    /**
     * 获取当前缩放值
     * @return 当前缩放值
     */
    public float getCurrentScale() {
        mMatrix.getValues(matrixValues);
        float scale = matrixValues[Matrix.MSCALE_X]; //当前缩放值
        return scale;
    }
    /**
     * 触摸事件
     * @param v 控件
     * @param event 事件
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);
        mScaleGestureDetector.onTouchEvent(event);
        mGestureDetector.onTouchEvent(event);
        int pointNum = event.getPointerCount();
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (mLastX != -1 && pointNum == 1) {
                    float dx = event.getX() - mLastX;
                    float dy = event.getY() - mLastY;
                    moveMatriX(dx, dy); //进行移动
                }
                mLastX = event.getX();
                mLastY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                mLastY = -1;
                mLastX = -1;
                break;

        }

        return true;
    }

    /**
     * 移动
     * @param dx x轴移动距离
     * @param dy y轴移动距离
     */
    private void moveMatriX(float dx, float dy) {
        RectF rectF = new RectF();
        Drawable drawable = getDrawable();
        if (drawable != null) {
            rectF.set(0, 0 , drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            mMatrix.mapRect(rectF);
        }

        if (dx < 0 && rectF.right > getWidth()) { //向左移动时 右边有超出的长度
            if (dx < - (rectF.right - getWidth())) { //超出的长度没有移动的长度宽
                dx  = - (rectF.right - getWidth());
            }
        } else if (dx > 0 && rectF.left < 0) { //向右移动
            if (dx > -rectF.left) {
                dx = -rectF.left;
            }
        } else {
            getParent().requestDisallowInterceptTouchEvent(false);
            dx = 0;
        }

        if(dy < 0 && rectF.bottom > getHeight()) { //向上移动
            if (dy < - (rectF.bottom - getHeight())) {
                dy = - (rectF.bottom - getHeight());
            }
        }else if (dy > 0 && rectF.top < 0) { //向下移动
            if (dy > -rectF.top) {
                dy = - rectF.top;
            }
        }else {
            dy = 0;
        }
        mMatrix.postTranslate(dx, dy);
        setImageMatrix(mMatrix);
    }

    /**
     * 缩放
     * @param scaleFactor 缩放比例
     * @param x 缩放点X
     * @param y 缩放点Y
     */
    public void scale(float scaleFactor, float x, float y) {
        float scale = matrixValues[Matrix.MSCALE_X]; //当前缩放值
        if ((scale * scaleFactor) > SCALE_MAX) { // || (scale * scaleFactor) < mInitScale  ) { //如果大于最大值 或者 小于最小值
            scaleFactor = SCALE_MAX / scale;
        }
        if ((scale * scaleFactor) < mInitScale) {
            scaleFactor = mInitScale / scale;
        }
        mMatrix.postScale(scaleFactor, scaleFactor, x, y); //以中心点缩放
        centerControl(); //边界控制
        setImageMatrix(mMatrix);
    }

    /**
     * 中心/边界控制
     */
    public void centerControl() {
        RectF rectF = new RectF();
        Drawable drawable = getDrawable();
        Matrix matrix = mMatrix;
        if (drawable != null) {
            rectF.set(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            matrix.mapRect(rectF);
        }

        float tranX = 0;
        float tranY = 0;

        if (rectF.width() >= getWidth()) { //当宽度大于等于控件
            if (rectF.left > 0) {
                tranX = - rectF.left;
            }

            if (rectF.right < getWidth()){ //rectF.right是图片右边到控件左边沿的距离
                tranX = getWidth() - rectF.right;
            }
        }else { //宽度小于控件 应该居中
            tranX = getWidth()/2 - rectF.left - rectF.width()/2;
        }

        if (rectF.height() >= getHeight()) {
            if (rectF.top > 0 ) {
                tranY = - rectF.top;
            }

            if (rectF.bottom < getHeight()){ //rectf.bottom是图片距离控件顶部的距离
                tranY = getHeight() - rectF.bottom;
            }
        }else {
            tranY = getHeight()/2 - rectF.bottom + rectF.height()/2;
        }

        mMatrix.postTranslate(tranX, tranY);
    }

}