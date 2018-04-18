package com.example.sahilgoyal.apnishuttle.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.sahilgoyal.apnishuttle.HomeActivity;
import com.example.sahilgoyal.apnishuttle.R;
import com.example.sahilgoyal.apnishuttle.common.UtilityMethods;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.ErrorDto;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.LoginResponse;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.PostDispatchs;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.ResponseTypes;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.models.DispatchPostResponse;
import com.example.sahilgoyal.apnishuttle.serverrequesthandler.prefrences.Prefrences;

/**
 * Created by sahil.goyal on 4/6/2018.
 */

public class LoginFragment extends Fragment implements PostDispatchs {


    EditText etName;
    EditText etPasswords;
    Button login;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        etName = view.findViewById(R.id.etEmail);
        etPasswords = view.findViewById(R.id.etPassword);
        login = view.findViewById(R.id.btnLogin);

        if (!TextUtils.isEmpty(Prefrences.getInstance().getToken(getActivity())) && !TextUtils.isEmpty(Prefrences.getInstance().getUsername(getActivity())) && !Prefrences.getInstance().getUsername(getActivity()).equalsIgnoreCase("") && !Prefrences.getInstance().getToken(getActivity()).equalsIgnoreCase("") && !Prefrences.getInstance().getPassword(getActivity()).equalsIgnoreCase("")) {

            etName.setText(Prefrences.getInstance().getUsername(getActivity()));
            etPasswords.setText(Prefrences.getInstance().getPassword(getActivity()));
            sendData(Prefrences.getInstance().getUsername(getActivity()), Prefrences.getInstance().getPassword(getActivity()));

        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validateData();

            }
        });
        return view;
    }

    private void sendData(String username, String password) {


        UtilityMethods.showProgressDialog(getActivity(), "", "Please Wait");

        String value = username + ";" + password;
        Log.e("tag","value "+value);
        DispatchPostResponse.disptatchRequest(this, ResponseTypes.LOGIN, value, getActivity());

    }

    private void validateData() {


        if (TextUtils.isEmpty(etName.getText())) {

            etName.setError("Enter Email");
        } else if (TextUtils.isEmpty(etPasswords.getText())) {

            etPasswords.setError("Enter Password");
        } else {

            UtilityMethods.showProgressDialog(getActivity(), "", "Please Wait");

            String value = etName.getText().toString() + ";" + etPasswords.getText().toString();
            Log.e("tag","value "+value);
            DispatchPostResponse.disptatchRequest(this, ResponseTypes.LOGIN, value, getActivity());

        }

    }

    @Override
    public void apiSuccess(Object body, ResponseTypes response) {

        LoginResponse loginResponse = (LoginResponse) body;
        Prefrences.getInstance().storeUserData(getActivity(), etName.getText().toString(), etPasswords.getText().toString(), loginResponse.getToken(),loginResponse.getUser().getEmail());

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
