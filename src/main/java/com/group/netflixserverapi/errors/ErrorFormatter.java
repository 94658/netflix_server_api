package com.group.netflixserverapi.errors;

public class ErrorFormatter {
    private String error;

    public ErrorFormatter(String message) {
        this.error = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
