package cn.xiaoniaojun.secondhandtoy.mvvm.VM;

import android.databinding.ObservableList;

import java.util.List;

import cn.xiaoniaojun.secondhandtoy.mvvm.M.entity.BannerEntity;
import cn.xiaoniaojun.secondhandtoy.mvvm.M.entity.HomeListEntity;
import cn.xiaoniaojun.secondhandtoy.mvvm.core.binding.IBinding;

/**
 * Package: cn.xiaoniaojun.secondhandtoy.mvvm.VM.Contract
 * Created by hackpoint on 2017/5/19.
 */

public interface IHomeFragmentViewModel extends IBinding {

    /**
     *  加载轮播图
     */
    void loadBanner();

    /**
     * 为BannerList添加监听器
     * @param callback
     */
    void addBannerListChangedCallback(ObservableList.OnListChangedCallback callback);

    List<BannerEntity> getBannerEntityList();

    void onBannerItemClick(BannerEntity entity);

    List<HomeListEntity> getHomeList();

    void loadHomeData();

    void addHomeListChangedCallback(ObservableList.OnListChangedCallback callback);
}
