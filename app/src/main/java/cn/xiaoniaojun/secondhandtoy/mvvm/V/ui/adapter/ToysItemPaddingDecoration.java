package cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import cn.xiaoniaojun.secondhandtoy.R;

/**
 * Package: cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.adapter
 * Created by hackpoint on 2017/5/27.
 */

public class ToysItemPaddingDecoration extends RecyclerView.ItemDecoration {
    private final Rect paddingRect;

    public ToysItemPaddingDecoration(Context context) {
        final int padding = (int) context.getResources().getDimension(R.dimen.product_margin);
        paddingRect = new Rect(padding, 0, padding, 0);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(paddingRect);
    }
}
