package cn.xiaoniaojun.secondhandtoy.mvvm.V.layer;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;

import cn.xiaoniaojun.secondhandtoy.R;
import cn.xiaoniaojun.secondhandtoy.databinding.LayoutHomeBannerBinding;
import cn.xiaoniaojun.secondhandtoy.databinding.LayoutHomePageFragmentBinding;
import cn.xiaoniaojun.secondhandtoy.mvvm.M.entity.BannerEntity;
import cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.adapter.BannerAdapter;
import cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.adapter.HomeListAdapter;
import cn.xiaoniaojun.secondhandtoy.mvvm.VM.IHomeFragmentViewModel;
import cn.xiaoniaojun.secondhandtoy.mvvm.core.DataBindingViewLayer;
import cn.xiaoniaojun.secondhandtoy.mvvm.core.bind.callback.BaseQuickAdapterChangedCallback;
import cn.xiaoniaojun.secondhandtoy.utils.DisplayUtil;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Package: cn.xiaoniaojun.secondhandtoy.mvvm.V.layer
 * Created by hackpoint on 2017/5/19.
 */

public class HomeViewLayer extends DataBindingViewLayer<LayoutHomePageFragmentBinding, IHomeFragmentViewModel, SupportFragment>
        implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {


    private IHomeFragmentViewModel mViewModel;
    private LayoutHomeBannerBinding mBannerBinding;
    private RecyclerView mBannerListView;
    private LinearLayout mDotLayout;
    private BannerAdapter mBannerAdapter;
    private HomeListAdapter mHomeListAdapter;


    public HomeViewLayer(LayoutHomePageFragmentBinding binding, SupportFragment supportFragment, Bundle savedInstanceState) {
        super(binding, supportFragment, savedInstanceState);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    protected void onAttach(IHomeFragmentViewModel viewModel) {
        mViewModel = viewModel;

        initView();
        mViewModel.loadHomeData();
    }

    private void initView() {
        initBannerListView();
        initHomeListView();
        initDataBinding();
    }

    private void initDataBinding() {
        mViewModel.addHomeListChangedCallback(new BaseQuickAdapterChangedCallback(mHomeListAdapter));
    }

    private void initHomeListView() {
        mHomeListAdapter = new HomeListAdapter(
                mContainer.getContext(),
                R.layout.item_home_list,
                mViewModel.getHomeList());
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContainer.getContext());
        mBinding.list.setLayoutManager(layoutManager);
        mBinding.list.setAdapter(mHomeListAdapter);
    }

    private void initBannerListView() {
        mBannerBinding = DataBindingUtil.inflate(
                LayoutInflater.from(mContainer.getContext()),
                R.layout.layout_home_banner, null, false);

        mBannerListView = mBannerBinding.bannerList;
        mDotLayout = mBannerBinding.layoutDot;

        int screenWidth = DisplayUtil.getScreenWidth(mContainer.getContext());
        int height = ((int) (screenWidth / 2.13));
        RelativeLayout.LayoutParams params =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, height);
        mBannerListView.setLayoutParams(params);

        mBannerAdapter = new BannerAdapter(mContainer.getContext(), mViewModel.getBannerEntityList());
        mBannerAdapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BannerEntity entity = (BannerEntity) v.getTag();
                mViewModel.onBannerItemClick(entity);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContainer.getContext(), LinearLayoutManager.HORIZONTAL, false);
        mBannerListView.setLayoutManager(layoutManager);
        mBannerListView.setAdapter(mBannerAdapter);
        mBannerListView.addOnScrollListener(mBannerScrollListener);


        /**
         *  {@link PagerSnapHelper} is the key to perform carousel banner,
         *  just like {@link android.support.v4.view.ViewPager}.
         */
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mBannerListView);
    }

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

    @Override
    public void onLoadMoreRequested() {

    }
}
