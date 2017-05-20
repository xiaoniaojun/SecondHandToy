package cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.widget.group_view;

import android.content.Context;

import cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.widget.row_view.Base.BaseViewFactory;

/**
 * Package: cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.widgets.group_view
 * Created by hackpoint on 2017/5/9.
 */

public class GroupViewFactory{

    public GroupView createView(GroupViewDescriptor params, Context context, BaseViewFactory factory) {
        GroupView groupView = new GroupView(context);
        groupView.addChildViews(params.mParamsList);
        groupView.notifyChanged(factory);
        return groupView;
    }
}
