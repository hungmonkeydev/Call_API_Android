package com.example.callapipart1.controller;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.callapipart1.api.ApiService;
import com.example.callapipart1.model.Post;
import com.example.callapipart1.model.PostResponse;
import com.example.callapipart1.ui.MainActivity;
import com.example.callapipart1.ui.UserView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainController {

    private  UserView view;
    private Context context;
    MainActivity mainActivity;
    public MainController(UserView userView,Context context) {
        this.view=userView;
        this.context =userView.getContext();
        this.mainActivity = (MainActivity) context;
        view.setCallApiButtonClickListener(new buttonCallApi());
    }
    private class buttonCallApi implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            mainActivity.sendPost();
        }
    }

}
