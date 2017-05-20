package cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.widget.row_view.normal_row_view;

import android.os.Bundle;

import cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.widget.row_view.Base.BaseViewDescriptor;


/**
 * Package: cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.widgets.row_view.Base.BaseViewDescriptor
 * Created by hackpoint on 2017/5/9.
 */

public class NormalRowViewDescriptor extends BaseViewDescriptor {

    // RowClick事件枚举值(替代Enum类)
    static final int ROW_ACTION_NO_ACTION = 0;


    private NormalRowViewDescriptor(int viewType) {
        super(viewType);
    }



    public static Builder build() {
        return new Builder();
    }

    public static class Builder {
        String content;
        int icon;
        int action;



        public Builder setIconAndContent(int icon, String content) {
            this.content = content;
            this.icon = icon;
            return this;
        }

        public Builder setOnRowAction(int action) {
            this.action = action;
            return this;
        }

        public NormalRowViewDescriptor done() {
            NormalRowViewDescriptor descriptor = new NormalRowViewDescriptor(BaseViewDescriptor.VIEW_TYPE_NORMAL_ROW);
            Bundle bundle = new Bundle();
            if (content == null && icon == 0) {
                try {
                    throw new Exception("RowView必须包含图标和内容！");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                bundle.putString("content", content);
                bundle.putInt("icon", icon);
            }
            bundle.putInt("action", action);
            descriptor.setParams(bundle);
            return descriptor;
        }
    }


}
