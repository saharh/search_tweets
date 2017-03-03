
package com.sahar.tweets_search.api.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Metadata {

    @JsonProperty("iso_language_code")
    private String isoLanguageCode;
    @JsonProperty("result_type")
    private String resultType;

    @JsonProperty("iso_language_code")
    public String getIsoLanguageCode() {
        return isoLanguageCode;
    }

    @JsonProperty("iso_language_code")
    public void setIsoLanguageCode(String isoLanguageCode) {
        this.isoLanguageCode = isoLanguageCode;
    }

    @JsonProperty("result_type")
    public String getResultType() {
        return resultType;
    }

    @JsonProperty("result_type")
    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

}
