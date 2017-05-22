package cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.xiaoniaojun.secondhandtoy.R;
import cn.xiaoniaojun.secondhandtoy.databinding.ItemHomePageCategoryListBinding;
import cn.xiaoniaojun.secondhandtoy.mvvm.M.entity.HomePageCategoryEntity;

/**
 * Package: cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.adapter
 * Created by hackpoint on 2017/5/21.
 */

public class HomePageCategoryAdapter extends RecyclerView.Adapter<HomePageCategoryAdapter.ViewHolder> {

    private Context mContext;
    private List<HomePageCategoryEntity> mData;

    private View.OnClickListener mListener;

    public HomePageCategoryAdapter(Context context, List<HomePageCategoryEntity> data, View.OnClickListener listener) {

        mContext = context;
        mData = data;
        mListener = listener;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemHomePageCategoryListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.item_home_page_category_list,
                parent, false);

        ViewHolder holder = new ViewHolder(binding);

        if (mListener != null) {
            binding.getRoot().setOnClickListener(mListener);
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mData.size() <= 0) {
            return;
        }
        holder.mBinding.setEntity(mData.get(position));
        //TODO: onClick
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
        holder.mBinding.getRoot().setOnClickListener(null);
        holder.mBinding.unbind();

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemHomePageCategoryListBinding mBinding;

        public ViewHolder(ItemHomePageCategoryListBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

    }

}
