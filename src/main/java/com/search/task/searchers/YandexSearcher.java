package com.search.task.searchers;

/**
 * Yandex searcher
 * According to searchers rating for 2015 year
 * in Russia google has 50.93% popularity.
 * Created by IPahomov on 12.07.2016.
 */
public class YandexSearcher extends Searcher {
    public YandexSearcher(){
        this.name = "Yandex";
        this.address = "http://yandex.com/search?text=";
        this.tag = "h2.serp-item__title a";
        this.rating = 1.5093;
        this.userAgent = "Mozilla";
    }
}
