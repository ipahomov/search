package com.search.task.searchers;

/**
 * Yandex searcher
 * According to searchers rating for 2015 year
 * in Russia google has 50.93% popularity.
 * Created by IPahomov on 12.07.2016.
 */
public class YandexSearcher extends SearcherBuilder {
    @Override
    public void buildName() {
        searcher.setName("Yandex");
    }

    @Override
    public void buildAddress() {
        searcher.setAddress("http://yandex.com/search?text=");
    }

    @Override
    public void buildTag() {
        searcher.setTag("h2.serp-item__title a");
    }

    @Override
    public void buildRating() {
        searcher.setRating(1.5093);
    }
}
