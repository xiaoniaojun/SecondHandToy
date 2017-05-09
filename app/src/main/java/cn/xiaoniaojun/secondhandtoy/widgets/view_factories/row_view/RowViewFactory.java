package cn.xiaoniaojun.secondhandtoy.widgets.view_factories.row_view;

import android.content.Context;



import cn.xiaoniaojun.secondhandtoy.widgets.view_factories.BaseViewFactory;
import cn.xiaoniaojun.secondhandtoy.widgets.view_factories.BaseViewParams;

/**
 * Package: com.github.ypicoleal.logindemo.widgets.view_factories.row_view
 * Created by hackpoint on 2017/5/9.
 */

public class RowViewFactory extends BaseViewFactory<RowView> {


    public RowViewFactory(Context context) {
        super(context);
    }

    @Override
    public RowView createView(BaseViewParams params) {
        String content = params.getProperty("content");
        int icon = params.getProperty("icon");
        int action = params.getProperty("action");

        RowView rowView = new RowView(mContext);
        rowView.config(icon,content,action);
        return rowView;
    }
}
