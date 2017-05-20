package cn.xiaoniaojun.secondhandtoy.mvvm.M.entity;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import cn.xiaoniaojun.secondhandtoy.BR;


/**
 * Package: cn.xiaoniaojun.secondhandtoy.mvvm.M.entity
 * Created by hackpoint on 2017/5/19.
 */

public class HomeListEntity extends BaseObservable {

    private String title;
    private String date;
    private String imgUrl;

    public void setDate(String date) {
        this.date = date;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        notifyPropertyChanged(BR.imgUrl);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    @Bindable
    public String getImgUrl() {
        return imgUrl;
    }

    public String getTitle() {
        return title;
    }
}