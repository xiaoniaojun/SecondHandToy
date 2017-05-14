package cn.xiaoniaojun.secondhandtoy.ui.fragments.HomePage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.xiaoniaojun.carousel.normalcarousel.NormalCarousel;
import cn.xiaoniaojun.secondhandtoy.R;
import cn.xiaoniaojun.secondhandtoy.ui.fragments.BaseMainFragment;

/**
 * Package: cn.xiaoniaojun.secondhandtoy.ui.fragments.HomePage
 * Created by hackpoint on 2017/5/11.
 */

public class HomeFragment extends BaseMainFragment {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment_layout,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final NormalCarousel carouselView = new NormalCarousel(getContext());
        carouselView.config(4,500,500);
        String[] urls = new String[]{"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494700164024&di=074d34a5cabd25deced0063a84efb9fc&imgtype=0&src=http%3A%2F%2Fww1.sinaimg.cn%2Fmw600%2F005DHhhLjw9ejgs9ymlwgj30h20p1dkz.jpg",
                "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2911358600,20096734&fm=23&gp=0.jpg", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494700209438&di=a3a27718d0f1f043f175ab5ff16fe99e&imgtype=0&src=http%3A%2F%2Fww1.sinaimg.cn%2Flarge%2F005vbOHfgw1ewxch9q24vj30pi0e1tbw.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494700209437&di=c388ed9491c782245a4f2f4d351c471d&imgtype=0&src=http%3A%2F%2Fww1.sinaimg.cn%2Fmw600%2F6b6d41ebtw1e6ovimxg8sj20qy11idma.jpg"};
        carouselView.setContentUrl(urls,this);
        carouselView.post(new Runnable() {
            @Override
            public void run() {
                Log.v("carouselView", "width:" + carouselView.getMeasuredWidth()+"height:" + carouselView.getMeasuredHeight());
            }
        });
        final ViewGroup container = (ViewGroup) view;
        container.addView(carouselView);
    }
}
