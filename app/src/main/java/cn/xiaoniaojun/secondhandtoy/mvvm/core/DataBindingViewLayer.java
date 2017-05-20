package cn.xiaoniaojun.secondhandtoy.mvvm.core;

import android.databinding.ViewDataBinding;
import android.os.Bundle;

import cn.xiaoniaojun.secondhandtoy.mvvm.core.binding.IBinding;


/**
 * Created by Ruby on 2017/5/13.
 */

public abstract class DataBindingViewLayer<DB extends ViewDataBinding, VM extends IBinding, Container> extends ViewLayer<VM, Container> {

    protected DB mBinding;

    public DataBindingViewLayer(DB binding, Container container, Bundle savedInstanceState) {
        super(container, savedInstanceState);
        mBinding = binding;
    }

    @Override
    protected void onDetach() {
        mBinding.unbind();
    }
}

