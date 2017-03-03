
package com.sahar.tweets_search.api.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchMetadata {

    @JsonProperty("max_id")
    private Long maxId;
    @JsonProperty("since_id")
    private Long sinceId;
    @JsonProperty("refresh_url")
    private String refreshUrl;
    @JsonProperty("next_results")
    private String nextResults;
    @JsonProperty("count")
    private Long count;
    @JsonProperty("completed_in")
    private Double completedIn;
    @JsonProperty("since_id_str")
    private String sinceIdStr;
    @JsonProperty("query")
    private String query;
    @JsonProperty("max_id_str")
    private String maxIdStr;

    @JsonProperty("max_id")
    public Long getMaxId() {
        return maxId;
    }

    @JsonProperty("max_id")
    public void setMaxId(Long maxId) {
        this.maxId = maxId;
    }

    @JsonProperty("since_id")
    public Long getSinceId() {
        return sinceId;
    }

    @JsonProperty("since_id")
    public void setSinceId(Long sinceId) {
        this.sinceId = sinceId;
    }

    @JsonProperty("refresh_url")
    public String getRefreshUrl() {
        return refreshUrl;
    }

    @JsonProperty("refresh_url")
    public void setRefreshUrl(String refreshUrl) {
        this.refreshUrl = refreshUrl;
    }

    @JsonProperty("next_results")
    public String getNextResults() {
        return nextResults;
    }

    @JsonProperty("next_results")
    public void setNextResults(String nextResults) {
        this.nextResults = nextResults;
    }

    @JsonProperty("count")
    public Long getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(Long count) {
        this.count = count;
    }

    @JsonProperty("completed_in")
    public Double getCompletedIn() {
        return completedIn;
    }

    @JsonProperty("completed_in")
    public void setCompletedIn(Double completedIn) {
        this.completedIn = completedIn;
    }

    @JsonProperty("since_id_str")
    public String getSinceIdStr() {
        return sinceIdStr;
    }

    @JsonProperty("since_id_str")
    public void setSinceIdStr(String sinceIdStr) {
        this.sinceIdStr = sinceIdStr;
    }

    @JsonProperty("query")
    public String getQuery() {
        return query;
    }

    @JsonProperty("query")
    public void setQuery(String query) {
        this.query = query;
    }

    @JsonProperty("max_id_str")
    public String getMaxIdStr() {
        return maxIdStr;
    }

    @JsonProperty("max_id_str")
    public void setMaxIdStr(String maxIdStr) {
        this.maxIdStr = maxIdStr;
    }

}
