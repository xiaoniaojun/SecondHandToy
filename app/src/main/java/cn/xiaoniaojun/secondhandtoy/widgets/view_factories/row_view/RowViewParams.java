package cn.xiaoniaojun.secondhandtoy.widgets.view_factories.row_view;

import android.os.Bundle;

import cn.xiaoniaojun.secondhandtoy.widgets.view_factories.BaseViewParams;


/**
 * Package: com.github.ypicoleal.logindemo.widgets.view_factories.row_view
 * Created by hackpoint on 2017/5/9.
 */

public class RowViewParams extends BaseViewParams {

    // RowClick事件枚举值(替代Enum类)
    public static final int ROW_ACTION_NO_ACTION = 0;


    private RowViewParams(int viewType) {
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

        public RowViewParams done() {
            RowViewParams params = new RowViewParams(BaseViewParams.VIEW_TYPE_NORMAL_ROW);
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
            params.setParams(bundle);
            return params;
        }
    }


}
