package cn.xiaoniaojun.secondhandtoy.mvvm.VM.impl;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.net.Uri;
import android.util.Log;

import com.facebook.common.util.UriUtil;

import java.util.ArrayList;
import java.util.List;

import cn.xiaoniaojun.secondhandtoy.R;
import cn.xiaoniaojun.secondhandtoy.mvvm.M.entity.BannerEntity;
import cn.xiaoniaojun.secondhandtoy.mvvm.M.entity.HomeFragmentEntity;
import cn.xiaoniaojun.secondhandtoy.mvvm.M.entity.HomeListEntity;
import cn.xiaoniaojun.secondhandtoy.mvvm.VM.IHomeFragmentViewModel;
import cn.xiaoniaojun.secondhandtoy.mvvm.core.ViewLayer;
import cn.xiaoniaojun.secondhandtoy.mvvm.core.ViewModel;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Package: cn.xiaoniaojun.secondhandtoy.mvvm.VM.impl
 * Created by hackpoint on 2017/5/19.
 */

public class HomeFragmentViewModel extends ViewModel implements IHomeFragmentViewModel {

    private final int PAGE_SIZE = 10;

    private ObservableList<BannerEntity> mBannerList;
    private HomeFragmentEntity mEntity;
    private ObservableList<HomeListEntity> mHomeEntityList;
    private List<HomeListEntity> mFreshList;
    private List<HomeListEntity> mNearList;

    public HomeFragmentViewModel(ViewLayer viewLayer) {
        super(viewLayer);
    }

    @Override
    protected void onAttach() {
        mEntity = new HomeFragmentEntity();
        mBannerList = new ObservableArrayList<>();
        mHomeEntityList = new ObservableArrayList<>();
        mFreshList = new ArrayList<>();
        mNearList = new ArrayList<>();

    }

    @Override
    protected void onDetach() {

    }

    @Override
    public void loadBanner() {
        Uri[] bannerList = {
                UriUtil.getUriForResourceId(R.drawable.carousel_1),
                UriUtil.getUriForResourceId(R.drawable.carousel_2),
                UriUtil.getUriForResourceId(R.drawable.carousel_3),
                UriUtil.getUriForResourceId(R.drawable.carousel_4)
        };

        List<BannerEntity> banners = new ArrayList<>();
        for (Uri aBannerList : bannerList) {
            BannerEntity entity = new BannerEntity();
            entity.setImgUrl(aBannerList.toString());
            banners.add(entity);
        }

        mBannerList.clear();
        mBannerList.addAll(banners);

        mEntity.setBannerCount(mBannerList.size());
    }

    @Override
    public void addBannerListChangedCallback(ObservableList.OnListChangedCallback callback) {
        addObservableListBinding(mBannerList, callback);
    }

    @Override
    public List<BannerEntity> getBannerEntityList() {
        return mBannerList;
    }

    @Override
    public void onBannerItemClick(BannerEntity entity) {
        // TODO: handler banner item click
    }

    @Override
    public List<HomeListEntity> getHomeList() {
        return mHomeEntityList;
    }

    @Override
    public void loadHomeData() {
        mHomeEntityList.clear();

        Observable.create(new ObservableOnSubscribe<List<HomeListEntity>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<HomeListEntity>> e) throws Exception {
                List<HomeListEntity> list = new ArrayList<>();
                String message = "最新文章 ";
                for (int i = 0; i < PAGE_SIZE; i++) {
                    HomeListEntity entity = new HomeListEntity();
                    entity.setTitle(message + i);
                    entity.setDate("2017-05-19");
                    entity.setImgUrl(UriUtil.getUriForResourceId(R.drawable.article_mock).toString());
                    list.add(entity);
                }

                Thread.sleep(2000);
                e.onNext(list);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<List<HomeListEntity>>() {
                    @Override
                    public void onNext(@NonNull List<HomeListEntity> homeListEntities) {
                        mFreshList.clear();
                        mNearList.clear();
                        mHomeEntityList.clear();

                        mFreshList.addAll(homeListEntities);
                        mHomeEntityList.addAll(mFreshList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void addHomeListChangedCallback(ObservableList.OnListChangedCallback callback) {
        addObservableListBinding(mHomeEntityList, callback);
    }


}
