package cn.xiaoniaojun.secondhandtoy.widgets.group_view;

import java.util.List;

import cn.xiaoniaojun.secondhandtoy.widgets.row_view.Base.BaseViewDescriptor;

/**
 *  管理GroupView的行为。
 */
public interface GroupDuty {

    // 向GroupView内添单个加子View
    public void addChildView(BaseViewDescriptor descriptor);

    // 想GroupView内添加多个子View
    public void addChildViews(List<BaseViewDescriptor> descriptors);

    // 通知GroupView初始化子View并为它们定位
    public void notifyChanged();

    // 通知GroupView从某个位置初始化子View并为它们定位
    public void notifyChangedAt(int index);
}
