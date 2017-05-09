package com.github.ypicoleal.logindemo.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.github.ypicoleal.logindemo.widgets.view_factories.row_view.RowView;

import java.util.ArrayList;
import java.util.List;

/**
 * Package: com.github.ypicoleal.logindemo.widgets
 * Created by hackpoint on 2017/5/9.
 */

public class GroupView extends LinearLayout{

    private List<RowView> mRowViewList = new ArrayList<>();

    public GroupView(Context context) {
        this(context,null,0);
    }

    public GroupView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public GroupView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        configViewParams();
    }

    private void configViewParams() {
        setOrientation(VERTICAL);

    }



}
