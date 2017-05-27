package cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.fragment.ToysOnOfferPage;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.xiaoniaojun.secondhandtoy.R;
import cn.xiaoniaojun.secondhandtoy.databinding.LayoutToysFragmentBinding;
import cn.xiaoniaojun.secondhandtoy.mvvm.M.entity.ToysEntity;
import cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.adapter.ToysAdapter;
import cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.adapter.ToysItemPaddingDecoration;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Package: cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.fragment.ToysOnOfferPage
 * Created by hackpoint on 2017/5/27.
 */

public class ToysProductFragment extends SupportFragment {

    private List<ToysEntity> mFakeToys;
    private LayoutToysFragmentBinding mBinding;

    public static ToysProductFragment newInstance() {

        Bundle args = new Bundle();

        ToysProductFragment fragment = new ToysProductFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_toys_fragment,container,false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mFakeToys = ToysEntity.createFakeProducts();
        initRecycler(mBinding.productsRecycler);
    }

    private void initRecycler(RecyclerView productsRecycler) {
        productsRecycler.setHasFixedSize(true);

        productsRecycler.setAdapter(new ToysAdapter(mFakeToys));
        productsRecycler.addItemDecoration(new ToysItemPaddingDecoration(getActivity()));
        productsRecycler.addOnItemTouchListener(new OnItemSelectedListener(getActivity()) {
            @Override
            public void onItemSelected(RecyclerView.ViewHolder holder, int position) {
                OrderDialogFragment.newInstance(mFakeToys.get(position)).show(getChildFragmentManager(), null);
            }
        });
    }
}
