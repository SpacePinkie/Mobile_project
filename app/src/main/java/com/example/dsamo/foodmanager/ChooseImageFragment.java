package com.example.dsamo.foodmanager;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dsamo.foodmanager.models.classes.FridgeAdapter;
import com.example.dsamo.foodmanager.models.classes.ImageAdapter;
import com.example.dsamo.foodmanager.models.database.entity.Product;

public class ChooseImageFragment extends DialogFragment implements ImageAdapter.ImageClickListener {

    private static final String TARGET_IMAGE = "target_image";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mlayoutManager;
    private String targetImageName;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.product_image_choosing, null);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.image_container);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mlayoutManager = new GridLayoutManager(getActivity(),3);
        mRecyclerView.setLayoutManager(mlayoutManager);
        mAdapter = new ImageAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        return new android.support.v7.app.AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Выбор картинки")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(targetImageName != null)
                            sendResult(Activity.RESULT_OK, targetImageName);
                        else
                            Toast.makeText(getContext(), "Вы не выбрали картинку", Toast.LENGTH_LONG).show();
                    }
                })
                .create();
    }

    private void sendResult(int resultCode, String imageName){
        if(getTargetFragment() == null) {
            return;
        }
        else{
            Intent intent = new Intent();
            intent.putExtra(TARGET_IMAGE, imageName);
            getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
        }
    }

    @Override
    public void onImageClick(String fileName) {
        targetImageName = fileName;
    }
}
