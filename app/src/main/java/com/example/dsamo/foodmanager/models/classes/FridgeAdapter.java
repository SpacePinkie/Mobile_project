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
    private int targetPosition;
    private int longTargetPosition;
    private ItemClickListener mItemClickListener;

    public interface ItemClickListener{
        public void onItemClick(int item);
        public void onLongItemClick(int item);
    }

    public static class MyViewholder extends RecyclerView.ViewHolder {
        TextView mProductName;
        ImageView mProductImage;
        ViewGroup mParent;
        ItemClickListener mItemClickListener;
        public static int targetPosition;
        public static int longTargetPosition;

        public MyViewholder(View v, ViewGroup parent, ItemClickListener adapterListener) {
            super(v);
            this.mParent = parent;
            mItemClickListener = adapterListener;
            mProductName = v.findViewById(R.id.product_name);
            mProductImage = v.findViewById(R.id.product_img);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    targetPosition = getLayoutPosition();
                    if(mItemClickListener != null)
                        mItemClickListener.onItemClick(getLayoutPosition());
                }
            });
            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    longTargetPosition = getLayoutPosition();
                    if(mItemClickListener != null)
                        mItemClickListener.onLongItemClick(getLayoutPosition());
                    return true;
                }
            });
        }

    }

    public FridgeAdapter(List<Product> myProducts, ItemClickListener listener) {
        this.mItemClickListener = listener;
        mProducts = myProducts;
    }

    @NonNull
    @Override
    public FridgeAdapter.MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fridge_item, parent, false);
        final MyViewholder vH = new MyViewholder(v, parent, mItemClickListener);

        return vH;
    }

    @NonNull
    @Override
    public void onBindViewHolder(MyViewholder holder, int position) {
        holder.mProductName.setText(mProducts.get(position).getName());
        int id = holder.mParent.getContext().getResources().getIdentifier(mProducts.get(position).getImage(),
                "drawable", holder.mParent.getContext().getPackageName());
        holder.mProductImage.setImageResource(id);
        if(mProducts.get(position).getValue() == 0)
            holder.mProductImage.setAlpha(0.3f);
        else
            holder.mProductImage.setAlpha(1f);

    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public int getTargetPosition(){return MyViewholder.targetPosition;}
    public int getLongTargetPosition(){return MyViewholder.longTargetPosition;}
}

