package com.sahar.tweets_search.api.model;

import java.util.List;

/**
 * Created by Sahar on 03/03/2017.
 */

public class TweetsResponse {
    List<Tweet> tweets;

    public TweetsResponse(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    @Override
    public String toString() {
        return "TweetsResponse{" +
                "tweets=" + tweets +
                '}';
    }
}
