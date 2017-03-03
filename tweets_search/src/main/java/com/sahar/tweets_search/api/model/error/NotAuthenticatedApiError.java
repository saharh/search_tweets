package com.sahar.tweets_search.api.model.error;

/**
 * Created by Sahar on 03/03/2017.
 */

public class NotAuthenticatedApiError extends ApiError {
    public NotAuthenticatedApiError() {
    }

    public NotAuthenticatedApiError(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "NotAuthenticatedApiError{} " + super.toString();
    }
}
