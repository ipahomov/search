package com.search.task.searchers;

import com.search.task.link.Link;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class Searcher for search requests.
 * Uses Jsoup to parse results page.
 *
 * @see <a href="http://revolweb.ru/prodvizhenie-sajtov/samye-populyarnye-poiskovye-sistemy-na-2015-god">
 * Ratings</a> for searchers rating in Russia for 2015 year.
 * Created by IPahomov on 12.07.2016.
 */
public abstract class Searcher {
    private static final Logger log = Logger.getLogger(Searcher.class);
    protected String name;
    protected String address;
    protected String tag;
    protected Double rating;
    protected String userAgent;

    /**
     * Get list of results from searcher
     *
     * @param request inputted by user
     * @return list of results
     */
    public List<Link> search(String request) {
        List<Link> links = new ArrayList<Link>();
        Document doc = null;
        try {
            doc = Jsoup.connect(address + request)
                    .userAgent(userAgent)
                    .timeout(5000)           //delete
                    .get();
        } catch (IOException e) {
            log.error("Error connect" + e.getMessage());
        }

        int position = 1;
        if (doc != null) {
            for (Element result : doc.select(tag)) {
                String tempUrl = result.attr("href");
                log.info("Getting url from: " + tempUrl);

                Link link;
                if (null != tempUrl && tempUrl.contains("http")) {
                    link = new Link();
                    link.setPosition(position++);
                    link.setUrl(getUrlName(tempUrl));
                    links.add(link);
                    log.info("Link added: " + link);
                }
            }
        }
        return links;
    }

    /**
     * Get url by regex pattern
     *
     * @param url to regex
     * @return url name
     */
    public String getUrlName(String url) {
        String urlName = "";
        String pattern = "https?://([\\w\\d\\./-]+)";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(url);
        if (matcher.find()) {
            urlName = matcher.group(1);
        }
        return urlName;
    }

    public String getName() {
        return name;
    }

    public Double getRating() {
        return rating;
    }

}
