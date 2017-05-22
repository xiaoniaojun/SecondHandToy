package cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import cn.xiaoniaojun.secondhandtoy.utils.DisplayUtil;

/**
 * Package: cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.adapter
 * Created by hackpoint on 2017/5/20.
 */

public class BannerIndicatorItemDecoration extends RecyclerView.ItemDecoration {


    private final static int COLOR_SELECTED = Color.rgb(251, 217, 70);
    private final static int COLOR_UNSELECTED = Color.rgb(51, 51, 53);

    private int _bannerSize;
    private int _drawLeft;

    private Context mContext;

    private final int r;
    private Paint mSelectedIndicatorPaint;
    private Paint mUnSelectedIndicatorPaint;

    private boolean isFirstMeasure = true;

    private float _cy;
    private final int _dotInterval;


    public BannerIndicatorItemDecoration(Context context, int bannerSize) {
        mContext = context;
        _bannerSize = bannerSize;

        r =  DisplayUtil.dip2px(mContext, 2);
        _dotInterval = DisplayUtil.dip2px(context, 20);


        mSelectedIndicatorPaint = new Paint();
        mSelectedIndicatorPaint.setColor(COLOR_SELECTED);
        mSelectedIndicatorPaint.setStyle(Paint.Style.FILL);

        mUnSelectedIndicatorPaint = new Paint();
        mUnSelectedIndicatorPaint.setColor(COLOR_UNSELECTED);
        mUnSelectedIndicatorPaint.setStyle(Paint.Style.FILL);

    }




    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {

        if (isFirstMeasure) {
            int height = parent.getMeasuredHeight();
            _cy = (float) (height * 0.9 + 0.5);
            if ((_bannerSize & 1) != 0) {
                _drawLeft = parent.getMeasuredWidth() / 2 - ((_bannerSize / 2) * _dotInterval);
            } else {
                _drawLeft = (int) (parent.getMeasuredWidth() / 2 - (((float)(_bannerSize / 2)) - 0.5) * _dotInterval);
            }
            isFirstMeasure = false;
        }

        Paint p;

        float cx = _drawLeft;

        final int position = ((LinearLayoutManager) parent.getLayoutManager()).findFirstVisibleItemPosition();


        for (int i = 0; i < _bannerSize; i++) {
            p = (position % _bannerSize) == i ? mSelectedIndicatorPaint : mUnSelectedIndicatorPaint;
            Log.v("绘制信息", String.format(
                    "cx: %s, cy: %s",
                    cx, _cy
            ));
            c.drawCircle(cx,_cy,r,p);
            cx += _dotInterval;
        }



    }
}
