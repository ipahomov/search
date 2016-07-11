package com.search.task.link;

import java.util.Comparator;

/**
 * Comparator for links
 * Created by IPahomov on 11.07.2016.
 */
public class LinkComp implements Comparator<Link> {
    public int compare(Link o1, Link o2) {
        int result = o1.getUrl().compareTo(o2.getUrl());
        if(result==0){
            return result;
        } else {
            return Double.compare(o1.getAverage(), o2.getAverage());
        }
    }

}
