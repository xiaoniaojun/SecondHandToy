package cn.xiaoniaojun.bottomnavigationbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Package: cn.xiaoniaojun.bottomnavigationbar
 * Created by hackpoint on 2017/5/6.
 */

public class BottomNavigationBar extends LinearLayout {

    private Paint paint;

    private int cx, cy, r;

    private int currentColor;

    private ArrayList<BottomBarTab> tabList = new ArrayList<>();

    private TabListener mTabListener;

    private int tabSelectedWidth;
    private int tabDefaultWidth;

    private int imageSelectedTop;
    private int imageDefaultTop;

    private float textDefaultScale;

    private int prePosition;
    private int currentPosition;

    public int animation_duration = 150;

    // 更改此值以启用"选中缩放效果"
    private float tabWidthSelectedScale = 1.0f;

    public boolean textDefaultVisible = false;

    private boolean colorful = true;

    private int textSelectedColor;
    private int textDefaultColor;

    public BottomNavigationBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        setGravity(Gravity.CENTER_HORIZONTAL);
        float density = Resources.getSystem().getDisplayMetrics().scaledDensity;
        imageSelectedTop = (int) (density * 6);
        imageDefaultTop = (int) (density * 16);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        /*
           如果该View包含其他子View，则应该使用onLayout为子View定位以及设定尺寸。

         */
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            // 计算单个tab的默认宽度；计算被选中的tab的宽度
            tabDefaultWidth = (int) (getWidth() / (tabList.size() + tabWidthSelectedScale - 1));
            tabSelectedWidth = (int) (tabDefaultWidth * tabWidthSelectedScale);

            // 处理每个子View
            // 这里子View由BottomBarTab构成
            for (int i = 0; i < getChildCount(); i++) {
                // 取得每个子View，并获取其参数列表
                BottomBarTab tab = (BottomBarTab) getChildAt(i);
                LayoutParams params = (LayoutParams) tab.getLayoutParams();
                // 如果是当前选中的tab
                if (i == currentPosition) {
                    currentColor = tab.color;
                    params.width = tabSelectedWidth;
                    tab.textView.setVisibility(VISIBLE);
                    tab.imageView.setY(imageSelectedTop);
                    tab.setSelected(true);
                } else { // 对于其他的tab
                    params.width = tabDefaultWidth;
                    tab.imageView.setY(imageDefaultTop);
                    tab.textView.setScaleX(textDefaultScale);
                    tab.textView.setScaleY(textDefaultScale);
                    tab.setSelected(false);
                }
                setBackgroundColor(currentColor);
            }
        }
    }

    public void measureChildWidth() {
        // 得到WRAP_CONTENT行为
        int w = View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        measure(w, h);

        // 获取测量后的实际值
        int height = getMeasuredHeight();
        int width = getMeasuredWidth();

        // 重新测量每个tab的尺寸
        tabDefaultWidth = (int) (width / (tabList.size() + tabWidthSelectedScale -1));
        tabSelectedWidth = (int) (tabDefaultWidth * tabWidthSelectedScale);

        // 遍历所有子Tab
        for (int i = 0; i < getChildCount(); i++) {
            BottomBarTab tab = (BottomBarTab) getChildAt(i);
            LayoutParams params = (LayoutParams) tab.getLayoutParams();
            if (i == currentPosition) {
                currentColor =tab.color;
                params.width = tabSelectedWidth;
                tab.textView.setVisibility(VISIBLE);
                tab.imageView.setY(imageSelectedTop);
                tab.setSelected(true);
            } else {
                params.width = tabDefaultWidth;
                tab.imageView.setY(imageDefaultTop);
                tab.textView.setScaleX(textDefaultScale);
                tab.textView.setY(textDefaultScale);
                tab.setSelected(false);
            }
        }
        setBackgroundColor(currentColor);
    }

    public void setSelected(int position) {
        this.currentPosition = position;
        this.currentColor = tabList.get(position).color;
        for (int i = 0; i < tabList.size(); i++) {
            BottomBarTab tab = tabList.get(i);
            if (i == position) {
                tab.setSelected(true);
            } else {
                tab.setSelected(false);
            }
        }
    }

    public void setTabWidthSelectedScale(float tabWidthSelectedScale) {
        this.tabWidthSelectedScale = tabWidthSelectedScale;
    }

    public void setTextDefaultVisible(boolean visible) {
        this.textDefaultVisible = visible;
        if (textDefaultVisible) {
            imageDefaultTop = (int) (Resources.getSystem().getDisplayMetrics().scaledDensity * 8);
            textDefaultScale = 0.9f;
        } else {
            imageDefaultTop = (int) (Resources.getSystem().getDisplayMetrics().scaledDensity * 16);
            textDefaultScale = 0f;
        }
    }

    public void setColorful(boolean colorful) {
        this.colorful = colorful;
    }

    public void setTextColorResId(int textColorResId) {
        ColorStateList stateList = getResources().getColorStateList(textColorResId);
        textSelectedColor = stateList.getColorForState(View.SELECTED_STATE_SET, 0xffffffff);
        textDefaultColor = stateList.getDefaultColor();
    }

    public void ripple(View view, int color) {
        int x = (int) (view.getX() + view.getWidth() / 2);
        int y = (int) (view.getY() + view.getHeight() / 2);

        if (colorful) {
            drawCircle(x, y, getWidth(), color);
        } else {
            drawCircle(x, y, getHeight() / 2 , textSelectedColor);
        }
    }

    private void drawCircle(int cx, int cy, int maxR, int color) {
        this.cx = cx;
        this.cy = cy;
        this.currentColor = color;

        ValueAnimator va = ValueAnimator.ofInt(0, maxR);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                r = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        va.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                setBackgroundColor(currentColor);
            }
        });
        va.setDuration(300);
        va.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(currentColor);
        canvas.drawCircle(cx, cy, r, paint);
    }

    public void addTab(int resId, String text, int color) {
        BottomBarTab tab = new BottomBarTab(getContext());
        tab.setImageResource(resId);
        tab.setText(text);
        tab.color = color;
        tab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClickEvent((BottomBarTab) v);
            }
        });
        LayoutParams params = (LayoutParams) tab.getLayoutParams();
        if (params == null) {
            params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        }

        addView(tab, params);
        tabList.add(tab);
    }

    private void handleClickEvent(BottomBarTab selected) {
        for (int i = 0; i < tabList.size(); i++) {
            final BottomBarTab tab = tabList.get(i);
            if (selected.equals(tab)) {
                this.currentPosition = i;
                mTabListener.onSelected(tab, i, prePosition);
                this.prePosition = this.currentPosition;
                if (!tab.isSelected()) {
                    tab.setSelected(true);

                    tab.textView.setVisibility(VISIBLE);
                    tab.widthAnimator(tabDefaultWidth, tabSelectedWidth);
                    tab.textScaleAnimator(1);
                    tab.imageTranslationAnimator(imageDefaultTop, imageSelectedTop);
                    tab.animatorsStart(animation_duration);
                    ripple(tab, tab.color);
                }
            } else {
                if (tab.isSelected()) {
                    tab.setSelected(false);

                    tab.widthAnimator(tabSelectedWidth, tabDefaultWidth);
                    tab.textScaleAnimator(textDefaultScale);
                    tab.imageTranslationAnimator(imageSelectedTop, imageDefaultTop);
                    tab.animatorsStart(animation_duration);
                }
            }
        }
    }

    public void setOnTabListener(TabListener tabListener) {
        this.mTabListener = tabListener;
    }

    public interface TabListener {
        void onSelected(BottomBarTab tab, int position, int pre);
    }


}//Class End
