package cn.xiaoniaojun.secondhandtoy.mvvm.M.entity;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import cn.xiaoniaojun.secondhandtoy.BR;

/**
 * Package: cn.xiaoniaojun.secondhandtoy.mvvm.M.entity
 * Created by hackpoint on 2017/5/21.
 */

public class HomePageCategoryEntity extends BaseObservable {

    private String title;
    private String imgUri;
    private String intro;

    @Bindable
    public String getTitle() {
        return title;
    }

    @Bindable
    public String getImgUri() {
        return imgUri;
    }

    @Bindable
    public String getIntro() {
        return intro;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
        notifyPropertyChanged(BR.imgUri);
    }

    public void setIntro(String intro) {
        this.intro = intro;
        notifyPropertyChanged(BR.intro);
    }
}
