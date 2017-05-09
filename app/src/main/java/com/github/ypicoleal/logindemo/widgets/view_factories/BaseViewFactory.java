package com.github.ypicoleal.logindemo.widgets.view_factories;

import android.content.Context;
import android.view.View;

import com.github.ypicoleal.logindemo.widgets.view_factories.row_view.OnRowClickListener;

/**
 * Package: com.github.ypicoleal.logindemo.widgets.view_factories
 * Created by hackpoint on 2017/5/9.
 */

public abstract class BaseViewFactory<V extends View> implements ViewFactory<V> {
    protected Context mContext;

    public BaseViewFactory(Context context) {
        mContext = context;
    }

    @Override
    public abstract V createView(BaseViewParams params);
}