package com.example.myapplication.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.controller.MainController;
import com.example.myapplication.model.SinhVien;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements UserView {

    EditText edtTen;
    public EditText edtMa;
    Button btnThem;
    Button btnXoa;
    Button btnSua;

    TableLayout table;

    MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SinhVien sv = new SinhVien("123", "Hung");
        Gson gson = new Gson();
        String strJson = gson.toJson(sv);
        Log.e("String Json", strJson);
        edtTen = findViewById(R.id.tenSinhVien);
        edtMa = findViewById(R.id.maSinhVien);
        btnThem = findViewById(R.id.idthem);
        btnXoa = findViewById(R.id.idXoa);
        btnSua = findViewById(R.id.idSua);
        table = findViewById(R.id.tableSinhVien);
        controller = new MainController(this, this);
    }

    @Override
    public void setChangeXoaButtonClickListener(View.OnClickListener listener) {
        btnXoa.setOnClickListener(listener);
    }

    @Override
    public void setChangeThemButtonClickListener(View.OnClickListener listener) {
        btnThem.setOnClickListener(listener);
    }

    @Override
    public void setChangeSuaButtonClickListener(View.OnClickListener listener) {
        btnSua.setOnClickListener(listener);
    }

    @Override
    public String getStudentNameInput() {
        return edtTen.getText().toString().trim();
    }

    @Override
    public String getStudentIdInput() {
        return edtMa.getText().toString().trim();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public EditText getEdtMa() {
        return edtMa;
    }

    @Override
    public EditText getEdtTen() {
        return edtTen;
    }

    @Override
    public TableLayout getTableLayout() {
        return table;
    }
}