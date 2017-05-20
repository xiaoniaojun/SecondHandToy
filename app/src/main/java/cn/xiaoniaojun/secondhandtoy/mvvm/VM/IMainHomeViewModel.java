package cn.xiaoniaojun.secondhandtoy.mvvm.VM;

import android.databinding.Observable;
import android.databinding.ObservableList;

import cn.xiaoniaojun.secondhandtoy.mvvm.core.binding.IBinding;

/**
 * Package: cn.xiaoniaojun.secondhandtoy.mvvm.VM
 * Created by hackpoint on 2017/5/18.
 */

public interface IMainHomeViewModel extends IBinding {

    void addPageIndexChangedCallback(Observable.OnPropertyChangedCallback callback);

    /**
     * 设置底部导航栏数据
     */
    void setBottomNavigationBarData();

    /**
     * 设置当前页（Fragment）。
     * @param index 页索引
     */
    void setPage(int index);



}
