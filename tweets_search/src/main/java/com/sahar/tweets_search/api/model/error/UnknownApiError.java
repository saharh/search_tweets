package com.sahar.tweets_search.api.model.error;

/**
 * Created by Sahar on 03/03/2017.
 */

public class UnknownApiError extends ApiError {
    public UnknownApiError() {
    }

    public UnknownApiError(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "UnknownApiError{} " + super.toString();
    }
}
