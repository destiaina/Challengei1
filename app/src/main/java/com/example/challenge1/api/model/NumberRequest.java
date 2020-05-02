package com.example.challenge1.api.model;

public class NumberRequest {
    public String number;
    public String access_key;
    public String country_code;
    public String format;


    public NumberRequest(String number, String access_key, String country_code, String format) {
        this.number = number;
        this.access_key = access_key;
        this.country_code = country_code;
        this.format = format;
    }

    public String getAccess_key() {
        return access_key;
    }

    public void setAccess_key(String access_key) {
        this.access_key = access_key;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
