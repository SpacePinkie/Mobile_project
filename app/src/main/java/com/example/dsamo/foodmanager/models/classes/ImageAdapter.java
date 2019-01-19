package com.example.dsamo.foodmanager.models.classes;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dsamo.foodmanager.R;


import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {
    private static final int IMAGE_COUNT = 15;
    private List<String> image_fileName;

    public static class MyViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{
        ImageView mImage;
        ViewGroup mParent;
        public MyViewHolder(View v, ViewGroup parent){
            super(v);
            mParent = parent;
            mImage = (ImageView) v.findViewById(R.id.image_choose_item);
        }

        @Override
        public void onClick(View view) {
            mImage.setAlpha(0.5f);
            //Toast.makeText(view.getContext(), "Я картинка: " + getPosition(), Toast.LENGTH_SHORT).show();
        }
    }

    public ImageAdapter(){
        image_fileName = new ArrayList<String>();
        for(int i = 0; i < IMAGE_COUNT; i++){
            if(i < 10) {
                image_fileName.add("p_00" + i);
            }
            else if(i >=10 && i < 100){
                image_fileName.add("p_0" + i);
            }
            else{
                image_fileName.add("p_" + i);
            }
        }
    }


    @NonNull
    @Override
    public ImageAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        final ImageAdapter.MyViewHolder vH = new ImageAdapter.MyViewHolder(v, parent);
        return vH;
    }

    @NonNull
    @Override
    public void onBindViewHolder(ImageAdapter.MyViewHolder holder, int position) {
        int resID = holder.mParent.getContext().getResources().getIdentifier(image_fileName.get(position),
                "drawable", holder.mParent.getContext().getPackageName());
        holder.mImage.setImageResource(resID);
    }

    @Override
    public int getItemCount() {
        return image_fileName.size();
    }
}
