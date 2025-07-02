package com.example.myapplication.ui;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;

public interface UserView {
    Context getContext();  // Thêm dòng này

    EditText getEdtMa();

    EditText getEdtTen();

    String getStudentIdInput();

    TableLayout getTableLayout();

    void setChangeXoaButtonClickListener(View.OnClickListener listener);

    void setChangeThemButtonClickListener(View.OnClickListener listener);

    void setChangeSuaButtonClickListener(View.OnClickListener listener);

    String getStudentNameInput();
}
