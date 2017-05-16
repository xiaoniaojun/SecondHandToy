package cn.xiaoniaojun.secondhandtoy.ui.fragments.HomePage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import cn.xiaoniaojun.carousel.normalcarousel.NormalCarouselView;
import cn.xiaoniaojun.secondhandtoy.R;
import cn.xiaoniaojun.secondhandtoy.ui.fragments.BaseMainFragment;

/**
 * Package: cn.xiaoniaojun.secondhandtoy.ui.fragments.HomePage
 * Created by hackpoint on 2017/5/11.
 */

public class HomeFragment extends BaseMainFragment {

    final private HomeFragment mFragment = this;
    private NormalCarouselView mCarouselView;
    private GridLayout mGridCategory;

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
        mCarouselView = (NormalCarouselView) view.findViewById(R.id.stub_carousel_head);
        mCarouselView.config(4,500,5000);


    }
}
