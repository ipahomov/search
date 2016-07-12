package com.search.task.searchers;

/**
 * Rambler searcher
 * According to searchers rating for 2015 year
 * in Russia google has 1.73% popularity.
 * Created by IPahomov on 12.07.2016.
 */
public class RamblerSearcher extends Searcher {
    public RamblerSearcher() {
        this.name = "Rambler";
        this.address = "http://nova.rambler.ru/search?query=";
        this.tag = "h2.b-serp-item__header a";
        this.rating = 1.173;
        this.userAgent = "Mozilla";
    }
}
