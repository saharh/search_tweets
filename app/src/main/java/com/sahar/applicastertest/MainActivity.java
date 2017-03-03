package com.sahar.applicastertest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.sahar.tweets_search.api.TwitterClient;
import com.sahar.tweets_search.api.model.Callback;
import com.sahar.tweets_search.api.model.CallbackEmpty;
import com.sahar.tweets_search.api.model.Tweet;
import com.sahar.tweets_search.api.model.TweetsResponse;
import com.sahar.tweets_search.api.model.error.ApiError;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TwitterClient mTwitterClient;
    private boolean mAuthenticated;
    public static final String CONSUMER_KEY = "QlBKaCF5SheaE7OqQTH01wjUH";
    public static final String CONSUMER_SECRET = "yLMHAIWrC5KhhSBCLSiTHQ0YkvK1SF6YhzTv6Ezr6A73kn9BnJ";
    private ListView mResultsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mResultsList = (ListView) findViewById(R.id.results_list);
        final EditText hashtagInput = (EditText) findViewById(R.id.hashtag_input);
        Button searchBtn = (Button) findViewById(R.id.search_btn);

        mTwitterClient = new TwitterClient();

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hashtag = hashtagInput.getText().toString();
                if (mAuthenticated) {
                    searchForTweets(hashtag);
                } else {
                    authenticateAndSearch(hashtag);
                }
            }
        });
    }

    private void authenticateAndSearch(final String hashtag) {
        mTwitterClient.authenticate(CONSUMER_KEY, CONSUMER_SECRET, new CallbackEmpty() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "Success");
                mAuthenticated = true;
                searchForTweets(hashtag);
            }

            @Override
            public void onFailure(ApiError apiError) {
                Log.d(TAG, "Failed, " + apiError);
            }
        });
    }

    private void searchForTweets(String hashtag) {
        mTwitterClient.searchTweets(hashtag, new Callback<TweetsResponse>() {
            @Override
            public void onSuccess(TweetsResponse response) {
                Log.d(TAG, "Success, " + response.toString());
                mResultsList.setAdapter(new ArrayAdapter<Tweet>(MainActivity.this, android.R.layout.simple_list_item_1, response.getTweets()));
            }

            @Override
            public void onFailure(ApiError apiError) {
                Log.d(TAG, "Failed, " + apiError);
            }
        });
    }

}
