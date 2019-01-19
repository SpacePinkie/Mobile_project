package com.example.dsamo.foodmanager.models.classes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dsamo.foodmanager.R;
import com.example.dsamo.foodmanager.models.database.entity.ItemOfList;
import com.example.dsamo.foodmanager.models.database.entity.Product;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewholder> {
    private List<ItemOfList> mProducts;

    public static class MyViewholder extends RecyclerView.ViewHolder{
        TextView mListProductName;
        ImageView mListProductImage;
        CheckBox mListCheckBox;
        public MyViewholder(View v){
            super(v);
            mListProductName = v.findViewById(R.id.list_product_name);
            mListProductImage = v.findViewById(R.id.list_product_img);
            mListCheckBox = v.findViewById(R.id.list_checkBox);
        }
    }

    public ListAdapter(List<ItemOfList> myProducts){
        mProducts = myProducts;
    }

    @NonNull
    @Override
    public ListAdapter.MyViewholder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ListAdapter.MyViewholder vH = new ListAdapter.MyViewholder(v);
        return vH;
    }

    @NonNull
    @Override
    public void onBindViewHolder(ListAdapter.MyViewholder holder, int position){
        holder.mListProductName.setText(mProducts.get(position).getText()); //
        holder.mListProductImage.setImageResource(R.drawable.ic_menu_camera); //mProducts[position].getImage()
        holder.mListCheckBox.setChecked(mProducts.get(position).getChecked());
    }

    @Override
    public int getItemCount(){ return mProducts.size(); }
}
