package com.example.dsamo.foodmanager.models.classes;

import android.app.Activity;
import android.content.Intent;
import android.net.sip.SipSession;
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
    private static final int IMAGE_COUNT = 19;
    private List<String> image_fileName;
    private ImageClickListener mImageClickListener;

    public interface ImageClickListener{
        public void onImageClick(String fileName);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{
        ImageView mImage;
        ViewGroup mParent;
        ImageClickListener mImageClickListener;
        String name;
        public MyViewHolder(View v, ViewGroup parent, ImageClickListener adapterListener){
            super(v);
            this.mImageClickListener = adapterListener;
            mParent = parent;
            mImage = (ImageView) v.findViewById(R.id.image_choose_item);
            mImage.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mImageClickListener != null){
                mImageClickListener.onImageClick(this.name);
            }
        }

    }

    public ImageAdapter(ImageAdapter.ImageClickListener listener){
        this.mImageClickListener = listener;
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
        final ImageAdapter.MyViewHolder vH = new ImageAdapter.MyViewHolder(v, parent, mImageClickListener);
        return vH;
    }

    @NonNull
    @Override
    public void onBindViewHolder(ImageAdapter.MyViewHolder holder, int position) {
        int resID = holder.mParent.getContext().getResources().getIdentifier(image_fileName.get(position),
                "drawable", holder.mParent.getContext().getPackageName());
        holder.mImage.setImageResource(resID);
        holder.name = image_fileName.get(position);
    }

    @Override
    public int getItemCount() {
        return image_fileName.size();
    }
}
