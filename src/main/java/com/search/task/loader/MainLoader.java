package com.search.task.loader;

import com.search.task.searchers.*;
import com.search.task.services.SearchService;

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

        SearchService service = new SearchService(searchers);
        service.menu();
    }
}
