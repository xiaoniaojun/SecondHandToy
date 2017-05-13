package cn.xiaoniaojun.secondhandtoy.ui.fragments;

import android.content.Context;

import cn.xiaoniaojun.secondhandtoy.ui.fragments.HomePage.HomeFragment;
import cn.xiaoniaojun.secondhandtoy.ui.interfaces.OnBackToFirstFragmentListener;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Package: cn.xiaoniaojun.secondhandtoy.ui.fragments
 * Created by hackpoint on 2017/5/11.
 */

public class BaseMainFragment extends SupportFragment {

    protected OnBackToFirstFragmentListener _mBackToFirstFragmentListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnBackToFirstFragmentListener) {
            _mBackToFirstFragmentListener = (OnBackToFirstFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement OnBackToFirstFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // 避免内存泄漏
        _mBackToFirstFragmentListener = null;
    }

    /**
     * 处理用户回退事件
     * @return 是否消费处理
     */
    @Override
    public boolean onBackPressedSupport() {
        if (getChildFragmentManager().getBackStackEntryCount() > 1) {
            popChild();
        } else {
            if (this instanceof HomeFragment) { // 如果是主页Fragment，则退出Activity
                _mActivity.finish();
            } else {    // 如果不是，则触发回调，返回到主页
                _mBackToFirstFragmentListener.onBackToFirstFragment();
            }
        }
        return true;
    }

}
