package com.duarte.victor.plr.model;

public class ServiceResponse {
    private int code;
    private String message;

    public ServiceResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
