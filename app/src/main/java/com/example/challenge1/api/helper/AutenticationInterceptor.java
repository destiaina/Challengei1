package com.example.challenge1.api.helper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AutenticationInterceptor implements Interceptor {

    private String authToken;

    public AutenticationInterceptor(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder builder = original.newBuilder()
                .header("Authorization", authToken)
                .method(original.method(), original.body());
        Request request = builder.build();
        return chain.proceed(request);
    }
}
