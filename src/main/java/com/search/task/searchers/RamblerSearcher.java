package com.search.task.searchers;

/**
 * Rambler searcher
 * According to searchers rating for 2015 year
 * in Russia google has 1.73% popularity.
 * Created by IPahomov on 12.07.2016.
 */
public class RamblerSearcher extends SearcherBuilder {
    @Override
    public void buildName() {
        searcher.setName("Rambler");
    }

    @Override
    public void buildAddress() {
        searcher.setAddress("http://nova.rambler.ru/search?query=");
    }

    @Override
    public void buildTag() {
        searcher.setTag("h2.b-serp-item__header a");
    }

    @Override
    public void buildRating() {
        searcher.setRating(1.173);
    }
}
