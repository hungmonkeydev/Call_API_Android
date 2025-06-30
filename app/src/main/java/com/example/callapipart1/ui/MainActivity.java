package com.example.callapipart1.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.callapipart1.R;
import com.example.callapipart1.api.ApiService;
import com.example.callapipart1.model.Currency;
import com.example.callapipart1.model.Post;
import com.example.callapipart1.model.PostResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private TextView tvTerms;
    private TextView tvSource;
    private TextView tvUSDVND;
    private Button btnCallApi;
    private TextView tvPost;


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
        tvTerms = findViewById(R.id.tvTerms);
        tvSource = findViewById(R.id.tvSource);
        tvUSDVND = findViewById(R.id.tvUSDVND);
        btnCallApi = findViewById(R.id.btnCallApi);
        tvPost = findViewById(R.id.tvPost);
        btnCallApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               clickCallApi();
                sendPost();
            }
        });

    }
    private void clickCallApi() {
        ApiService.apiService.getCurrencyList().enqueue(new Callback<List<Currency>>() {
            @Override
            public void onResponse(Call<List<Currency>> call, Response<List<Currency>> response) {
                Toast.makeText(MainActivity.this, "Call API success", Toast.LENGTH_SHORT).show();
                List<Currency> currencyList = response.body();
                if (currencyList != null && !currencyList.isEmpty()) {
                    Currency currency = currencyList.get(0);
                    if (currency.isSuccess()) {
                        tvTerms.setText(currency.getTerms());
                        tvSource.setText(currency.getSource());
                        tvUSDVND.setText(String.valueOf(currency.getQuotes().getUsdVnd()));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Currency>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call API error", Toast.LENGTH_SHORT).show();
                Log.e("API_ERROR", t.getMessage(), t);
            }
        });
    }
    private void sendPost(){
        Post post = new Post(10,101,"Tincoder","Tincoder channel");
        ApiService.apiService.sendPost(post).enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    PostResponse postResponse = response.body();
                    Log.d("API_RESPONSE", postResponse.toString());
                    tvPost.setText("Post: " + postResponse.toString());
                } else {
                    Toast.makeText(MainActivity.this, "Lỗi: Không nhận được phản hồi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gọi API thất bại", Toast.LENGTH_SHORT).show();
            }
        });

    }
}