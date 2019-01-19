package com.example.dsamo.foodmanager;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegistrationFragment extends Fragment {

    Button req_btn;
    Button back_btn;
    EditText user_name_text;
    EditText user_password_text;
    EditText user_password_again_text;
    Fragment authFragment;
    FragmentManager fragmentManager;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RegistrationFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static RegistrationFragment newInstance(String param1, String param2) {
        RegistrationFragment fragment = new RegistrationFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_registration, container, false);
        req_btn = (Button) v.findViewById(R.id.reg_ok);
        back_btn = (Button) v.findViewById(R.id.go_back);
        user_name_text = (EditText) v.findViewById(R.id.reg_user_name);
        user_password_text = (EditText) v.findViewById(R.id.reg_user_password);
        user_password_again_text = (EditText) v.findViewById(R.id.reg_user_password_again);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authFragment = new AuthorizationFragment();
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.out_alfa, R.anim.slide_right)
                        .replace(R.id.login_container, authFragment)
                        .commit();
            }
        });
        req_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s_name = user_name_text.getText().toString();
                if (s_name.matches("")) {
                    Toast.makeText(getActivity(), "Введите ваше имя", Toast.LENGTH_SHORT).show();
                    return;
                }
                String s1 = user_password_text.getText().toString();
                if (s1.matches("")) {
                    Toast.makeText(getActivity(), "Введите пароль", Toast.LENGTH_SHORT).show();
                    return;
                }
                String s2 = user_password_again_text.getText().toString();
                if (s2.matches("")) {
                    Toast.makeText(getActivity(), "Введите пароль повторно", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(s1.equals(s2)){
                    Toast.makeText(getActivity(), "Успешно!", Toast.LENGTH_SHORT).show();
                    //TODO Запись в базу данных пользователя и создание к нему холодильника
                    authFragment = new AuthorizationFragment();
                    fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .setCustomAnimations(R.anim.out_alfa, R.anim.slide_right)
                            .replace(R.id.login_container, authFragment)
                            .commit();
                    }
                }
        });
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

 /*   @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

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
}
