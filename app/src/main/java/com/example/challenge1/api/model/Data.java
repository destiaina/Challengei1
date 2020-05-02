package com.example.challenge1.api.model;

public class Data<T> {
    Links links;
    Meta meta;
    T data;

    public Data() {
    }

    public Data(Links links, Meta meta, T data) {
        this.links = links;
        this.meta = meta;
        this.data = data;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
