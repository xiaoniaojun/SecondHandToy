package cn.xiaoniaojun.secondhandtoy.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;



import java.util.ArrayList;
import java.util.List;

import cn.xiaoniaojun.secondhandtoy.widgets.view_factories.row_view.normal_row_view.NormalRowView;

/**
 * Package: com.github.ypicoleal.logindemo.widgets
 * Created by hackpoint on 2017/5/9.
 */

public class GroupView extends LinearLayout{

    private List<NormalRowView> mNormalRowViewList = new ArrayList<>();

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
