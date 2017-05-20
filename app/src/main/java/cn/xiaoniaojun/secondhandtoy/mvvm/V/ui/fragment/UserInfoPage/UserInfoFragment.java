package cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.fragment.UserInfoPage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.xiaoniaojun.secondhandtoy.R;
import cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.fragment.BaseMainFragment;
import cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.widget.container_view.ContainerView;
import cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.widget.group_view.GroupViewDescriptor;
import cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.widget.row_view.Base.BaseViewDescriptor;
import cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.widget.row_view.normal_row_view.NormalRowViewDescriptor;
import cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.widget.row_view.normal_row_view.NormalRowViewFactory;
import cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.widget.row_view.user_info_row_view.UserInfoRowDescriptor;
import cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.widget.row_view.user_info_row_view.UserInfoRowViewFactory;

/**
 * Package: cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.fragments
 * Created by hackpoint on 2017/5/11.
 */

public class UserInfoFragment extends BaseMainFragment {

    public static final int ROW_ACTION_FIRST = 1;
    public static final int ROW_ACTION_SECOND = 2;


    public static UserInfoFragment newInstance() {
        return new UserInfoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_info_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final ViewGroup container = (ViewGroup) view;

        ContainerView containerView = new ContainerView(_mActivity);

        GroupViewDescriptor groupViewDescriptor = new GroupViewDescriptor();

        NormalRowViewFactory rowViewFactory = new NormalRowViewFactory();

        groupViewDescriptor.addRowViewDescriptor(
                NormalRowViewDescriptor.build()
                        .setIconAndContent(R.drawable.ic_qq_blue, "我的订单")
                        .setOnRowAction(ROW_ACTION_FIRST)
                        .done()
        );
        groupViewDescriptor.addRowViewDescriptor(
                NormalRowViewDescriptor.build().
                        setIconAndContent(R.drawable.ic_wechat_blue, "我的地址")
                        .setOnRowAction(ROW_ACTION_FIRST)
                        .done()
        );

        UserInfoRowDescriptor userInfoRowDescriptor = new UserInfoRowDescriptor(BaseViewDescriptor.VIEW_TYPE_USER_INFO,
                "", "xiaoniaojun", "用户id:xiaoniaojun");
        UserInfoRowViewFactory userInfoRowViewFactory = new UserInfoRowViewFactory();

        containerView.addRowView(userInfoRowDescriptor, userInfoRowViewFactory);
        containerView.addGroupView(groupViewDescriptor, rowViewFactory);
        containerView.addRowView(NormalRowViewDescriptor.build()
                .setIconAndContent(R.drawable.ic_weibo_blue, "设置")
                .setOnRowAction(ROW_ACTION_SECOND)
                .done(), rowViewFactory);

// 查看container最终测量大小
//        container.post(new Runnable() {
//            @Override
//            public void run() {
//                int width = container.getMeasuredWidth();
//                int height = container.getMeasuredHeight();
//                Log.v("container", String.format("width: %s, height: %s", width, height));
//            }
//        });

        container.addView(containerView);
    }
}
