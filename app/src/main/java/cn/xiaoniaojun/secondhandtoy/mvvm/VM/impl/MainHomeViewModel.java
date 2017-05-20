package cn.xiaoniaojun.secondhandtoy.mvvm.VM.impl;

import android.databinding.Observable;

import cn.xiaoniaojun.secondhandtoy.mvvm.VM.IMainHomeViewModel;
import cn.xiaoniaojun.secondhandtoy.mvvm.core.ViewLayer;
import cn.xiaoniaojun.secondhandtoy.mvvm.core.ViewModel;

/**
 * Package: cn.xiaoniaojun.secondhandtoy.mvvm.VM.impl
 * Created by hackpoint on 2017/5/18.
 */

public class MainHomeViewModel extends ViewModel implements IMainHomeViewModel{


    public MainHomeViewModel(ViewLayer viewLayer) {
        super(viewLayer);
    }

    @Override
    public void addPageIndexChangedCallback(Observable.OnPropertyChangedCallback callback) {

    }

    @Override
    public void setBottomNavigationBarData() {

    }

    @Override
    public void setPage(int index) {

    }


    @Override
    protected void onAttach() {

    }

    @Override
    protected void onDetach() {

    }
}
