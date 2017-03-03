package com.sahar.tweets_search.api;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.sahar.tweets_search.api.data.BaseResponse;
import com.sahar.tweets_search.api.data.Error;
import com.sahar.tweets_search.api.data.SearchTweetsResponse;
import com.sahar.tweets_search.api.data.Status;
import com.sahar.tweets_search.api.data.TokenResponse;
import com.sahar.tweets_search.api.model.CallbackEmpty;
import com.sahar.tweets_search.api.model.Tweet;
import com.sahar.tweets_search.api.model.TweetsResponse;
import com.sahar.tweets_search.api.model.error.ApiError;
import com.sahar.tweets_search.api.model.error.NotAuthenticatedApiError;
import com.sahar.tweets_search.api.model.error.UnknownApiError;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sahar on 03/03/2017.
 */

public class TwitterClient {

    private final TwitterApi mTwitterApi;
    private String mAccessToken;

    public TwitterClient() {
        mTwitterApi = TwitterApi.Creator.newTwitterApi();
    }

    public void authenticate(String consumer_key, String consumer_secret, final CallbackEmpty callback) {
        String auth;
        try {
            auth = generateAuthorizationHeader(consumer_key, consumer_secret);
        } catch (UnsupportedEncodingException e) {
            throw new InvalidParameterException("Couldn't generate authorization header from consumer key or secret");
        }
        mTwitterApi.oAuthToken("client_credentials", auth)
                .enqueue(new Callback<TokenResponse>() {
                    @Override
                    public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                        Log.d("", "onResponse: " + response.toString());
                        if (response.isSuccessful()) {
                            TokenResponse body = response.body();
                            if (!body.isSuccessful()) {
                                callback.onFailure(apiErrorFromResponse(body));
                            } else {
                                setAccessToken(body.getAccessToken());
                                callback.onSuccess();
                            }
                        } else {
                            try {
                                callback.onFailure(new NotAuthenticatedApiError("Error, Server returned: " + response.errorBody().string()));
                            } catch (IOException e) {
                                callback.onFailure(new NotAuthenticatedApiError());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<TokenResponse> call, Throwable t) {
                        Log.d("", "onFailure: " + t.toString());
                        callback.onFailure(new NotAuthenticatedApiError(t.toString()));
                    }
                });
    }

    private void setAccessToken(String accessToken) {
        mAccessToken = accessToken;
    }

    @Nullable
    private String generateAuthorizationHeader(String consumer_key, String consumer_secret) throws UnsupportedEncodingException {
        String auth = URLEncoder.encode(consumer_key, "UTF-8") + ":" + URLEncoder.encode(consumer_secret, "utf-8");
        auth = Base64.encodeToString(auth.getBytes("UTF-8"), Base64.NO_WRAP);
        return "Basic " + auth;
    }

    public void searchTweets(String hashtag, final com.sahar.tweets_search.api.model.Callback<TweetsResponse> callback) {
        if (TextUtils.isEmpty(mAccessToken)) {
            callback.onFailure(new NotAuthenticatedApiError("Authenticate first"));
        }
        mTwitterApi.searchTweets("#" + hashtag, "Bearer " + mAccessToken)
                .enqueue(new Callback<SearchTweetsResponse>() {
                    @Override
                    public void onResponse(Call<SearchTweetsResponse> call, Response<SearchTweetsResponse> response) {
                        Log.d("", "onResponse: " + response.toString());
                        if (response.isSuccessful()) {
                            SearchTweetsResponse body = response.body();
                            if (!body.isSuccessful()) {
                                callback.onFailure(apiErrorFromResponse(body));
                            } else {
                                List<Status> statuses = body.getStatuses();
                                List<Tweet> tweets = new ArrayList<>();
                                for (Status status : statuses) {
                                    tweets.add(new Tweet(status.getText()));
                                }
                                callback.onSuccess(new TweetsResponse(tweets));
                            }
                        } else {
                            try {
                                callback.onFailure(new UnknownApiError("Error, Server returned: " + response.errorBody().string()));
                            } catch (IOException e) {
                                callback.onFailure(new UnknownApiError());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<SearchTweetsResponse> call, Throwable t) {
                        Log.d("", "onFailure: " + t.toString());
                        callback.onFailure(new UnknownApiError(t.getMessage()));
                    }
                });
    }

    private static ApiError apiErrorFromResponse(BaseResponse response) {
        ApiError err;
        Error error = response.getErrors().get(0);
        String message = error.getMessage();
        switch (error.getCode()) {
            case 89:
            case 99:
                err = new NotAuthenticatedApiError(message);
                break;
            default:
                err = new UnknownApiError(message);
                break;
        }
        return err;
    }

//    private static class BaseResponseCallBack<T extends BaseResponse> implements Callback<T> {
//
//        private final com.sahar.tweets_search.api.model.Callback<BaseResponse> callback;
//
//        public BaseResponseCallBack(com.sahar.tweets_search.api.model.Callback<BaseResponse> callback) {
//            this.callback = callback;
//        }
//
//        @Override
//        public void onResponse(Call<T> call, Response<T> response) {
//            if (response.isSuccessful()) {
//                BaseResponse body = response.body();
//                if (!body.isSuccessful()) {
//                    callback.onFailure(apiErrorFromResponse(body));
//                } else {
//                    callback.onSuccess(body);
//                }
//            } else {
//                callback.onFailure(new NotAuthenticatedApiError(response.message()));
//            }
//        }
//
//        @Override
//        public void onFailure(Call<T> call, Throwable t) {
//            callback.onFailure(new UnknownApiError(t.getMessage()));
//        }
//    }
}
