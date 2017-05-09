package cn.xiaoniaojun.secondhandtoy.widgets.group_view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import cn.xiaoniaojun.secondhandtoy.widgets.row_view.Base.BaseViewDescriptor;
import cn.xiaoniaojun.secondhandtoy.widgets.row_view.normal_row_view.NormalRowView;
import cn.xiaoniaojun.secondhandtoy.widgets.row_view.normal_row_view.NormalRowViewFactory;

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
        view.setBackgroundColor(Color.argb(2, 255, 255, 255));
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
    public void notifyChanged() {
        notifyChangedAt(0);
    }

    @Override
    public void notifyChangedAt(int index) {
        for (int i = index; i < childViewDescriptors.size(); i++) {
            BaseViewDescriptor descriptor = childViewDescriptors.get(i);
            int viewType = descriptor.getViewType();
            switch (viewType) {
                case BaseViewDescriptor.VIEW_TYPE_NORMAL_ROW:
                    NormalRowViewFactory factory = new NormalRowViewFactory(getContext());
                    NormalRowView view = factory.createView(descriptor);
                    addView(view);
                    if (i != childViewDescriptors.size()) {
                        addView(createSpaceView());
                    }
                    break;
                default:
                    try {
                        throw new Exception("无法创建该View，请为它创建相应的工厂类。");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        }
        requestLayout();
    }


}
