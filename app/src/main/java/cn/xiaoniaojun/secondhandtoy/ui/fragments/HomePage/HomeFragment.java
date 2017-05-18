package cn.xiaoniaojun.secondhandtoy.ui.fragments.HomePage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

import cn.xiaoniaojun.bottomnavigationbar.CarouselView;
import cn.xiaoniaojun.secondhandtoy.R;
import cn.xiaoniaojun.secondhandtoy.ui.fragments.BaseMainFragment;

/**
 * Package: cn.xiaoniaojun.secondhandtoy.ui.fragments.HomePage
 * Created by hackpoint on 2017/5/11.
 */

public class HomeFragment extends BaseMainFragment {

    final private HomeFragment mFragment = this;
    private CarouselView mHeadCarouselView;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment_layout,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        mHeadCarouselView = (CarouselView) view.findViewById(R.id.stub_carousel_head);


    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final List<Bitmap> carouselBitmapList = new ArrayList<>();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.carousel_1);
        carouselBitmapList.add(bitmap);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.carousel_2);
        carouselBitmapList.add(bitmap);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.carousel_3);
        carouselBitmapList.add(bitmap);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.carousel_4);
        carouselBitmapList.add(bitmap);
        mHeadCarouselView.post(new Runnable() {
            @Override
            public void run() {
                mHeadCarouselView.setCarouselImages(carouselBitmapList);
            }
        });
    }
}
