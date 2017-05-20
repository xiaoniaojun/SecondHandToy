package cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.widget.row_view.user_info_row_view;

import cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.widget.row_view.Base.BaseViewDescriptor;

/**
 * Package: cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.widgets.row_view.user_info_row_view
 * Created by hackpoint on 2017/5/11.
 */

public class UserInfoRowDescriptor extends BaseViewDescriptor {

    private String profileUrl;
    private String userName;
    private String userId;

    private UserInfoRowDescriptor(int viewType) {
        super(viewType);
    }

    public UserInfoRowDescriptor(int viewType, String profileUrl, String userName, String userId) {
        super(viewType);
        this.profileUrl = profileUrl;
        this.userName = userName;
        this.userId = userId;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }
}
