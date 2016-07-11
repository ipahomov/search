package com.search.task.searchers;

import com.search.task.services.Link;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IPahomov on 09.07.2016.
 */
abstract public class AbstractSearcher {
    protected String name;
    protected Double rate;
    protected String ADDRESS;
    protected String TAG;
    protected Map<Integer, String> searcherResults;

    public List<Link> search(String request) {
        List<Link> links = new ArrayList<Link>();

        Document doc = null;
        try {
            doc = Jsoup.connect(ADDRESS + request)
                    .userAgent("Mozilla")
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int pos = 1;
        for (Element result : doc.select(TAG)) {
            String tempUrl = result.attr("href");

            if (tempUrl.contains("http")) {
                Link link = new Link(this.name);
                link.setTitle(result.text());
                link.setPosition(pos++);
                link.setUrl(getUrlName(tempUrl));
                links.add(link);
            }
        }

        return links;
    }

    public String getUrlName(String url) {
        String urlName = "";
        String patern = "(https?://[\\w\\d\\.-/]+)";
        Pattern p = Pattern.compile(patern);
        Matcher matcher = p.matcher(url);
        if (matcher.find()) {
            urlName = matcher.group(0);
        }

        return urlName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Map<Integer, String> getSearcherResults() {
        return searcherResults;
    }

    public void setSearcherResults(Map<Integer, String> searcherResults) {
        this.searcherResults = searcherResults;
    }
}
