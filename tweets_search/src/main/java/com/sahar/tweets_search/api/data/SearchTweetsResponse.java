
package com.sahar.tweets_search.api.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchTweetsResponse extends BaseResponse {

    @JsonProperty("statuses")
    private List<Status> statuses = null;
    @JsonProperty("search_metadata")
    private SearchMetadata searchMetadata;

    @JsonProperty("statuses")
    public List<Status> getStatuses() {
        return statuses;
    }

    @JsonProperty("statuses")
    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }

    @JsonProperty("search_metadata")
    public SearchMetadata getSearchMetadata() {
        return searchMetadata;
    }

    @JsonProperty("search_metadata")
    public void setSearchMetadata(SearchMetadata searchMetadata) {
        this.searchMetadata = searchMetadata;
    }

}
