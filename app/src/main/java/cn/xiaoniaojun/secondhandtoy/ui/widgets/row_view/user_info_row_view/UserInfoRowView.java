package cn.xiaoniaojun.secondhandtoy.ui.widgets.row_view.user_info_row_view;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.LayoutInflaterCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import cn.xiaoniaojun.secondhandtoy.R;


/**
 * Package: cn.xiaoniaojun.secondhandtoy.ui.widgets.row_view.user_info_row_view
 * Created by hackpoint on 2017/5/11.
 */

public class UserInfoRowView extends ConstraintLayout{

    private final TextView tvUserName;
    private final TextView tvUserId;
    private final ImageView imgvProfile;

    public UserInfoRowView(Context context) {
        this(context,null,0);
    }

    public UserInfoRowView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public UserInfoRowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.widgets_user_info_row_layout,this);

        tvUserName = (TextView) findViewById(R.id.tv_user_name);
        tvUserId = (TextView) findViewById(R.id.tv_user_id);
        imgvProfile = (ImageView) findViewById(R.id.imgv_profile);

    }

    public void config(String profileUrl, String userName, String userId) {
        //TODO: 设置头像
        tvUserName.setText(userName);
        tvUserId.setText(userId);

    }




}
