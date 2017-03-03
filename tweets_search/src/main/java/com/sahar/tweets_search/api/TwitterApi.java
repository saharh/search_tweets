package com.sahar.tweets_search.api;

import com.sahar.tweets_search.api.data.SearchTweetsResponse;
import com.sahar.tweets_search.api.data.TokenResponse;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Sahar on 03/03/2017.
 */

public interface TwitterApi {

    String API_VERSION = "1.1";
    String API_BASE_URL = "https://api.twitter.com/";

    @FormUrlEncoded
    @POST("oauth2/token")
    Call<TokenResponse> oAuthToken(@Field("grant_type") String grantType, @Header("Authorization") String authorization);

    @GET(API_VERSION + "/search/tweets.json")
    Call<SearchTweetsResponse> searchTweets(@Query("q") String query, @Header("Authorization") String authorization);

    class Creator {
        public static TwitterApi newTwitterApi() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .client(new OkHttpClient.Builder().build())
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
            return retrofit.create(TwitterApi.class);
        }
    }
}
