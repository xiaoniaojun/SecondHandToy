package cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.widget.group_view;

import java.util.ArrayList;
import java.util.List;

import cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.widget.row_view.Base.BaseViewDescriptor;

/**
 * Package: cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.widgets.group_view
 * Created by hackpoint on 2017/5/9.
 */

public class GroupViewDescriptor {

    public List<BaseViewDescriptor> mParamsList = new ArrayList<>();

    public void addRowViewDescriptor(BaseViewDescriptor descriptor) {
        if (descriptor != null) {
            mParamsList.add(descriptor);
        }
    }

    public void setRowViewDescriptors(List<BaseViewDescriptor> descriptors) {
        mParamsList.clear();
        if (descriptors != null) {
            mParamsList.addAll(descriptors);
        }
    }

    public int getCount(){
        return mParamsList.size();
    }
}
