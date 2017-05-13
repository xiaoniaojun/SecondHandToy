package cn.xiaoniaojun.secondhandtoy.ui.widgets.container_view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import cn.xiaoniaojun.secondhandtoy.R;
import cn.xiaoniaojun.secondhandtoy.ui.widgets.group_view.GroupView;
import cn.xiaoniaojun.secondhandtoy.ui.widgets.group_view.GroupViewDescriptor;
import cn.xiaoniaojun.secondhandtoy.ui.widgets.group_view.GroupViewFactory;
import cn.xiaoniaojun.secondhandtoy.ui.widgets.row_view.Base.BaseViewDescriptor;
import cn.xiaoniaojun.secondhandtoy.ui.widgets.row_view.Base.BaseViewFactory;

/**
 * Package: cn.xiaoniaojun.secondhandtoy.ui.widgets.container_view
 * Created by hackpoint on 2017/5/9.
 */

public class ContainerView extends LinearLayout implements ContainerDuty {


    public ContainerView(Context context) {
        this(context, null, 0);
    }

    public ContainerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ContainerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        LayoutParams params = (LayoutParams) getLayoutParams();
//        params.height = LayoutParams.MATCH_PARENT;
//        params.width = LayoutParams.MATCH_PARENT;
        configViewParams();
    }

    private void configViewParams() {
        setOrientation(VERTICAL);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        setLayoutParams(params);
    }


    @Override
    public <V extends View> void addRowView(BaseViewDescriptor descriptor, BaseViewFactory<V> factory) {
        V view = factory.createView(descriptor,getContext());
        View spaceView = createSpaceView();
        addView(spaceView);
        addView(view);


    }

    @Override
    public void addGroupView(GroupViewDescriptor descriptors, BaseViewFactory factory) {
        GroupViewFactory groupFactory = new GroupViewFactory();
        GroupView groupView = groupFactory.createView(descriptors, getContext(), factory);
        View spaceView = createSpaceView();
        addView(spaceView);
        addView(groupView);

    }

    private View createSpaceView() {
        View view = new View(getContext());
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 100);
        view.setLayoutParams(params);
        view.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.normal_background));
        return view;
    }


}
