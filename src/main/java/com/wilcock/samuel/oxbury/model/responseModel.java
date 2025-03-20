package com.wilcock.samuel.oxbury.model;

import org.springframework.http.HttpStatus;

public class responseModel {

    HttpStatus statusCode;
    String successString;
    public responseModel(HttpStatus statusCode, String successString) {
        this.statusCode = statusCode;
        this.successString = successString;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = HttpStatus.valueOf(statusCode);
    }

    public String getSuccessString() {
        return successString;
    }

    public void setSuccessString(String successString) {
        this.successString = successString;
    }

    @Override
    public String toString() {
        return "responseModel{" +
                "statusCode=" + statusCode +
                ", successString='" + successString + '\'' +
                '}';
    }
}
