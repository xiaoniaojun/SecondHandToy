package cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.widget.container_view;

import android.view.View;

import cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.widget.group_view.GroupViewDescriptor;
import cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.widget.row_view.Base.BaseViewDescriptor;
import cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.widget.row_view.Base.BaseViewFactory;

/**
 * Package: cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.widgets.container_view
 * Created by hackpoint on 2017/5/9.
 */

public interface ContainerDuty {
    // 向GroupView内添加单个RowView
    public <V extends View> void addRowView(BaseViewDescriptor descriptor, BaseViewFactory<V> factory);

    // 想GroupView内添加GroupView
    public void addGroupView(GroupViewDescriptor descriptors, BaseViewFactory factory);

}
