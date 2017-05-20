package cn.xiaoniaojun.secondhandtoy.mvvm.M.entity;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import cn.xiaoniaojun.secondhandtoy.BR;

/**
 * Package: cn.xiaoniaojun.secondhandtoy.mvvm.M.entity
 * Created by hackpoint on 2017/5/19.
 */

public class HomeFragmentEntity extends BaseObservable {

    private int bannerCount;

    @Bindable
    public int getBannerCount() {
        return bannerCount;
    }

    public void setBannerCount(int bannerCount) {
        this.bannerCount = bannerCount;
        notifyPropertyChanged(BR.bannerCount);
    }
}
