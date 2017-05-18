package cn.xiaoniaojun.secondhandtoy.mvvm;

import android.databinding.ViewDataBinding;

import cn.xiaoniaojun.secondhandtoy.mvvm.binding.IBinding;


/**
 * Created by Ben on 2017/4/9.
 */

public abstract class DataBindingViewLayer<DB extends ViewDataBinding, VM extends IBinding, Container> extends ViewLayer<VM, Container> {

    protected DB mBinding;

    public DataBindingViewLayer(DB binding, Container container) {
        super(container);
        mBinding = binding;
    }

    @Override
    protected void onDetach() {
        mBinding.unbind();
    }
}
