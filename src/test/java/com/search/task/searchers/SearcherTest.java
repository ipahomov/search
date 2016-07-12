package com.search.task.searchers;

import com.search.task.link.Link;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Test searcher methods
 * Created by IPahomov on 12.07.2016.
 */
public class SearcherTest {
    private static final Logger log = Logger.getLogger(SearcherTest.class);
    private static Searcher google;

    @BeforeClass
    public static void prepareTest() {
        Director director = new Director();

        director.setSearcherBuilder(new GoogleSearcher());
        director.constructSearcher();
        google = director.getSearcher();
    }

    @Test
    public void testSearch() throws Exception {
        String request = "test";
        List<Link> links = google.search(request);

        assertNotNull(links);
        log.info("Links: " + links);
        assertNotEquals(0, links.size());
    }

    @Test
    public void testGetUrlName() throws Exception {
        String url = "http://www.weather-forecast.com/locations/Minsk/forecasts/latest";
        String urlName = google.getUrlName(url);
        log.info("URL name: " + urlName);
        assertEquals("www.weather-forecast.com/locations/Minsk/forecasts/latest", urlName);
    }
}