package cn.xiaoniaojun.secondhandtoy.ui.widgets.row_view.Base;

import android.content.Context;
import android.view.View;

/**
 * Package: com.github.ypicoleal.logindemo.widgets.view_factories
 * Created by hackpoint on 2017/5/9.
 */

public abstract class BaseViewFactory<V extends View> {
    public abstract V createView(BaseViewDescriptor params, Context context);
}