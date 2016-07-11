package com.search.task.searchers;

/**
 * Created by IPahomov on 09.07.2016.
 */
public class Google extends AbstractSearcher {

    public Google(){
        this.name = "Google";
        this.rate = 1.4011;
        this.address = "http://google.by/search?q=";
        this.tag = "h3.r a";
    }

}
