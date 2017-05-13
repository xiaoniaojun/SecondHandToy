package cn.xiaoniaojun.secondhandtoy.ui.widgets.row_view.normal_row_view;

import android.content.Context;


import cn.xiaoniaojun.secondhandtoy.ui.widgets.row_view.Base.BaseViewDescriptor;
import cn.xiaoniaojun.secondhandtoy.ui.widgets.row_view.Base.BaseViewFactory;

/**
 * Created by hackpoint on 2017/5/9.
 */

public class NormalRowViewFactory extends BaseViewFactory<NormalRowView> {


    @Override
    public NormalRowView createView(BaseViewDescriptor params, Context context) {
        String content = params.getProperty("content");
        int icon = params.getProperty("icon");
        int action = params.getProperty("action");

        NormalRowView normalRowView = new NormalRowView(context);
        normalRowView.config(icon,content,action);
        return normalRowView;
    }


}
