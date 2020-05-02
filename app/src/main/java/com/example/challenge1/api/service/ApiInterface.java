package com.example.challenge1.api.service;

import com.example.challenge1.api.model.AppVersion;
import com.example.challenge1.api.model.NumberResponse;

public interface ApiInterface {
    @GET("/")
    Call<AppVersion> getAppVersion();

    @GET("/api/validate")
    Call<NumberResponse> doValidate(@Query("access_key") String access_key, @Query("number") String number, @Query("country_code") String country_code, @Query("format") String format);



}
