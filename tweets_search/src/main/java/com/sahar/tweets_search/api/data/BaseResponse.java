package com.sahar.tweets_search.api.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Sahar on 03/03/2017.
 */

public class BaseResponse {
    @JsonProperty("errors")
    private List<Error> errors = null;

    @JsonProperty("errors")
    public List<Error> getErrors() {
        return errors;
    }

    @JsonProperty("errors")
    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public boolean isSuccessful() {
        return errors == null || errors.isEmpty();
    }

}
