package com.search.task.loader;

import com.search.task.searchers.GoogleSearcher;
import com.search.task.searchers.RamblerSearcher;
import com.search.task.searchers.Searcher;
import com.search.task.searchers.YandexSearcher;
import com.search.task.services.MultiSearcher;

import java.util.ArrayList;
import java.util.List;


/**
 * Main loader
 */
public class MainLoader {
    public static void main(String[] args) {

        List<Searcher> searchers = new ArrayList<Searcher>();
        searchers.add(new GoogleSearcher());
        searchers.add(new YandexSearcher());
        searchers.add(new RamblerSearcher());

        MultiSearcher multiSearcher = new MultiSearcher(searchers);
        multiSearcher.menu();
    }
}
