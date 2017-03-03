package com.sahar.tweets_search.api.model;

/**
 * Created by Sahar on 03/03/2017.
 */

public class Tweet {
    String status;

    public Tweet(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
