package com.example.callapipart1.ui;

import android.content.Context;
import android.view.View;

public interface UserView {

    Context getContext();  // Thêm dòng này

    void setCallApiButtonClickListener(View.OnClickListener listener);
}
