package com.example.challenge1.api.model;

import java.util.List;

public class Error {
    @SerializedName("name")
    @Expose
    private List<String> name = null;

    @SerializedName("invalid")
    @Expose
    private Integer invalid;

}
