package com.example.myapplication.controller;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.ui.UserView;

import java.util.HashSet;
import java.util.Set;

public class MainController {
    Set<String> maSinhVienSet = new HashSet<>();
    private UserView view;
    private Context context;
    EditText edtTen;
    public EditText edtMa;
    TableLayout table;
    MainController controller;

    public MainController(UserView userView, Context context) {
        this.view = userView;
        this.context = userView.getContext();
        this.edtMa = view.getEdtMa();
        this.edtTen = view.getEdtTen();
        this.table = view.getTableLayout();
        view.setChangeThemButtonClickListener(new buttonThemClick());
        view.setChangeXoaButtonClickListener(new buttonXoaClick());
        view.setChangeSuaButtonClickListener(new buttonSuaClick());
    }

    private class buttonThemClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String ma = edtMa.getText().toString().trim();
            String ten = edtTen.getText().toString().trim();

            if (ma.isEmpty() || ten.isEmpty()) {
                Toast.makeText(context, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }
            if (maSinhVienSet.contains(ma)) {
                Toast.makeText(context, "Mã sinh viên đã tồn tại", Toast.LENGTH_SHORT).show();
                return;
            }
            // Thêm mã vào set
            maSinhVienSet.add(ma);
            // Tạo dòng mới
            TableRow row = new TableRow(context);

            TextView tvMa = new TextView(context);
            tvMa.setText(ma);
            tvMa.setGravity(Gravity.CENTER);
            tvMa.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1));

            TextView tvTen = new TextView(context);
            tvTen.setText(ten);
            tvTen.setGravity(Gravity.CENTER);
            tvTen.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1));

            row.addView(tvMa);
            row.addView(tvTen);
            table.addView(row);
        }
    }

    private class buttonXoaClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String macanxoa = view.getStudentIdInput();
            TableLayout table = view.getTableLayout();
            int rowCount = table.getChildCount();
            // Bắt đầu từ 1 để bỏ qua dòng tiêu đề
            for (int i = 1; i < rowCount; i++) {
                TableRow row = (TableRow) table.getChildAt(i);
                TextView tvMa = (TextView) row.getChildAt(0); // cột 0: mã sinh viên
                if (tvMa != null && tvMa.getText().toString().equals(macanxoa)) {
                    table.removeViewAt(i);               // Xóa dòng khỏi bảng
                    maSinhVienSet.remove(macanxoa);      // Xóa mã khỏi HashSet
                    Toast.makeText(context, "Đã xóa sinh viên có mã: " + macanxoa, Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            Toast.makeText(context, "Không tìm thấy mã sinh viên: " + macanxoa, Toast.LENGTH_SHORT).show();
        }
    }

    private class buttonSuaClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String macansua = view.getStudentIdInput();
            String tenMoi = view.getStudentNameInput().trim();
            TableLayout table = view.getTableLayout();
            int rowCount = table.getChildCount();
            if (macansua.isEmpty() || tenMoi.isEmpty()) {
                Toast.makeText(context, "Vui lòng nhập mã và tên mới", Toast.LENGTH_SHORT).show();
                return;
            }
            for (int i = 1; i < rowCount; i++) {
                TableRow row = (TableRow) table.getChildAt(i);
                TextView tvMa = (TextView) row.getChildAt(0); // cột 0: mã sinh viên
                TextView tvTen = (TextView) row.getChildAt(1);
                if (tvMa != null && tvTen != null && tvMa.getText().toString().equals(macansua)) {
                    tvTen.setText(tenMoi); // Cập nhật tên mới
                    Toast.makeText(context, "Đã sửa tên sinh viên", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            Toast.makeText(context, "Không tìm thấy mã sinh viên cần sửa", Toast.LENGTH_SHORT).show();
        }
    }
}
