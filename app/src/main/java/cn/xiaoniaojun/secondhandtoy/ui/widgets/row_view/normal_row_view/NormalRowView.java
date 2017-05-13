package cn.xiaoniaojun.secondhandtoy.ui.widgets.row_view.normal_row_view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.xiaoniaojun.secondhandtoy.R;
import cn.xiaoniaojun.secondhandtoy.ui.widgets.row_view.OnRowClickListener;


/**
 * Package: ${PACKAGE_NAME}
 * Created by hackpoint on 2017/5/6.
 */

public class NormalRowView extends LinearLayout implements View.OnClickListener {


    private final OnRowClickListener mOnRowClickListener;
    private ImageButton btnRowClickable;
    private ImageView imgvRowIcon;
    private TextView tvRowContent;
    private int mAction;



    public NormalRowView(Context context) {
        this(context,null);
    }

    public NormalRowView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public NormalRowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initFindingViews();
        if (context instanceof OnRowClickListener) {
            mOnRowClickListener = (OnRowClickListener) context;
        } else {
            mOnRowClickListener = null;
        }
    }


    private void initFindingViews() {
        LayoutInflater.from(getContext()).inflate(R.layout.widgets_row_layout, this);
        btnRowClickable = (ImageButton) findViewById(R.id.btn_row_clickable);
        imgvRowIcon = (ImageView) findViewById(R.id.imgv_row_icon);
        tvRowContent = (TextView) findViewById(R.id.tv_row_content);
    }




    public void config(int icon, String content, int action) {
        imgvRowIcon.setImageResource(icon);
        tvRowContent.setText(content);

        mAction = action;
        if (mAction != NormalRowViewDescriptor.ROW_ACTION_NO_ACTION && mOnRowClickListener  != null) {
            setOnClickListener(this);
            setBackgroundResource(R.drawable.widget_row_selector);
        } else {
            setBackgroundColor(Color.WHITE);
            btnRowClickable.setVisibility(GONE);
        }

    }

    @Override
    public void onClick(View v) {
        if (mOnRowClickListener != null) {
            mOnRowClickListener.onRowClick(mAction);
        }
    }
}
