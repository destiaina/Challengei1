package com.example.challenge1.api.model;

import com.example.challenge1.api.helper.ServiceGenerator;
import com.google.android.gms.common.api.Response;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;

public class Links {
    public static ApiError parseError(Response<?> response) {
        Converter<ResponseBody, ApiError> converter =
                ServiceGenerator.retrofit()
                        .responseBodyConverter(ApiError.class, new Annotation[0]);

        ApiError error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ApiError();
        }

        return error;
    }
}
