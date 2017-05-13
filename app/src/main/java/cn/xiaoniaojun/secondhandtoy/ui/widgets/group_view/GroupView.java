package cn.xiaoniaojun.secondhandtoy.ui.widgets.group_view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import cn.xiaoniaojun.secondhandtoy.R;
import cn.xiaoniaojun.secondhandtoy.ui.widgets.row_view.Base.BaseViewDescriptor;
import cn.xiaoniaojun.secondhandtoy.ui.widgets.row_view.Base.BaseViewFactory;


/**
 * Package: com.github.ypicoleal.logindemo.widgets
 * Created by hackpoint on 2017/5/9.
 */

public class GroupView extends LinearLayout implements GroupDuty {

    ArrayList<BaseViewDescriptor> childViewDescriptors = new ArrayList<>();



    public GroupView(Context context) {
        this(context, null, 0);
    }

    public GroupView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GroupView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        configViewParams();
    }

    private void configViewParams() {
        setOrientation(VERTICAL);
    }

    private View createSpaceView() {
        View view = new View(getContext());
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 2);
        view.setLayoutParams(params);
        view.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.normal_background));
        return view;
    }

    @Override
    public void addChildView(BaseViewDescriptor descriptor) {
        if (descriptor != null) {
            childViewDescriptors.add(descriptor);
        }
    }

    @Override
    public void addChildViews(List<BaseViewDescriptor> descriptors) {
        if (descriptors != null) {
            childViewDescriptors.addAll(descriptors);
        }
    }

    @Override
    public void notifyChanged(BaseViewFactory factory) {
        notifyChangedAt(0, factory);
    }

    @Override
    public void notifyChangedAt(int index, BaseViewFactory factory) {
        Context context = getContext();
        for (int i = index; i < childViewDescriptors.size(); i++) {
            BaseViewDescriptor descriptor = childViewDescriptors.get(i);
            View view = factory.createView(descriptor, context);
            addView(view);
            View spaceView = createSpaceView();
            addView(spaceView);


        }
        requestLayout();
    }


}
