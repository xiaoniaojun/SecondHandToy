package cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.xiaoniaojun.secondhandtoy.R;
import cn.xiaoniaojun.secondhandtoy.databinding.ItemHomeListBinding;
import cn.xiaoniaojun.secondhandtoy.mvvm.M.entity.HomeListEntity;

/**
 * Package: cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.adapter
 * Created by hackpoint on 2017/5/19.
 */

public class HomeListAdapter extends BaseQuickAdapter<HomeListEntity, HomeListAdapter.ViewHolder>{

    private Context mContext;

    public HomeListAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<HomeListEntity> data) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected ViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        ItemHomeListBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext),
                R.layout.item_home_list,
                parent,
                false
        );
        ViewHolder holder = new ViewHolder(binding);
        return holder;
    }

    @Override
    protected ViewHolder createBaseViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void convert(ViewHolder holder, HomeListEntity item) {
        holder.mBinding.setEntity(item);
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        if (holder.mBinding != null) {
            holder.mBinding.unbind();
        }
    }

    public class ViewHolder extends BaseViewHolder {

        ItemHomeListBinding mBinding;

        // TODO: Figure out why
        public ViewHolder(View view) {
            super(view);
        }

        public ViewHolder(ItemHomeListBinding binding) {
            super(binding.getRoot());
            mBinding = binding;

        }
    }
}
