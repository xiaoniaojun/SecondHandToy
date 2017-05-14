package cn.xiaoniaojun.carousel.normalcarousel;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Scroller;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import org.reactivestreams.Subscriber;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import cn.xiaoniaojun.bottomnavigationbar.R;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * Package: cn.xiaoniaojun.carousel.normalcarousel
 * Created by xiaoniaojun on 2017/5/13.
 */

public class NormalCarousel extends ViewGroup {


    // TODO: 定时轮播

    // limit the max number of carousel
    public static final int MAX_ALLOWED = 5;
    private final static int SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
    private final static float DENSITY = Resources.getSystem().getDisplayMetrics().density;
    private final static int COLOR_SELECTED = Color.rgb(251, 217, 70);
    private final static int COLOR_UNSELECTED = Color.rgb(51, 51, 53);
    private final static int INDICATOR_INTERVAL = (int) (20 * DENSITY);

    private int mDrawIndicatorLeft;
    private Paint mSelectedIndicatorPaint;
    private Paint mUnSelectedIndicatorPaint;

    private ArrayList<ImageView> mContentImageViews = new ArrayList<>();
    private int mContentSize;
    private int mViewHeight;
    private int mInterval = 5000;
    private boolean _timerstarted = false;
    private String[] mImageUrls;

    private int mCurrentIndex = 0;

    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;
    private int mLastXIntercept;
    private int mLastYIntercept;
    private int mLastX;


    public NormalCarousel(Context context) {
        this(context, null, 0);
    }

    public NormalCarousel(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NormalCarousel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void config(int size, int height, int interval) {
        if (size < 0 && size > MAX_ALLOWED)
            throw new UnsupportedOperationException("轮播图项目最多为" + MAX_ALLOWED);
        if (height > 0) {
            mContentSize = size;
            mViewHeight = height;
            mInterval = Math.max(1500, interval);
        }

        initializeConfig();
    }

    public void setContentUrl(String[] urls, Fragment fragment) {
        if (urls.length != mContentSize) {
            try {
                throw new Exception("url数组大小不符合！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mImageUrls = urls;
        for (int i = 0; i < mContentSize; i++) {
            Glide.with(fragment).load(mImageUrls[i])
                    .into(new SimpleTarget<GlideDrawable>() {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                            Log.v("调试", "图片下载完成！");
                        }
                    });
        }

        // 开始轮播计时
        _timerstarted = true;
        if (_timerstarted) {
            Observable<Long> timer = Observable.interval(mInterval, TimeUnit.MILLISECONDS);
            timer.observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                @Override
                public void accept(@NonNull Long aLong) throws Exception {
                    mCurrentIndex = ++mCurrentIndex < mContentSize ? mCurrentIndex : 0;
                    int dx = (mCurrentIndex) * SCREEN_WIDTH - getScrollX();
                    smoothScrollBy(dx, 0);
                }
            });

        }
    }



    private void initializeConfig() {
        final Context context = getContext();
        for (int i = 0; i < mContentSize; i++) {
            ImageView iv = new LogImageView(context);
            int childMeasuredWidth = MeasureSpec.makeMeasureSpec(SCREEN_WIDTH, MeasureSpec.EXACTLY);
            int childMeasuredHeight = MeasureSpec.makeMeasureSpec(mViewHeight, MeasureSpec.EXACTLY);
            iv.measure(childMeasuredWidth, childMeasuredHeight);
            iv.setImageResource(R.drawable.meizi);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mContentImageViews.add(iv);
            addView(iv);
        }

        initIndicatePointDrawingParams();
    }

    private void initIndicatePointDrawingParams() {

        if ((mContentSize & 1) != 0) {
            mDrawIndicatorLeft = SCREEN_WIDTH / 2 - ((mContentSize / 2) * INDICATOR_INTERVAL);
        } else {
            mDrawIndicatorLeft = (int) (SCREEN_WIDTH / 2 - (((float) (mContentSize / 2)) - 0.5) * INDICATOR_INTERVAL);
        }

        mSelectedIndicatorPaint = new Paint();
        mSelectedIndicatorPaint.setColor(COLOR_SELECTED);
        mSelectedIndicatorPaint.setStyle(Paint.Style.FILL);

        mUnSelectedIndicatorPaint = new Paint();
        mUnSelectedIndicatorPaint.setColor(COLOR_UNSELECTED);
        mUnSelectedIndicatorPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
        final int r = (int) (2 * DENSITY);
        Paint paint;
        float cx = mDrawIndicatorLeft + getScrollX();
        float cy = (float) (mViewHeight * 0.9);
        for (int i = 0; i < mContentSize; i++) {
            if (i == mCurrentIndex) {
                paint = mSelectedIndicatorPaint;
            } else {
                paint = mUnSelectedIndicatorPaint;
            }
            canvas.drawCircle(cx, cy, r, paint);
            cx += INDICATOR_INTERVAL;
        }
    }


    public void setContentImageUrls(String[] urls) {
        if (mContentSize != urls.length) {
            try {
                throw new Exception("轮播图配置错误：url数量与轮播图内容数量不符合！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mImageUrls = urls;
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childLeft = 0;
        final int measuredWidth = SCREEN_WIDTH;
        final int measureHeight = getMeasuredHeight();
        for (int i = 0; i < mContentSize; i++) {
            final View childView = mContentImageViews.get(i);
            childView.layout(childLeft, 0, childLeft + measuredWidth, measureHeight);
            childLeft += measuredWidth;
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mContentSize = mContentImageViews.size();

        if (mContentSize > 0) {
            int measuredWidth = SCREEN_WIDTH * mContentSize;
            int measuredHeight = mViewHeight;

            setMeasuredDimension(measuredWidth, measuredHeight);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = false;

        // 如果dx > dy,就拦截
        int x = (int) ev.getX();
        int y = (int) ev.getY();

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercepted = false;
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                    intercepted = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int dX = x - mLastXIntercept;
                int dY = y - mLastYIntercept;

                if (Math.abs(dX) > Math.abs(dY)) {
                    intercepted = true;
                } else {
                    intercepted = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercepted = false;
                break;
        }
        mLastX = x;
        mLastXIntercept = x;
        mLastYIntercept = y;

        return intercepted;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mVelocityTracker.addMovement(event);
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int scrollX = getScrollX();
                if (scrollX > 0 && scrollX < SCREEN_WIDTH * (mContentSize - 1)) {
                int dX = x - mLastX;
                scrollBy(-dX, 0);
            }
            break;
            case MotionEvent.ACTION_UP:
                scrollX = getScrollX();
                final int measuredWidth = SCREEN_WIDTH;
                mVelocityTracker.computeCurrentVelocity(1000);
                float xVelocity = mVelocityTracker.getXVelocity();
                if (Math.abs(xVelocity) >= 50 * DENSITY) {
                    mCurrentIndex = xVelocity > 0 ? mCurrentIndex - 1 : mCurrentIndex + 1;
                } else {
                    mCurrentIndex = (scrollX + measuredWidth / 2) / measuredWidth;
                }
                mCurrentIndex = Math.max(0, Math.min(mCurrentIndex, mContentSize - 1));
                int dx = mCurrentIndex * measuredWidth - scrollX;
                smoothScrollBy(dx, 0);
                mVelocityTracker.clear();

                break;
        }
        Log.v("this", "当前轮播图位置：" + mCurrentIndex);
        mLastX = x;
        return true;
    }

    private void init() {
        mScroller = new Scroller(getContext());
        mVelocityTracker = VelocityTracker.obtain();
        setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.white));
    }

    private void smoothScrollBy(int dx, int dy) {
        mScroller.startScroll(getScrollX(), 0, dx, dy, 500);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mVelocityTracker.recycle();
    }

    public class LogImageView extends android.support.v7.widget.AppCompatImageView {

        private int _onMeasureTimes;

        public static final String TAG = "LogImageView";

        public LogImageView(Context context) {
            super(context);
        }

        public LogImageView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        public LogImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            Log.v(TAG, "on Measure," + (++_onMeasureTimes) + " times");
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

}


