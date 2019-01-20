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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.dsamo.foodmanager.models.database.entity.PMeasurement;
import com.example.dsamo.foodmanager.models.database.entity.PType;
import com.example.dsamo.foodmanager.models.database.entity.Product;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CreateProductFragment extends DialogFragment {
    private static final int REQUEST_CODE = 0;
    private static final String PRODUCT_NAME = "name";
    private static final String PRODUCT_TYPE = "type";
    private static final String PRODUCT_MEASUREMENT = "Measurement";
    private static final String PRODUCT_VALUE = "value";
    private static final String PRODUCT_IMAGE = "image";
    private static final String TARGET_IMAGE = "target_image";
    private ImageView choose_image;
    private String targetImageName;
    private List<String> type_list;
    private List<String> measurement_list;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.create_product_dialog, null);
        type_list = new ArrayList<String>();
        measurement_list = new ArrayList<String>();
        final EditText product_name = (EditText) v.findViewById(R.id.choose_name);
        final Spinner choose_measurement = (Spinner) v.findViewById(R.id.choose_measurement);
        final Spinner choose_type = (Spinner) v.findViewById(R.id.choose_type);
        choose_image = (ImageView) v.findViewById(R.id.choose_image);

        choose_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                ChooseImageFragment CIF = new ChooseImageFragment();
                CIF.setTargetFragment(CreateProductFragment.this, REQUEST_CODE);
                CIF.show(fm, "me");
            }
        });
        final List<PType> pType = App.getInstance().getDatabase().daoInterfacePType().getAll();
        for(int i = 0; i < pType.size(); i++)
            type_list.add(pType.get(i).getName());
        final List<PMeasurement> pMeasurements = App.getInstance().getDatabase().daoInterfacePMeasurement().getAll();
        for(int i = 0; i < pType.size(); i++)
            measurement_list.add(pMeasurements.get(i).getName());
        ArrayAdapter<String> type_adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, type_list);
        type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> measurement_adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, measurement_list);
        measurement_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        choose_type.setAdapter(type_adapter);
        choose_measurement.setAdapter(measurement_adapter);

        return new android.support.v7.app.AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Создание продукта")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String productName = product_name.getText().toString();
                        if(!productName.equals("")){
                            if(targetImageName != null){
                                int productValue = 0;
                                int productType = 0;
                                for(int g = 0; g < pType.size(); g++)
                                    if(pType.get(g).getName().equals(choose_type.getSelectedItem().toString()))
                                        productType = pType.get(g).getId();//TODO returned
                                int productMeasurement = 0;
                                for(int g = 0; g < pMeasurements.size(); g++)
                                    if(pMeasurements.get(g).getName().equals(choose_measurement.getSelectedItem().toString()))
                                        productMeasurement = pMeasurements.get(g).getId();
                                sendResult(Activity.RESULT_OK, productName, targetImageName,
                                        productValue, productType, productMeasurement);
                            }
                        }
                    }
                })
                .create();
    }

    private void sendResult(int resultCode, String product_name, String product_image,
                            int product_value, int product_type, int product_measurement){
        if(getTargetFragment() == null) {
            return;
        }
        else{
            Intent intent = new Intent();
            intent.putExtra(PRODUCT_NAME, product_name);
            intent.putExtra(PRODUCT_IMAGE, product_image);
            intent.putExtra(PRODUCT_VALUE, product_value);
            intent.putExtra(PRODUCT_MEASUREMENT, product_measurement);
            intent.putExtra(PRODUCT_TYPE, product_type);
            getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != Activity.RESULT_OK){
            return;
        }
        if(requestCode == REQUEST_CODE){
            targetImageName = (String) data.getSerializableExtra(TARGET_IMAGE);
            int id = getContext().getResources().getIdentifier(targetImageName,
                    "drawable", getContext().getPackageName());
            choose_image.setImageResource(id);
        }
    }
}
