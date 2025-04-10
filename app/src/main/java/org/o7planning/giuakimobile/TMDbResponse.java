package org.o7planning.giuakimobile;

import java.util.List;

public class TMDbResponse {
    private List<Item> results;

    public List<Item> getResults() {
        return results;
    }

    public void setResults(List<Item> results) {
        this.results = results;
    }
}