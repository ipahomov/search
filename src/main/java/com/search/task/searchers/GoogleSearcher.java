package com.search.task.searchers;

/**
 * Google searcher
 * According to searchers rating for 2015 year
 * in Russia google has 40.11% popularity.
 * Created by IPahomov on 12.07.2016.
 */
public class GoogleSearcher extends SearcherBuilder {
    @Override
    public void buildName() {
        searcher.setName("Google");
    }

    @Override
    public void buildAddress() {
        searcher.setAddress("http://google.by/search?q=");
    }

    @Override
    public void buildTag() {
        searcher.setTag("h3.r a");
    }

    @Override
    public void buildRating() {
        searcher.setRating(1.4011);
    }
}
