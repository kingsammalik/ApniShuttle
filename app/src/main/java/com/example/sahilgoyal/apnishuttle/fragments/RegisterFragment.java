package com.example.sahilgoyal.apnishuttle.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.sahilgoyal.apnishuttle.HomeActivity;
import com.example.sahilgoyal.apnishuttle.R;
import com.example.sahilgoyal.apnishuttle.common.UtilityMethods;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.ErrorDto;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.PostDispatchs;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.ResponseTypes;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.DispatchPostResponse;

/**
 * Created by sahil.goyal on 4/6/2018.
 */

public class RegisterFragment extends Fragment implements PostDispatchs {

    EditText etName;
    EditText etMobile;
    EditText etEmail;
    EditText etPassword;
    EditText etConfirmPassowrd;
    EditText etLastName;
    Button register;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);
        etName = view.findViewById(R.id.etFirstname);
        etMobile = view.findViewById(R.id.etMobileNumber);
        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);
        etConfirmPassowrd = view.findViewById(R.id.etConfirmPassword);
        etLastName = view.findViewById(R.id.etLastName);
        register = view.findViewById(R.id.btnRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }
        });
        return view;
    }

    private void validateData() {


        if (TextUtils.isEmpty(etName.getText())) {

            etName.setError("Enter FirstName");
        } else if (TextUtils.isEmpty(etLastName.getText())) {

            etLastName.setError("Enter LastName");
        } else if (TextUtils.isEmpty(etMobile.getText()) || etMobile.getText().toString().length()!=10) {

            etMobile.setError("Enter Mobile");

        } else if (TextUtils.isEmpty(etEmail.getText()) || !etEmail.getText().toString().trim().matches(emailPattern)) {
            etEmail.setError("Enter Email");
        } else if (TextUtils.isEmpty(etPassword.getText())) {

            etPassword.setError("Enter Password");
        } else if (TextUtils.isEmpty(etConfirmPassowrd.getText())) {

            etConfirmPassowrd.setError("Enter Password");
        } else {

            UtilityMethods.showProgressDialog(getActivity(), "", "Please Wait");

            String value = etName.getText().toString() + ";" + etLastName.getText().toString() + ";" + etMobile.getText().toString() + ";" + etEmail.getText().toString() + ";" + etPassword.getText().toString() + ";" + etConfirmPassowrd.getText().toString();
            DispatchPostResponse.disptatchRequest(this, ResponseTypes.REGISTER, value, getActivity());

        }

    }

    @Override
    public void apiSuccess(Object body, ResponseTypes response) {

        getActivity().startActivity(new Intent(getActivity(), HomeActivity.class));
        getActivity().finish();

    }

    @Override
    public void apiError(ErrorDto error) {

    }

    @Override
    public void error(String body) {

    }
}
