package com.example.challenge1.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Error {
    @SerializedName("name")
    @Expose
    private List<String> name = null;

    @SerializedName("invalid")
    @Expose
    private Integer invalid;

}
