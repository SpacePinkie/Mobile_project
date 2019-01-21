package com.example.dsamo.foodmanager;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dsamo.foodmanager.models.database.entity.Product;


public class DataProductFragment extends DialogFragment {
    private static final String PRODUCT_NAME = "name";
    private static final String PRODUCT_ID = "id";
    private static final String PRODUCT_TYPE = "type";
    private static final String PRODUCT_MEASUREMENT = "Measurement";
    private static final String PRODUCT_VALUE = "value";
    private static final String PRODUCT_IMAGE = "image";


    private Product product;
    private TextView value_text;
    private EditText edit_value_text;
    private LinearLayout btn_tool_layout;
    private TextView measurement_text;
    private Button btn_plus;
    private Button btn_minus;
    private ImageView image;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        product = new Product();
        product.setId((long) getArguments().getSerializable(PRODUCT_ID));
        product.setName((String) getArguments().getSerializable(PRODUCT_NAME));
        product.setType((long) getArguments().getSerializable(PRODUCT_TYPE));
        product.setMeasurement((long) getArguments().getSerializable(PRODUCT_MEASUREMENT));
        product.setImage((String) getArguments().getSerializable(PRODUCT_IMAGE));
        product.setValue((int) getArguments().getSerializable(PRODUCT_VALUE));

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.data_product_dialog, null);
        measurement_text = (TextView) v.findViewById(R.id.text_measurement);
        btn_tool_layout = (LinearLayout) v.findViewById(R.id.btn_tool);
        btn_plus = (Button) v.findViewById(R.id.btn_plus);
        btn_minus = (Button) v.findViewById(R.id.btn_minus);
        value_text = (TextView) v.findViewById(R.id.text_value);
        image = (ImageView) v.findViewById(R.id.data_image);
        measurement_text.setText(App.getInstance().getDatabase().
                daoInterfacePMeasurement().getById(product.getMeasurement()).getName());
        value_text.setText("" + product.getValue());
        edit_value_text = (EditText) v.findViewById(R.id.input_value);
        edit_value_text.setVisibility(View.GONE);
        value_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = edit_value_text.getText().toString();
                if (s.matches("")) {}
                else
                value_text.setText(s);
                edit_value_text.setVisibility(View.GONE);
                btn_tool_layout.setVisibility(View.GONE);
                edit_value_text.setText("");
            }
        });
        value_text.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view) {
                edit_value_text.setVisibility(View.VISIBLE);
                btn_tool_layout.setVisibility(View.VISIBLE);
                return true;
            }
        });
        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(value_text.getText().toString()) > 0)
                value_text.setText("" + (Integer.parseInt(value_text.getText().toString()) - 1));
            }
        });
        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value_text.setText("" + (Integer.parseInt(value_text.getText().toString()) + 1));
            }
        });
        int id = getContext().getResources().getIdentifier(product.getImage(), "drawable", getContext().getPackageName());
        image.setImageResource(id);

        return new android.support.v7.app.AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(product.getName())
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        product.setValue(Integer.parseInt(value_text.getText().toString()));
                        sendResult(Activity.RESULT_OK, product.getId(), product.getName(),product.getImage(), product.getValue(),
                                product.getType(), product.getMeasurement());
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create();

    }
    public static DataProductFragment newInstance(Product p){
        Bundle product = new Bundle();
        product.putSerializable(PRODUCT_ID, p.getId());
        product.putSerializable(PRODUCT_NAME, p.getName());
        product.putSerializable(PRODUCT_IMAGE, p.getImage());
        product.putSerializable(PRODUCT_MEASUREMENT, p.getMeasurement());
        product.putSerializable(PRODUCT_TYPE, p.getType());
        product.putSerializable(PRODUCT_VALUE, p.getValue());
        DataProductFragment mDataProductFragment = new DataProductFragment();
        mDataProductFragment.setArguments(product);
        return mDataProductFragment;
    }

    private void sendResult(int resultCode, long product_id, String product_name, String product_image,
                            int product_value, long product_type, long product_measurement){
        if(getTargetFragment() == null) {
            return;
        }
        else{
            Intent intent = new Intent();
            intent.putExtra(PRODUCT_ID, product_id);
            intent.putExtra(PRODUCT_NAME, product_name);
            intent.putExtra(PRODUCT_IMAGE, product_image);
            intent.putExtra(PRODUCT_VALUE, product_value);
            intent.putExtra(PRODUCT_MEASUREMENT, product_measurement);
            intent.putExtra(PRODUCT_TYPE, product_type);
            getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
        }
    }

}
