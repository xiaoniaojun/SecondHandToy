package cn.xiaoniaojun.secondhandtoy.mvvm.core;


import cn.xiaoniaojun.secondhandtoy.mvvm.core.binding.IBinding;

import android.os.Bundle;


/**
 * Created by Ruby on 2017/5/13.
 */

public abstract class ViewLayer<VM extends IBinding, Container> {

    protected Container mContainer;
    protected Bundle mSavedInstanceState;

    public ViewLayer(Container container, Bundle savedInstanceState) {
        mContainer = container;
        mSavedInstanceState = savedInstanceState;
    }

    protected abstract void onAttach(VM viewModel);

    protected abstract void onDetach();

}
