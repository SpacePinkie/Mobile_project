package com.example.dsamo.foodmanager.models.classes;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dsamo.foodmanager.R;
import com.example.dsamo.foodmanager.models.database.entity.Product;

import java.util.List;

public class FridgeAdapter extends RecyclerView.Adapter<FridgeAdapter.MyViewholder> {
    private List<Product> mProducts;


    public static class MyViewholder extends RecyclerView.ViewHolder {
        TextView mProductName;
        ImageView mProductImage;

        public MyViewholder(View v) {
            super(v);
            mProductName = v.findViewById(R.id.product_name);
            mProductImage = v.findViewById(R.id.product_img);
        }
    }

    public FridgeAdapter(List<Product> myProducts, OnItemClickListener listener) {
        mProducts = myProducts;
        mOnItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Product item);
    }
    private OnItemClickListener mOnItemClickListener;
    @NonNull
    @Override
    public FridgeAdapter.MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fridge_item, parent, false);
        final MyViewholder vH = new MyViewholder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick(mProducts.get(vH.getPosition()));
                }
            }
        });
        return vH;
    }

    @NonNull
    @Override
    public void onBindViewHolder(MyViewholder holder, int position) {
        holder.mProductName.setText(mProducts.get(position).getName());
        holder.mProductImage.setImageResource(R.drawable.ic_menu_camera); //mProducts[position].getImage()
        if(mProducts.get(position).getValue() == 0)
            holder.mProductImage.setAlpha(0.3f);
        else
            holder.mProductImage.setAlpha(1f);
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }
}

