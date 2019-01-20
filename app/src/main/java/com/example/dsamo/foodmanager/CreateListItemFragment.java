package com.example.dsamo.foodmanager;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class CreateListItemFragment extends DialogFragment {
    private static final int REQUEST_CODE = 0;
    private static final String TARGET_IMAGE = "target_image";
    private ImageView choose_image;
    private String targetImageName;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.create_list_item_dialog, null);
        EditText product_name = (EditText) v.findViewById(R.id.choose_name);
        choose_image = (ImageView) v.findViewById(R.id.choose_image);

        choose_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                ChooseImageFragment CIF = new ChooseImageFragment();
                CIF.setTargetFragment(CreateListItemFragment.this, REQUEST_CODE);
                CIF.show(fm, "me");
            }
        });

        return new android.support.v7.app.AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Создание элемента списка")
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != Activity.RESULT_OK){
            return;
        }
        if(requestCode == REQUEST_CODE){
            String targetImageName = (String) data.getSerializableExtra(TARGET_IMAGE);
            int id = getContext().getResources().getIdentifier(targetImageName,
                    "drawable", getContext().getPackageName());
            choose_image.setImageResource(id);
        }
    }
}
