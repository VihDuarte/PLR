package com.duarte.victor.plr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Plr {

    @SerializedName("Id")
    @Expose
    private String id;


    @SerializedName("Text")
    @Expose
    private String text;

    @SerializedName("Created")
    @Expose
    private Date created;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
