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
        Director director = new Director();

        director.setSearcherBuilder(new GoogleSearcher());
        director.constructSearcher();
        Searcher google = director.getSearcher();

        director.setSearcherBuilder(new YandexSearcher());
        director.constructSearcher();
        Searcher yandex = director.getSearcher();

        director.setSearcherBuilder(new RamblerSearcher());
        director.constructSearcher();
        Searcher rambler = director.getSearcher();

        List<Searcher> searchers = new ArrayList<Searcher>();
        searchers.add(google);
        searchers.add(yandex);
        searchers.add(rambler);

        SearchService service = new SearchService(searchers);
        service.menu();
    }
}
