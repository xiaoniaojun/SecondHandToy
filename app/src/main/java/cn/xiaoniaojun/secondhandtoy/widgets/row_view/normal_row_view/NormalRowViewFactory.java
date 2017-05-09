package cn.xiaoniaojun.secondhandtoy.widgets.row_view.normal_row_view;

import android.content.Context;


import cn.xiaoniaojun.secondhandtoy.widgets.row_view.Base.BaseViewDescriptor;
import cn.xiaoniaojun.secondhandtoy.widgets.row_view.Base.BaseViewFactory;

/**
 * Created by hackpoint on 2017/5/9.
 */

public class NormalRowViewFactory extends BaseViewFactory<NormalRowView> {


    public NormalRowViewFactory(Context context) {
        super(context);
    }

    @Override
    public NormalRowView createView(BaseViewDescriptor params) {
        String content = params.getProperty("content");
        int icon = params.getProperty("icon");
        int action = params.getProperty("action");

        NormalRowView normalRowView = new NormalRowView(mContext);
        normalRowView.config(icon,content,action);
        return normalRowView;
    }
}
