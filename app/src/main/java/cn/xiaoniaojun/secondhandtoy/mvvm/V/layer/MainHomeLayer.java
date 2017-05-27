package cn.xiaoniaojun.secondhandtoy.mvvm.V.layer;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import cn.xiaoniaojun.bottomnavigationbar.BottomBarTab;
import cn.xiaoniaojun.bottomnavigationbar.BottomNavigationBar;
import cn.xiaoniaojun.secondhandtoy.R;
import cn.xiaoniaojun.secondhandtoy.databinding.LayoutHomeActivityBinding;
import cn.xiaoniaojun.secondhandtoy.databinding.LayoutToolBarSharedBinding;
import cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.activity.HomeActivity;
import cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.fragment.HomePage.HomePageFragment;
import cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.fragment.ToysOnOfferPage.ToysProductFragment;
import cn.xiaoniaojun.secondhandtoy.mvvm.VM.IMainHomeViewModel;
import cn.xiaoniaojun.secondhandtoy.mvvm.core.ViewLayer;
import cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.fragment.UserInfoPage.UserInfoFragment;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Package: cn.xiaoniaojun.secondhandtoy.mvvm.V.layer
 * Created by hackpoint on 2017/5/18.
 */

public class MainHomeLayer extends ViewLayer<IMainHomeViewModel, HomeActivity> {

    // Root Fragments
    // 分别表示(1)首页、(2)正在出售的玩具列表页、(3)玩具出售发布页面、(4)用户个人信息页面
    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_OFFER_TOYS = 1;
    private static final int FRAGMENT_SALE_TOYS = 2;
    private static final int FRAGMENT_USER_INFO = 3;

    private SupportFragment[] mFragments = new SupportFragment[4];
    private BottomNavigationBar mBottomNavigationBar;
    private Toolbar mToolbar;

    private IMainHomeViewModel mViewModel;
    private LayoutHomeActivityBinding mBinding;
    private LayoutToolBarSharedBinding mToolBarBinding;

    public MainHomeLayer(HomeActivity homeActivity, Bundle savedInstanceState) {
        super(homeActivity, savedInstanceState);
    }


    @Override
    protected void onAttach(IMainHomeViewModel viewModel) {
        mViewModel = viewModel;

        initView();
        initDataBinding();
    }

    private void initDataBinding() {
    }

    private void initView() {
        mBinding = DataBindingUtil.setContentView(mContainer, R.layout.layout_home_activity);
        mToolBarBinding = DataBindingUtil.inflate(mContainer.getLayoutInflater(), R.layout.layout_tool_bar_shared, null, false);


        mContainer.setSupportActionBar(mToolBarBinding.toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = mContainer.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(mContainer, android.R.color.holo_orange_light));
        }

        // Setup Fragments
        if (mSavedInstanceState == null) {
            mFragments[FRAGMENT_HOME] = HomePageFragment.newInstance();
            mFragments[FRAGMENT_USER_INFO] = UserInfoFragment.newInstance();
            mFragments[FRAGMENT_OFFER_TOYS] = ToysProductFragment.newInstance();
            mContainer.loadMultipleRootFragment(
                    R.id.fl_fragment_holder,
                    FRAGMENT_HOME,
                    mFragments[FRAGMENT_HOME],
                    mFragments[FRAGMENT_OFFER_TOYS],
                    mFragments[FRAGMENT_USER_INFO]
            );

        } else {
            mFragments[FRAGMENT_HOME] = mContainer.findFragment(HomePageFragment.class);
            mFragments[FRAGMENT_OFFER_TOYS] = mContainer.findFragment(ToysProductFragment.class);
            mFragments[FRAGMENT_USER_INFO] = mContainer.findFragment(UserInfoFragment.class);
        }

        //Setup BottomNavigationBar
        mBottomNavigationBar = mBinding.bottomNavigationBar;
        mBottomNavigationBar.addTab(R.drawable.selector_home, "主页", 0xffffffff);
        mBottomNavigationBar.addTab(R.drawable.selector_home, "玩具", 0xffffffff);
        mBottomNavigationBar.addTab(R.drawable.selector_home, "发布", 0xffffffff);
        mBottomNavigationBar.addTab(R.drawable.selector_home, "我", 0xffffffff);
        mBottomNavigationBar.setTextColorResId(R.color.bottom_bar_text_color);
        mBottomNavigationBar.setTextDefaultVisible(true);
        mBottomNavigationBar.setOnTabListener((tab, position, preposition) -> mContainer.showHideFragment(mFragments[position], mFragments[preposition]));
        mBottomNavigationBar.post(() -> mBottomNavigationBar.requestLayout());

        // Setup Category List

    }






    @Override
    protected void onDetach() {

    }
}
