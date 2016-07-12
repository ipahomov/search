package com.search.task.services;

import com.search.task.link.Link;
import com.search.task.searchers.Searcher;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Multisearcher class for search through several
 * searchers.
 * Created by IPahomov on 12.07.2016.
 */
public class MultiSearcher {
    private static final Logger log = Logger.getLogger(MultiSearcher.class);
    public static List<Searcher> searchers;
    public static String request;
    private Set<Link> results;

    public MultiSearcher(List<Searcher> searcherList) {
        searchers = searcherList;
    }

    public Integer getSearchersCount() {
        return searchers.size();
    }

    /**
     * Main menu
     */
    public void menu() {
        System.out.println("Welcome to Multisearcher!");
        MenuOptions options = new MenuOptions();
        boolean needMenu = true;

        while (needMenu) {
            options.printOptions();
            int choice = options.getChoice();
            switch (choice) {
                case 0:
                    options.exit();
                    break;
                case 1:
                    startSearch(options.search());
                    break;
                default:
                    System.out.println("Enter correct number!");
                    break;
            }
        }
    }

    public void startSearch(String request) {
        searchLinks(request);
        Table resultsTable = new Table(this.results);
        resultsTable.printTable();
        resultsTable.writeTableToFile();
    }

    /**
     * Search request in all searchers
     *
     * @param request of user
     */
    private void searchLinks(String request) {
        // add all links with order position
        List<Link> allLinks = new ArrayList<Link>();
        int sPosition = 0;
        for (Searcher searcher : this.searchers) {
            List<Link> searcherLinks = searcher.search(request);
            for (Link link : searcherLinks) {
                int[] positions = new int[getSearchersCount()];
                positions[sPosition] = link.getPosition();
                link.setPositions(positions);
                allLinks.add(link);
            }
            sPosition++;
        }

        sort(allLinks);

    }

    /**
     * Sort all link to set with ratings
     *
     * @param allLinks from all searchers
     * @return sorted set
     */
    private Set<Link> sort(List<Link> allLinks) {
        this.results = new TreeSet<Link>();
        int allLinksSize = allLinks.size();
        for (int i = 0; i < allLinksSize; i++) {
            Link link1 = allLinks.get(i);
            int count = 0;
            for (int j = i + 1; j < allLinksSize; j++) {
                Link link2 = allLinks.get(j);

                if (link1.getUrl().equals(link2.getUrl())) {
                    int[] updateArr = link2.getPositions();
                    for (int m = 0; m < updateArr.length; m++) {
                        updateArr[m] += link1.getPositions()[m];
                    }
                    link2.setPositions(updateArr);
                    link2.setAverage(rate(link2));
                    results.add(link2);
                    count++;
                }
            }
            if (count == 0) {
                link1.setAverage(rate(link1));
                results.add(link1);

            }
        }
        log.info("Added links to set: " + results);
        return results;
    }

    /**
     * Rate request results to sort fo table
     *
     * @param link result
     * @return rating of link
     */
    private Double rate(Link link) {
        int[] positions = link.getPositions();
        int size = positions.length;
        double sumPositions = 0;
        for (int i = 0; i < size; ) {
            for (Searcher searcher : this.searchers) {
                if (positions[i] != 0) {
                    sumPositions += positions[i] * searcher.getRating();
                } else {
                    sumPositions += getSearchersCount() * (searcher.getRating() - 1);
                    size--;
                }
                i++;
            }
        }
        return sumPositions / size;
    }
}
