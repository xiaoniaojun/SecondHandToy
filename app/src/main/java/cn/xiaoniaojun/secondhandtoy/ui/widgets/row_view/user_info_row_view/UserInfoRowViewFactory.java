package cn.xiaoniaojun.secondhandtoy.ui.widgets.row_view.user_info_row_view;

import android.content.Context;

import cn.xiaoniaojun.secondhandtoy.ui.widgets.row_view.Base.BaseViewDescriptor;
import cn.xiaoniaojun.secondhandtoy.ui.widgets.row_view.Base.BaseViewFactory;

/**
 * Package: cn.xiaoniaojun.secondhandtoy.ui.widgets.row_view.user_info_row_view
 * Created by hackpoint on 2017/5/11.
 */

public class UserInfoRowViewFactory extends BaseViewFactory<UserInfoRowView> {
    @Override
    public UserInfoRowView createView(BaseViewDescriptor params, Context context) {
        UserInfoRowView view = new UserInfoRowView(context);
        UserInfoRowDescriptor descriptor = (UserInfoRowDescriptor)params;
        view.config(descriptor.getProfileUrl(), descriptor.getUserName(), descriptor.getUserId());
        return view;
    }
}
