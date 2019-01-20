package com.example.dsamo.foodmanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dsamo.foodmanager.models.classes.FridgeAdapter;
import com.example.dsamo.foodmanager.models.database.entity.Fridge;
import com.example.dsamo.foodmanager.models.database.entity.Product;

import java.util.ArrayList;
import java.util.List;


public class FridgeFragment extends Fragment {

    private static final int REQUEST_CODE = 0;
    private static final int REQUEST_CODE_2 = 1;
    private static final String PRODUCT_NAME = "name";
    private static final String PRODUCT_TYPE = "type";
    private static final String PRODUCT_MEASUREMENT = "Measurement";
    private static final String PRODUCT_VALUE = "value";
    private static final String PRODUCT_IMAGE = "image";

    private RecyclerView mRecyclerView;
    private FridgeAdapter mAdapter;
    private RecyclerView.LayoutManager mlayoutManager;
    private int targetItem;


    private  List<Product> products;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FridgeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FridgeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FridgeFragment newInstance(String param1, String param2) {
        FridgeFragment fragment = new FridgeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fridge, container, false);
        products = App.getInstance().getDatabase().daoInterfaceProduct().getAll();
        mRecyclerView = (RecyclerView) v.findViewById(R.id.list_of_products);
        mlayoutManager = new GridLayoutManager(getActivity(),3);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mlayoutManager);
        mAdapter = new FridgeAdapter(products, new FridgeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product item) {
                FragmentManager fm = getFragmentManager();
                DataProductFragment dialog = DataProductFragment.newInstance(item);
                dialog.setTargetFragment(FridgeFragment.this, REQUEST_CODE);
                dialog.show(fm, "me");

            }

            @Override
            public void onLongItemClick(Product item) {
                products.remove(item);
                App.getInstance().getDatabase().daoInterfaceProduct().delete(item);
                mAdapter.notifyDataSetChanged();
            }
        });//TODO передать сюда продукты
        mRecyclerView.setAdapter(mAdapter);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

  /*  @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
*/
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != Activity.RESULT_OK) {
            return;
        }
        if(requestCode == REQUEST_CODE){
            Product p = new Product();
            p.setName((String) data.getSerializableExtra(PRODUCT_NAME));
            p.setValue((int) data.getSerializableExtra(PRODUCT_VALUE));
            p.setMeasurement((int) data.getSerializableExtra(PRODUCT_MEASUREMENT));
            p.setImage((String) data.getSerializableExtra(PRODUCT_IMAGE));
            p.setType((int) data.getSerializableExtra(PRODUCT_TYPE));
            for(int i = 0; i < products.size(); i++){
                if(products.get(i).getName().equals(p.getName())){
                    products.get(i).setValue(p.getValue());
                    products.get(i).setMeasurement(p.getMeasurement());
                }
            }
            App.getInstance().getDatabase().daoInterfaceProduct().update(p);
            mAdapter.notifyItemChanged(mAdapter.getTargetPosition());
        }
        if(requestCode == REQUEST_CODE_2){
            Product p = new Product();
            p.setName((String) data.getSerializableExtra(PRODUCT_NAME));
            p.setValue((int) data.getSerializableExtra(PRODUCT_VALUE));
            p.setMeasurement((int) data.getSerializableExtra(PRODUCT_MEASUREMENT));
            p.setImage((String) data.getSerializableExtra(PRODUCT_IMAGE));
            p.setType((int) data.getSerializableExtra(PRODUCT_TYPE));
            products.add(p);
            long id = App.getInstance().getDatabase().daoInterfaceProduct().insert(p);
            p.setId(id);
            mAdapter.notifyDataSetChanged();
        }
    }
}
