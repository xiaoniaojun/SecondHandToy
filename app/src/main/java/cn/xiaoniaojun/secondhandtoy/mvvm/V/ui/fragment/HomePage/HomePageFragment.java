package cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.fragment.HomePage;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.xiaoniaojun.secondhandtoy.R;
import cn.xiaoniaojun.secondhandtoy.databinding.LayoutHomePageFragmentBinding;
import cn.xiaoniaojun.secondhandtoy.mvvm.V.layer.HomeViewLayer;
import cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.fragment.BaseMainFragment;
import cn.xiaoniaojun.secondhandtoy.mvvm.VM.IHomeFragmentViewModel;
import cn.xiaoniaojun.secondhandtoy.mvvm.VM.impl.HomeFragmentViewModel;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Package: cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.fragment.HomePage
 * Created by hackpoint on 2017/5/19.
 */

public class HomePageFragment extends BaseMainFragment {
    private IHomeFragmentViewModel mViewModel;

    public static HomePageFragment newInstance() {
        HomePageFragment fragment = new HomePageFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutHomePageFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.layout_home_page_fragment, container, false);
        mViewModel = new HomeFragmentViewModel(new HomeViewLayer(binding, this, savedInstanceState));
        mViewModel.bind();
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        mViewModel.unbind();
        super.onDestroyView();
    }
}

