package com.search.task.searchers;

/**
 * Director class for constract searchers
 * through the searcher builder.
 * Created by IPahomov on 12.07.2016.
 */
public class Director {
    private SearcherBuilder searcherBuilder;

    public void setSearcherBuilder(SearcherBuilder searcherBuilder) {
        this.searcherBuilder = searcherBuilder;
    }

    public Searcher getSearcher() {
        return searcherBuilder.getSearcher();
    }

    public void constructSearcher() {
        searcherBuilder.createNewSearcher();
        searcherBuilder.buildName();
        searcherBuilder.buildAddress();
        searcherBuilder.buildTag();
        searcherBuilder.buildRating();
    }
}
