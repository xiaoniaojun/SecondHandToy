package cn.xiaoniaojun.secondhandtoy.widgets.row_view.Base;

import android.view.View;

/**
 * Package: com.github.ypicoleal.logindemo.widgets.view_factories.row_view
 * Created by hackpoint on 2017/5/9.
 */

public interface ViewFactory <V extends View>{
    public abstract V createView(BaseViewDescriptor params);
}