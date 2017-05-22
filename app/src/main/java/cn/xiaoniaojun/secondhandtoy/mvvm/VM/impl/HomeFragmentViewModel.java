package cn.xiaoniaojun.secondhandtoy.mvvm.VM.impl;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.facebook.common.util.UriUtil;

import java.util.ArrayList;
import java.util.List;

import cn.xiaoniaojun.secondhandtoy.FrescoApplication;
import cn.xiaoniaojun.secondhandtoy.R;
import cn.xiaoniaojun.secondhandtoy.mvvm.M.entity.BannerEntity;
import cn.xiaoniaojun.secondhandtoy.mvvm.M.entity.HomeFragmentEntity;
import cn.xiaoniaojun.secondhandtoy.mvvm.M.entity.HomeListEntity;
import cn.xiaoniaojun.secondhandtoy.mvvm.M.entity.HomePageCategoryEntity;
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

//--------------------------------------------------------------------------------------------------
  /* Local Field / Constructors / init */
//--------------------------------------------------------------------------------------------------
    private final int PAGE_SIZE = 10;

    private ObservableList<BannerEntity> mBannerList;
    private HomeFragmentEntity mEntity;
    private ObservableList<HomeListEntity> mHomeEntityList;
    private List<HomeListEntity> mFreshList;
    private List<HomeListEntity> mNearList;
    private ObservableList<HomePageCategoryEntity> mHomePageCategoryEntityList;

    public HomeFragmentViewModel(ViewLayer viewLayer) {
        super(viewLayer);
    }

    @Override
    protected void onAttach() {

        /* init field variables */
        mEntity = new HomeFragmentEntity();
        mBannerList = new ObservableArrayList<>();
        mHomeEntityList = new ObservableArrayList<>();
        mFreshList = new ArrayList<>();
        mNearList = new ArrayList<>();
        mHomePageCategoryEntityList = new ObservableArrayList<>();


    }

    @Override
    protected void onDetach() {

    }
//--------------------------------------------------------------------------------------------------
  /* Banner stuff */
//--------------------------------------------------------------------------------------------------
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
    public List<BannerEntity> getBannerEntityList() {
        return mBannerList;
    }

    @Override
    public void onBannerItemClick(BannerEntity entity) {
        // TODO: handler banner item click
    }

//--------------------------------------------------------------------------------------------------
  /* Home list stuff */
//--------------------------------------------------------------------------------------------------
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
//--------------------------------------------------------------------------------------------------
  /* home page category stuff */
//--------------------------------------------------------------------------------------------------

    @Override
    public void loadHomePageCategoryData() {
        List<HomePageCategoryEntity> entities = new ArrayList<>(4);
        HomePageCategoryEntity entity = new HomePageCategoryEntity();
        entity.setTitle("毛绒玩具");
        entity.setIntro("女孩的安全感");
        entity.setImgUri(UriUtil.getUriForResourceId(R.drawable.category_stuffed_toys).toString());
        entities.add(entity);

        entity = new HomePageCategoryEntity();
        entity.setTitle("电动玩具");
        entity.setIntro("男孩的好奇心");
        entity.setImgUri(UriUtil.getUriForResourceId(R.drawable.category_electronic_toys).toString());
        entities.add(entity);

        entity = new HomePageCategoryEntity();
        entity.setTitle("教育类玩具");
        entity.setIntro("让孩子在玩耍中成长");
        entity.setImgUri(UriUtil.getUriForResourceId(R.drawable.category_education_toys).toString());
        entities.add(entity);

        entity = new HomePageCategoryEntity();
        entity.setTitle("其他");
        entity.setIntro("其他类型的玩具");
        entity.setImgUri(UriUtil.getUriForResourceId(R.drawable.category_rest_toys).toString());
        entities.add(entity);

        mHomePageCategoryEntityList.addAll(entities);

    }

    @Override
    public List<HomePageCategoryEntity> getHomePageCategoryList() {
        return mHomePageCategoryEntityList;
    }


//--------------------------------------------------------------------------------------------------
  /* Add on Observable data changed Callback */
//--------------------------------------------------------------------------------------------------

    @Override
    public void addHomePageCategoryListChangedCallback(ObservableList.OnListChangedCallback callback) {
        addObservableListBinding(mHomePageCategoryEntityList, callback);
    }

    @Override
    public void addHomeListChangedCallback(ObservableList.OnListChangedCallback callback) {
        addObservableListBinding(mHomeEntityList, callback);
    }

    @Override
    public void addBannerListChangedCallback(ObservableList.OnListChangedCallback callback) {
        addObservableListBinding(mBannerList, callback);
    }



}
