package com.sahar.tweets_search.api.model;

import com.sahar.tweets_search.api.model.error.ApiError;

/**
 * Created by Sahar on 03/03/2017.
 */

public interface Callback<T> {
    void onSuccess(T response);

    void onFailure(ApiError apiError);
}
