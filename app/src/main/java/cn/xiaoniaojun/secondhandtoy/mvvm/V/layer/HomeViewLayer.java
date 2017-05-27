package cn.xiaoniaojun.secondhandtoy.mvvm.V.layer;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;

import cn.xiaoniaojun.secondhandtoy.R;
import cn.xiaoniaojun.secondhandtoy.databinding.LayoutCategoryListBinding;
import cn.xiaoniaojun.secondhandtoy.databinding.LayoutHomeBannerBinding;
import cn.xiaoniaojun.secondhandtoy.databinding.LayoutHomePageFragmentBinding;
import cn.xiaoniaojun.secondhandtoy.mvvm.M.entity.BannerEntity;
import cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.adapter.BannerAdapter;
import cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.adapter.BannerIndicatorItemDecoration;
import cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.adapter.HomeListAdapter;
import cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.adapter.HomePageCategoryAdapter;
import cn.xiaoniaojun.secondhandtoy.mvvm.VM.IHomeFragmentViewModel;
import cn.xiaoniaojun.secondhandtoy.mvvm.core.DataBindingViewLayer;
import cn.xiaoniaojun.secondhandtoy.mvvm.core.bind.callback.BaseQuickAdapterChangedCallback;
import cn.xiaoniaojun.secondhandtoy.mvvm.core.bind.callback.RecyclerViewAdapterChangedCallback;
import cn.xiaoniaojun.secondhandtoy.utils.DisplayUtil;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Package: cn.xiaoniaojun.secondhandtoy.mvvm.V.layer
 * Created by hackpoint on 2017/5/19.
 */

public class HomeViewLayer extends DataBindingViewLayer<LayoutHomePageFragmentBinding, IHomeFragmentViewModel, SupportFragment>
        implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    //--------------------------------------------------------------------------------------------------
  /* Local Field */
//--------------------------------------------------------------------------------------------------
    private IHomeFragmentViewModel mViewModel;
    private LayoutHomeBannerBinding mBannerBinding;
    private LayoutCategoryListBinding mCategoryListBinding;
    private RecyclerView mBannerListView;
    private LinearLayout mDotLayout;
    private BannerAdapter mBannerAdapter;
    private HomeListAdapter mHomeListAdapter;
    private HomePageCategoryAdapter mHomePageCategoryAdapter;
    private RecyclerView mCategroyList;
    private TabLayout mTabLayout;
    private TabLayout mStickyTabLayout;
    private int mNearPosition;
    private int mHottestPosition;
    private int mHomeListOffsetY;
    private int mStickyPositionY;
    private ViewGroup mViewContainer;

    private int mInitPositionY = -1; // init RecycleView y position

    //--------------------------------------------------------------------------------------------------
  /* Initialize */
//--------------------------------------------------------------------------------------------------
    public HomeViewLayer(LayoutHomePageFragmentBinding binding, SupportFragment supportFragment, Bundle savedInstanceState) {
        super(binding, supportFragment, savedInstanceState);

        // get status bar height
        int statusBarHeight = -1;
        int statusBarResId = mContainer.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (statusBarResId > 0) {
            statusBarHeight = mContainer.getResources().getDimensionPixelOffset(statusBarResId);
            mHomeListOffsetY = DisplayUtil.dip2px(mContainer.getContext(), 46);
            mStickyPositionY = statusBarHeight + mHomeListOffsetY;
        }

    }

    @Override
    public void onRefresh() {

    }

    @Override
    protected void onAttach(IHomeFragmentViewModel viewModel) {
        mViewModel = viewModel;

        initView();
        initDataBinding();
        loadData();
    }

    private void loadData() {
        mViewModel.loadHomeData();
        mViewModel.loadBanner();
        mViewModel.loadHomePageCategoryData();

    }

    private void initView() {
        // TODO: init [Banner,Category,Tab]
        initBannerListView();
        initCategoryListView();
        initTabView();

        initHomeListView();

    }


//--------------------------------------------------------------------------------------------------
  /* Init Views */
//--------------------------------------------------------------------------------------------------

    private void initTabView() {


        mTabLayout = new TabLayout(mContainer.getContext());
        TabLayout.LayoutParams lp = new TabLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                DisplayUtil.dip2px(mContainer.getContext(), 46));
        mTabLayout.setLayoutParams(lp);

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(mContainer.getContext(), R.color.colorPrimary));
        mTabLayout.setSelectedTabIndicatorHeight(DisplayUtil.dip2px(mContainer.getContext(), 3));
        mTabLayout.setTabTextColors(
                ContextCompat.getColor(mContainer.getContext(), R.color.colorGeryTextDescribe),
                ContextCompat.getColor(mContainer.getContext(), android.R.color.primary_text_light)
        );
        mTabLayout.setBackgroundResource(R.drawable.shape_tab_layout_bg);
        TabLayout.Tab tab = mTabLayout.newTab().setText("热门");
        mTabLayout.addTab(tab, true);
        tab = mTabLayout.newTab().setText("附近");
        mTabLayout.addTab(tab);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (!mStickyTabLayout.getTabAt(position).isSelected()) {
                    mStickyTabLayout.getTabAt(position).select();
                    // TODO: Change data
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        /* Sticky Header */
        mStickyTabLayout = new TabLayout(mContainer.getContext());
        lp = new TabLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                DisplayUtil.dip2px(mContainer.getContext(), 46));
        mStickyTabLayout.setLayoutParams(lp);
        mStickyTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mStickyTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mStickyTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(mContainer.getContext(), R.color.colorPrimary));
        mStickyTabLayout.setSelectedTabIndicatorHeight(DisplayUtil.dip2px(mContainer.getContext(), 3));
        mStickyTabLayout.setTabTextColors(
                ContextCompat.getColor(mContainer.getContext(), R.color.colorGeryTextDescribe),
                ContextCompat.getColor(mContainer.getContext(), android.R.color.primary_text_light)
        );
        mStickyTabLayout.setBackgroundResource(R.drawable.shape_tab_layout_bg);
        tab = mStickyTabLayout.newTab().setText("热门");
        mStickyTabLayout.addTab(tab, true);
        tab = mStickyTabLayout.newTab().setText("附近");
        mStickyTabLayout.addTab(tab);
        mStickyTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (!mTabLayout.getTabAt(position).isSelected()) {
                    mTabLayout.getTabAt(position).select();
                    mBinding.homelist.stopScroll();

                    mHomeListAdapter.setEnableLoadMore(false);
                    // TODO: changed data
                    LinearLayoutManager manager
                            = (LinearLayoutManager) mBinding.homelist.getLayoutManager();

                    // TODO: Loading View
                    int firstPosition = manager.findFirstVisibleItemPosition()
                            + mHomeListAdapter.getHeaderLayoutCount();
                    View firstVisibleView = manager.findViewByPosition(firstPosition);
                    if (position == 0) {
                        mHottestPosition = firstPosition;
                    } else {
                        mNearPosition = firstPosition;
                    }
                    if (firstVisibleView != null) {
                        mHomeListOffsetY = (int) firstVisibleView.getY();
                    }

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void initCategoryListView() {
        mHomePageCategoryAdapter = new HomePageCategoryAdapter(
                mContainer.getContext(),
                mViewModel.getHomePageCategoryList(),
                null/* TODO: onClick */
        );

        mCategoryListBinding = DataBindingUtil.inflate(LayoutInflater.from(mContainer.getContext()),
                R.layout.layout_category_list, null, false);
        mCategroyList = mCategoryListBinding.listCategory;

        GridLayoutManager manager = new GridLayoutManager(mContainer.getContext(), 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mCategroyList.setLayoutManager(manager);
        mCategroyList.setAdapter(mHomePageCategoryAdapter);


    }


    private void initHomeListView() {
        mViewContainer = mBinding.container;
        mHomeListAdapter = new HomeListAdapter(
                mContainer.getContext(),
                R.layout.item_home_list,
                mViewModel.getHomeList());
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContainer.getContext());
        mBinding.homelist.setLayoutManager(layoutManager);
        mBinding.homelist.setHasFixedSize(true);

        /* on Recycler View Scroll */
        mBinding.homelist.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int[] location = new int[2];
                mTabLayout.getLocationInWindow(location);
                Log.v("位置信息", String.format("getLocationInWindowY: %s, mStickyPositionY: %s",location[1],mStickyPositionY ));

                int count = mViewContainer.getChildCount();
                if (location[1] <= mStickyPositionY) {
                    if (count == 1) {
                        mViewContainer.addView(mStickyTabLayout);
                        mBinding.layoutRefresh.setEnabled(false);
                    }
                } else {
                    if (count > 1) {
                        mViewContainer.removeView(mStickyTabLayout);
                        mHomeListOffsetY = DisplayUtil.dip2px(mContainer.getContext(), 46);
                        mBinding.layoutRefresh.setEnabled(true);
                    }
                }

                if (mInitPositionY == -1) {
                    mInitPositionY = location[1];
                }
                mHomeListOffsetY = location[1];
            }
        });


        /* add header views */
        // 1. Banner
        // 2. Category
        // 3. Tab
        // 4. Loading
        mHomeListAdapter.addHeaderView(mBannerBinding.getRoot());
        mHomeListAdapter.addHeaderView(mCategoryListBinding.getRoot());
        mBinding.homelist.setAdapter(mHomeListAdapter);

        View view = new View(mContainer.getContext());
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                DisplayUtil.dip2px(mContainer.getContext(), 12)));
        view.setBackgroundColor(ContextCompat.getColor(mContainer.getContext(), R.color.normal_background));
        mHomeListAdapter.addHeaderView(view);

        mHomeListAdapter.addHeaderView(mTabLayout);
    }

    private void initBannerListView() {
        // inflate , and get view reference.
        mBannerBinding = DataBindingUtil.inflate(
                LayoutInflater.from(mContainer.getContext()),
                R.layout.layout_home_banner, null, false);

        mBannerListView = mBannerBinding.bannerList;
        mDotLayout = mBannerBinding.layoutDot;

        // set Banner view params
        int screenWidth = DisplayUtil.getScreenWidth(mContainer.getContext());
        int height = ((int) (screenWidth / 2.13));
        RelativeLayout.LayoutParams params =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, height);
        mBannerListView.setLayoutParams(params);

        // create banner adapter
        mBannerAdapter = new BannerAdapter(mContainer.getContext(), mViewModel.getBannerEntityList());
        mBannerAdapter.setListener(v -> {
            BannerEntity entity = (BannerEntity) v.getTag();
            mViewModel.onBannerItemClick(entity);
        });

        // setup banner view
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContainer.getContext(), LinearLayoutManager.HORIZONTAL, false);
        mBannerListView.setLayoutManager(layoutManager);
        mBannerListView.setAdapter(mBannerAdapter);
        mBannerListView.addItemDecoration(new BannerIndicatorItemDecoration(mContainer.getContext(),
                4/* banner size */));
        mBannerListView.addOnScrollListener(mBannerScrollListener);


        /* PagerSnapHelper is the key to perform carousel banner,  just like ViewPager. */
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mBannerListView);
    }

    //--------------------------------------------------------------------------------------------------
  /* Listeners */
//--------------------------------------------------------------------------------------------------
    private RecyclerView.OnScrollListener mBannerScrollListener = new RecyclerView.OnScrollListener() {

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                int count = mDotLayout.getChildCount();
                if (count == 0) {
                    return;
                }

                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int position = manager.findFirstCompletelyVisibleItemPosition() % count;
                for (int i = 0; i < count; i++) {
                    View view = mDotLayout.getChildAt(i);
                    if (i == position) {
                        if (view.isSelected()) {
                            view.setSelected(false);
                        } else {
                            view.setSelected(true);
                        }
                    }
                }
            }
        }
    };

    //--------------------------------------------------------------------------------------------------
  /* Init DataBinding */
//--------------------------------------------------------------------------------------------------
    private void initDataBinding() {
        mViewModel.addHomeListChangedCallback(new BaseQuickAdapterChangedCallback(mHomeListAdapter));
        mViewModel.addBannerListChangedCallback(new RecyclerViewAdapterChangedCallback(mBannerAdapter));
        mViewModel.addHomePageCategoryListChangedCallback(new RecyclerViewAdapterChangedCallback(mHomeListAdapter));
    }


    @Override
    public void onLoadMoreRequested() {

    }
}
