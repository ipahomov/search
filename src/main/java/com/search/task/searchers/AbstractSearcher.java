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
 * Created by IPahomov on 09.07.2016.
 */
abstract public class AbstractSearcher {
    private static final Logger log = Logger.getLogger(AbstractSearcher.class);
    private static final String USER_AGENT = "Mozilla";
    protected String name;
    protected Double rate;
    protected String address;
    protected String tag;

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
                    .userAgent(USER_AGENT)
                    .get();
        } catch (IOException e) {
            log.error("Error connect" + e.getMessage());
        }

        int position = 1;
        if (doc != null) {
            for (Element result : doc.select(tag)) {
                String tempUrl = result.attr("href");
                log.info("Getting url from: " + tempUrl);

                if (null != tempUrl && tempUrl.contains("http")) {
                    Link link = new Link();
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
        String pattern = "(https?://[\\w\\d\\./-]+)";
        Pattern p = Pattern.compile(pattern);
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
}
