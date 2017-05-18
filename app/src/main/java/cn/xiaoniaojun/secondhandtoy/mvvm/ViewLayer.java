package cn.xiaoniaojun.secondhandtoy.mvvm;


import cn.xiaoniaojun.secondhandtoy.mvvm.binding.IBinding;

/**
 * Created by Ben on 2017/4/3.
 */

public abstract class ViewLayer<VM extends IBinding, Container> {

    protected Container mContainer;

    public ViewLayer(Container container) {
        mContainer = container;
    }

    protected abstract void onAttach(VM viewModel);

    protected abstract void onDetach();

}
