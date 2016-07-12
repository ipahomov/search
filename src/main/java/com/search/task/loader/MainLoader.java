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
        /*Google google = new Google();
        Yandex yandex = new Yandex();
        Rambler rambler = new Rambler();

        List<AbstractSearcher> searcherList = new ArrayList<AbstractSearcher>();
        searcherList.add(google);
        searcherList.add(yandex);
        searcherList.add(rambler);*/





        Director director = new Director();

        SearcherBuilder googleSearcher = new GoogleSearcher();
        director.setSearcherBuilder(googleSearcher);
        director.constructSearcher();
        Searcher google = director.getSearcher();

        SearcherBuilder yandexSearcher = new YandexSearcher();
        director.setSearcherBuilder(yandexSearcher);
        director.constructSearcher();
        Searcher yandex = director.getSearcher();

        SearcherBuilder ramblerSearcher = new RamblerSearcher();
        director.setSearcherBuilder(ramblerSearcher);
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
