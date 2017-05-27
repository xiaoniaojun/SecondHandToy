package cn.xiaoniaojun.secondhandtoy.mvvm.M.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import cn.xiaoniaojun.secondhandtoy.R;

/**
 * Package: cn.xiaoniaojun.secondhandtoy.mvvm.M.entity
 * Created by hackpoint on 2017/5/27.
 */

public class ToysEntity implements Serializable {
    public final String name;
    public final String price;
    public final int image;
    public final int color;
    public final String date;

    private ToysEntity(String name, String price, int image, int color) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.color = color;
        this.date = "1天前";
    }

    public static List<ToysEntity> createFakeProducts() {
        return Arrays.asList(
                new ToysEntity("帆布鞋", "￥ 450", R.drawable.img_sneaker, R.color.product_yellow),
                new ToysEntity("儿童拖鞋", "￥ 1800", R.drawable.img_sandal, R.color.product_green),
                new ToysEntity("高跟鞋", "￥ 4000", R.drawable.img_shoe, R.color.product_red),
                new ToysEntity("滑冰鞋", "￥ 300", R.drawable.img_ice_skate, R.color.product_purple)
        );
    }
}
