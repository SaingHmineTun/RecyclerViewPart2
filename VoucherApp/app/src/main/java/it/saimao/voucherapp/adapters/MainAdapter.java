package it.saimao.voucherapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import it.saimao.voucherapp.model.Product;
import it.saimao.voucherapp.databinding.ItemProductBinding;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {


    private List<Product> products = new ArrayList<>();
    private OnDeleteListener onDeleteListener;

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductBinding binding = ItemProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MainViewHolder(binding);
    }

    public void setOnDeleteListener(OnDeleteListener onDeleteListener) {
        this.onDeleteListener = onDeleteListener;
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        Product product = products.get(position);
        holder.binding.tvName.setText(product.name());
        holder.binding.tvPrice.setText(product.price().toString());
        holder.binding.tvQuantity.setText(product.quantity().toString());
        holder.binding.btDeleteProduct.setOnClickListener(v -> {
            onDeleteListener.onDelete(product);
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    class MainViewHolder extends RecyclerView.ViewHolder {
        ItemProductBinding binding;

        MainViewHolder(ItemProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
