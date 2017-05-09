package cn.xiaoniaojun.secondhandtoy.widgets.row_view.Base;

import android.os.Bundle;

/**
 * Package: com.github.ypicoleal.logindemo.widgets.view_factories
 * Created by hackpoint on 2017/5/9.
 */

public class BaseViewDescriptor {

    // 每增加一种RowView，都需要在这里为该类型添加一个索引号
    public static final int VIEW_TYPE_NORMAL_ROW = 0;
    // public static final int VIEW_TYPE_ANOTHER_ROW = 3;

    private int viewType;
    private Bundle mParams;

    public BaseViewDescriptor(int viewType) {
        this.viewType = viewType;
    }

    public int getViewType() {
        return viewType;
    }

    public <T> T  getProperty(String key) {
        //noinspection unchecked
        return (T) mParams.get(key);
    }


    public void setParams(Bundle params) {
        mParams = params;
    }
}
