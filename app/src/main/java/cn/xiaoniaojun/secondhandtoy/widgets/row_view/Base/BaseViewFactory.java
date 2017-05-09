package cn.xiaoniaojun.secondhandtoy.widgets.row_view.Base;

import android.content.Context;
import android.view.View;

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
    public abstract V createView(BaseViewDescriptor params);
}