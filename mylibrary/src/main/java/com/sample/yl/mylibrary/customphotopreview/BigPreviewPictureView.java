package com.sample.yl.mylibrary.customphotopreview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Scroller;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ${jz} on 2019/12/26。
 * 大图预览加载控件
 * <p>
 * https://juejin.im/post/5dfcc561f265da33dd2f60d8
 */
public class BigPreviewPictureView extends View implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener,
        ScaleGestureDetector.OnScaleGestureListener {
    /**
     * 图片的宽和高
     */
    private float mImageWidth, mImageHeight;
    /**
     * 当前View的宽和高
     */
    private float mViewWidth, mViewHeight;
    /**
     * 图片的缩放比
     */
    private float mScale = 1;
    private float mCurrentScale = 1;
    /**
     * 放大几倍
     */
    private int mMultiple = 3;
    /**
     * 绘制区域
     */
    private final Rect mRect = new Rect();
    /**
     * 分区域加载器
     */
    private BitmapRegionDecoder mRegionDecoder;
    private BitmapFactory.Options mOptions;
    private Bitmap mBitmap;
    /**
     * 滑动器
     */
    private Scroller mScroller;
    /**
     * 缩放矩阵
     */
    private Matrix mMatrix;
    /**
     * 手势识别器
     */
    private GestureDetector mGestureDetector;
    private ScaleGestureDetector mScaleGestureDetector;

    public BigPreviewPictureView(Context context) {
        super(context);
        init();
    }

    public BigPreviewPictureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BigPreviewPictureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mOptions = new BitmapFactory.Options();
        //滑动器
        mScroller = new Scroller(getContext());
        //缩放器
        mMatrix = new Matrix();
        //用来识别双击事件
        mGestureDetector = new GestureDetector(getContext(), this);
        //用来监听手指的缩放事件
        mScaleGestureDetector = new ScaleGestureDetector(getContext(), this);
    }

    /**
     * 将默认的改成Bitmap.Config.RGB_565，去掉透明通道，可以减少一半的内存使用。
     * <p>
     * ARGB_8888就是由4个8位组成即32位， RGB_565就是R为5位，G为6位，B为5位共16位
     * <p>
     * 设置需要加载的图片
     */
    public void setImage(InputStream inputStream) {
        mOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, mOptions);
        mImageWidth = mOptions.outWidth;
        mImageHeight = mOptions.outHeight;
        mOptions.inPreferredConfig = Bitmap.Config.RGB_565;
        mOptions.inJustDecodeBounds = false;
        try {
            //区域解码器
            mRegionDecoder = BitmapRegionDecoder.newInstance(inputStream, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        requestLayout();
    }

    /**
     * 获取View的宽高，计算缩放值
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
        mRect.top = 0;
        mRect.left = 0;
        mRect.right = (int) mViewWidth;
        mRect.bottom = (int) mViewHeight;
        mScale = mViewWidth / mImageWidth;
        mCurrentScale = mScale;
    }

    /**
     * 通过区域解码器解码一个矩形的区域，返回一个Bitmap对象，然后通过canvas绘制Bitmap。
     * <p>
     * 这里运行就能绘制出一部分图片了，想要看全部的图片，需要手指拖动来看及需处理各种事件
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mRegionDecoder == null) {
            return;
        }
        //复用内存
        mOptions.inBitmap = mBitmap;
        mBitmap = mRegionDecoder.decodeRegion(mRect, mOptions);
        mMatrix.setScale(mCurrentScale, mCurrentScale);
        canvas.drawBitmap(mBitmap, mMatrix, null);
    }

    //事件都交给两个手势检测器自己去处理。
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);

        mScaleGestureDetector.onTouchEvent(event);
        return true;
    }

    //region 双击监听器
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        //处理双击事件
        if (mCurrentScale > mScale) {
            mCurrentScale = mScale;
        } else {
            mCurrentScale = mScale * mMultiple;
        }
        mRect.right = mRect.left + (int) (mViewWidth / mCurrentScale);
        mRect.bottom = mRect.top + (int) (mViewHeight / mCurrentScale);
        //处理上下左右的边界
        handleBorder();
        invalidate();
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }
    //endregion

    //region 滑动监听器
    @Override
    public boolean onDown(MotionEvent e) {
        //如果正在滑动，先停止
        if (!mScroller.isFinished()) {
            mScroller.forceFinished(true);
        }
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    /**
     * 根据手指移动的参数，来移动矩形绘制区域，这里需要处理各个边界点，
     * 比如左边最小就为0，右边最大为图片的宽度，不能超出边界否则就报错了。
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        //滑动的时候，改变mRect显示区域的位置
        mRect.offset((int) distanceX, (int) distanceY);
        handleBorder();
        invalidate();
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    /**
     * 在onFling方法中调用滑动器Scroller的fling方法来处理手指离开之后惯性滑动。
     * 惯性移动的距离在View的computeScroll()方法中计算，也需要注意边界问题，不要滑出边界。
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        mScroller.fling(mRect.left, mRect.top, -(int) velocityX, -(int) velocityY, 0, (int) mImageWidth
                , 0, (int) mImageHeight);
        return false;
    }
    //endregion

    //处理上下左右的边界
    private void handleBorder() {
        if (mRect.left < 0) {
            mRect.left = 0;
            mRect.right = (int) (mViewWidth / mCurrentScale);
        }
        if (mRect.right > mImageWidth) {
            mRect.right = (int) mImageWidth;
            mRect.left = (int) (mImageWidth - mViewWidth / mCurrentScale);
        }
        if (mRect.top < 0) {
            mRect.top = 0;
            mRect.bottom = (int) (mViewHeight / mCurrentScale);
        }
        if (mRect.bottom > mImageHeight) {
            mRect.bottom = (int) mImageHeight;
            mRect.top = (int) (mImageHeight - mViewHeight / mCurrentScale);
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (!mScroller.isFinished() && mScroller.computeScrollOffset()) {
            if (mRect.top + mViewHeight / mCurrentScale < mImageHeight) {
                mRect.top = mScroller.getCurrY();
                mRect.bottom = (int) (mRect.top + mViewHeight / mCurrentScale);
            }
            if (mRect.bottom > mImageHeight) {
                mRect.top = (int) (mImageHeight - mViewHeight / mCurrentScale);
                mRect.bottom = (int) mImageHeight;
            }
            invalidate();
        }
    }

    //region 缩放监听
    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        //处理手指缩放事件
        //获取与上次事件相比，得到的比例因子
        float scaleFactor = detector.getScaleFactor();
//        mCurrentScale+=scaleFactor-1;
        mCurrentScale *= scaleFactor;
        if (mCurrentScale > mScale * mMultiple) {
            mCurrentScale = mScale * mMultiple;
        } else if (mCurrentScale <= mScale) {
            mCurrentScale = mScale;
        }
        mRect.right = mRect.left + (int) (mViewWidth / mCurrentScale);
        mRect.bottom = mRect.top + (int) (mViewHeight / mCurrentScale);
        invalidate();
        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        //当 >= 2 个手指碰触屏幕时调用，若返回 false 则忽略改事件调用
        //onScaleBegin方法需要返回true，否则无法检测到手势缩放。
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }
    //endregion
}
