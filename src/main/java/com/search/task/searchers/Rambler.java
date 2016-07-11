package com.search.task.searchers;

/**
 * Created by IPahomov on 10.07.2016.
 */
public class Rambler extends AbstractSearcher {

    public Rambler(){
        this.name = "Rambler";
        this.rate = 1.173;
        this.address = "http://nova.rambler.ru/search?query=";
        this.tag = "h2.b-serp-item__header a";
    }
}
