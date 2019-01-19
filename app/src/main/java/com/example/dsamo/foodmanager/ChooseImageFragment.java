package com.example.dsamo.foodmanager;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.dsamo.foodmanager.models.classes.FridgeAdapter;
import com.example.dsamo.foodmanager.models.classes.ImageAdapter;
import com.example.dsamo.foodmanager.models.database.entity.Product;

public class ChooseImageFragment extends DialogFragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mlayoutManager;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.product_image_choosing, null);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.image_container);
        mlayoutManager = new GridLayoutManager(getActivity(),3);
        mRecyclerView.setLayoutManager(mlayoutManager);
        mAdapter = new ImageAdapter();
        mRecyclerView.setAdapter(mAdapter);

        return new android.support.v7.app.AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Выбор картинки")
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }
}
