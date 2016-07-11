package com.search.task.loader;

import com.search.task.searchers.Google;
import com.search.task.searchers.Rambler;
import com.search.task.searchers.AbstractSearcher;
import com.search.task.searchers.Yandex;
import com.search.task.services.SearchService;

import java.util.ArrayList;
import java.util.List;


/**
 * Main loader
 */
public class MainLoader {
    public static void main(String[] args) {
        Google google = new Google();
        Yandex yandex = new Yandex();
        Rambler rambler = new Rambler();

        List<AbstractSearcher> searcherList = new ArrayList<AbstractSearcher>();
        searcherList.add(google);
        searcherList.add(yandex);
        searcherList.add(rambler);



        SearchService service = new SearchService(searcherList);
        service.start();


    }
}
