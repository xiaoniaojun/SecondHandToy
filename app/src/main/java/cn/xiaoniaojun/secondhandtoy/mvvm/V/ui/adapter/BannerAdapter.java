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
import cn.xiaoniaojun.secondhandtoy.databinding.ItemBannerListBinding;
import cn.xiaoniaojun.secondhandtoy.mvvm.M.entity.BannerEntity;

/**
 * Package: cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.adapter
 * Created by hackpoint on 2017/5/19.
 */

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.ViewHolder> {


    private Context mContext;
    private List<BannerEntity> mEntities;

    private View.OnClickListener mListener;

    public BannerAdapter(Context context, List<BannerEntity> entities) {
        mContext = context;
        mEntities = entities;
    }

    public void setListener(View.OnClickListener listener) {
        mListener = listener;
    }

    @Override
    public BannerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemBannerListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.item_banner_list,
                parent,
                false
        );

        if (mListener != null) {
            binding.imgvItemBanner.setOnClickListener(mListener);
        }

        return new ViewHolder(binding);
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
        holder.mBinding.imgvItemBanner .setOnClickListener(null);
        holder.mBinding.unbind();
    }

    @Override
    public void onBindViewHolder(BannerAdapter.ViewHolder holder, int position) {
        final int size = mEntities.size();
        if (size <= 0) {
            return;
        }

        BannerEntity entity = mEntities.get(position % size);
        holder.mBinding.setBanner(entity);
        // TODO: figure out why
        holder.mBinding.imgvItemBanner.setTag(entity);

    }

    /**
     * **Key** to perform carousel banner
     */
    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemBannerListBinding mBinding;

        public ViewHolder(ItemBannerListBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }
}
