package com.sahar.tweets_search.api.model.error;

/**
 * Created by Sahar on 03/03/2017.
 */

public class ApiError {
    String message;

    public ApiError() {
    }

    public ApiError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ApiError{" +
                "message='" + message + '\'' +
                '}';
    }
}
