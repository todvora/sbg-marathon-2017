package cz.tomasdvorak.sbg.marathon.y2017;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.tomasdvorak.sbg.marathon.y2017.dto.Result;
import cz.tomasdvorak.sbg.marathon.y2017.dto.RunResults;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Parser {
    private final RunResults data;

    public Parser(final InputStream inputStream) throws IOException {
        final RunResults results = new ObjectMapper().readValue(inputStream, RunResults.class);
        this.data = results;
    }

    public List<Result> getResults() {
        return data.getResults();
    }
}
