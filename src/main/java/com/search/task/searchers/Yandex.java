package com.search.task.searchers;

/**
 * Created by IPahomov on 09.07.2016.
 */
public class Yandex extends AbstractSearcher {

    public Yandex(){
        this.name = "Yandex";
        this.rate = 1.5093;
        this.ADDRESS = "http://yandex.com/search?text=";
        this.TAG = "h2.serp-item__title a";
    }
}
