package cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.widget.group_view;

import java.util.List;

import cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.widget.row_view.Base.BaseViewDescriptor;
import cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.widget.row_view.Base.BaseViewFactory;

/**
 *  管理GroupView的行为。
 */
public interface GroupDuty {

    // 向GroupView内添单个加子View
    public void addChildView(BaseViewDescriptor descriptor);

    // 想GroupView内添加多个子View
    public void addChildViews(List<BaseViewDescriptor> descriptors);

    // 通知GroupView初始化子View并为它们定位
    public void notifyChanged(BaseViewFactory factory);

    // 通知GroupView从某个位置初始化子View并为它们定位
    public void notifyChangedAt(int index,BaseViewFactory factory);
}
