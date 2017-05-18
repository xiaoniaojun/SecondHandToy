package cn.xiaoniaojun.secondhandtoy.mvvm;

import android.databinding.Observable;
import android.databinding.ObservableList;



import java.util.ArrayList;
import java.util.List;

import cn.xiaoniaojun.secondhandtoy.mvvm.binding.IBinding;
import cn.xiaoniaojun.secondhandtoy.mvvm.binding.ObservableBindingUtil;

/**
 * Created by Ben on 2017/4/3.
 */

public abstract class ViewModel implements IBinding {

    private ViewLayer mViewLayer;

    private List<IBinding> mObservableBindingList;

    public ViewModel(ViewLayer viewLayer) {
        mViewLayer = viewLayer;
    }

    protected abstract void onAttach();

    protected abstract void onDetach();

    @Override
    public final void bind() {
        mObservableBindingList = new ArrayList<>();
        onAttach();
        if (null != mViewLayer) {
            mViewLayer.onAttach(this);
        }
    }

    @Override
    public final void unbind() {
        onDetach();

        for (int i = 0; i < mObservableBindingList.size(); i++) {
            IBinding binding = mObservableBindingList.get(i);
            binding.unbind();
        }
        mObservableBindingList = null;

        if (null != mViewLayer) {
            mViewLayer.onDetach();
            mViewLayer = null;
        }
    }

    protected void addObservableBinding(Observable observable, Observable.OnPropertyChangedCallback callback) {
        IBinding binding = ObservableBindingUtil.bind(observable, callback);
        mObservableBindingList.add(binding);
    }

    protected void addObservableListBinding(ObservableList observableList, ObservableList.OnListChangedCallback callback) {
        IBinding binding = ObservableBindingUtil.bind(observableList, callback);
        mObservableBindingList.add(binding);
    }
}
