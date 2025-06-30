package com.example.callapipart1.api;

import com.example.callapipart1.model.Currency;
import com.example.callapipart1.model.Post;
import com.example.callapipart1.model.PostResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {
    // link api fake
    //https://685958c2138a18086dfe1407.mockapi.io/APISV/Currency

    //link api postman
    // https://postman-rest-api-learner.glitch.me//info
//    Gson gson = new GsonBuilder()
//            .setDateFormat("yyyy-MM-dd HH:mm:ss")
//            .create();
//    ApiService apiService = new Retrofit.Builder()
//            .baseUrl("https://685958c2138a18086dfe1407.mockapi.io/")
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()
//            .create(ApiService.class);
    @GET("APISV/Currency")
    Call<List<Currency>> getCurrencyList();

    @GET("APISV/Currency")
    Call<Currency> getCurrencyCall(@QueryMap Map<String,String>options);

    // http://apilayer.net/api/users/list
    @GET("api/users/list")
    Call<Currency>getListUser();
    // http://apilayer.net/api/users/list?sort=desc
    @GET("api/users/list")
    Call<Currency> getSort(@Query("sort")String sort);

    // http://apilayer.net/api/group/1/users
    @GET("api/group/{id}/users")
    Call<Currency> getListUserFromGroup(@Part("id") int groupId);

    // http://apilayer.net/api/group/1/users?sort=desc
    @GET("api/group/{id}/users")
    Call<Currency> getListUserFromGroup2(@Part("id") int groupId,@Query("sort") String sort);







    //link api postman
    // https://postman-rest-api-learner.glitch.me/info
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://postman-rest-api-learner.glitch.me/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
   @POST("info")
    Call<PostResponse> sendPost(@Body Post post);
}
