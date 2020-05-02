package com.example.challenge1.api.service;

import com.example.challenge1.api.model.NumberResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/api/validate")
    Call<NumberResponse> doValidate(@Query("access_key") String access_key, @Query("number") String number, @Query("country_code") String country_code, @Query("format") String format);



}
