package cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import cn.xiaoniaojun.secondhandtoy.R;
import cn.xiaoniaojun.secondhandtoy.databinding.ItemToysBinding;
import cn.xiaoniaojun.secondhandtoy.mvvm.M.entity.ToysEntity;

/**
 * Package: cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.adapter
 * Created by hackpoint on 2017/5/27.
 */

public class ToysAdapter extends RecyclerView.Adapter<ToysAdapter.ProductViewHolder> {
    private final List<ToysEntity> products;

    public ToysAdapter(List<ToysEntity> products) {
        this.products = products;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final ItemToysBinding productBinding = ItemToysBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);

        return new ProductViewHolder(productBinding);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.bind(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        private final ItemToysBinding binding;

        ProductViewHolder(ItemToysBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(ToysEntity product) {
            binding.setProduct(product);

            final GradientDrawable gradientDrawable = (GradientDrawable) ContextCompat.getDrawable(
                    itemView.getContext(), R.drawable.bg_product);

            gradientDrawable.setColor(ContextCompat.getColor(
                    itemView.getContext(), product.color));

            gradientDrawable.setSize(itemView.getWidth(), getDrawableHeight());

            gradientDrawable.mutate();

            binding.imgProduct.setBackground(gradientDrawable);
            binding.imgProduct.setImageResource(product.image);
        }

        private int getDrawableHeight() {
            final Context context = itemView.getContext();

            return getAdapterPosition() % 2 == 0
                    ? context.getResources().getDimensionPixelOffset(R.dimen.product_regular_height)
                    : context.getResources().getDimensionPixelOffset(R.dimen.product_large_height);
        }
    }

}

