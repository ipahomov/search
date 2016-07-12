package com.search.task.searchers;

/**
 * Google searcher
 * According to searchers rating for 2015 year
 * in Russia google has 40.11% popularity.
 * Created by IPahomov on 12.07.2016.
 */
public class GoogleSearcher extends Searcher {
    public GoogleSearcher() {
        this.name = "Google";
        this.address = "http://google.by/search?q=";
        this.tag = "h3.r a";
        this.rating = 1.4011;
        this.userAgent = "Mozilla";
    }
}
