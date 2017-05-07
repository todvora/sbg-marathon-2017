package cz.tomasdvorak.sbg.marathon.y2017.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RunResults {
    @JsonProperty("Results")
    private List<Result> results;


    public List<Result> getResults() {
        return results;
    }

    public void setResults(final List<Result> results) {
        this.results = results;
    }
}
