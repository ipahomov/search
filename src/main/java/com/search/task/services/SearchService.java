package com.search.task.services;

import com.search.task.searchers.AbstractSearcher;

import java.util.*;

/**
 * Created by IPahomov on 09.07.2016.
 */
public class SearchService {
    private List<AbstractSearcher> searchers;
    private Integer searchersCount;
    private Map<Double, Link> results;

    public SearchService(List<AbstractSearcher> searchers) {
        this.searchers = searchers;
        this.searchersCount = searchers.size();
    }

    /**
     * Start multisearcher
     */
    public void start() {
        System.out.println("Welcome to multisearcher!");
        Scanner sc = new Scanner(System.in);
        System.out.println("Please, input search text");
        String request = sc.nextLine();
        if (validate(request)) {
            System.out.println("Inputed text: " + request);
            System.out.println("Searching...");
            System.out.println();
            search(request);
        }
    }

    /**
     * Search request in all searchers
     *
     * @param request of user
     */
    public void search(String request) {
        // add all links
        List<Link> allLinks = new ArrayList<Link>();
        int sPos = 0;
        for (AbstractSearcher searcher : this.searchers) {
            List<Link> searcherLinks = searcher.search(request);
            for (Link link : searcherLinks) {
                int[] positions = new int[searchersCount];
                positions[sPos] = link.getPosition();
                link.setPositions(positions);
                allLinks.add(link);
            }
            sPos++;
        }

        // sort links
        results = new TreeMap<Double, Link>();
        int allLinksSize = allLinks.size();
        for (int i = 0; i < allLinksSize; i++) {
            Link link1 = allLinks.get(i);

            for (int j = i + 1; j < allLinksSize; j++) {
                Link link2 = allLinks.get(j);
                if (link1.getUrl().equals(link2.getUrl()) || link1.getTitle().equals(link2.getTitle())) {
                    int[] updateArr = link1.getPositions();
                    for (int m = 0; m < updateArr.length; m++) {
                        updateArr[m] += link2.getPositions()[m];
                    }
                    link1.setPositions(updateArr);
                }
            }
            results.put(rate(link1), link1);
        }

        fillTable(results);


        // temp for debug
        System.out.println("ALL LINKS");
        for (Link link : allLinks)
            System.out.println(link.toString());

    }

    /**
     * Validate requested search text
     *
     * @param request text
     * @return true or false
     */
    public boolean validate(String request) {
        if (null != request && !request.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Rate request results to sort fo table
     *
     * @param link result
     * @return rating of link
     */
    public Double rate(Link link) {
        double rating;
        int[] positions = link.getPositions();
        int size = positions.length;
        double sumPositions = 0;
        int i = 0;
        while (i<size){
            for (AbstractSearcher searcher : this.searchers) {
                if(positions[i]!=0) {
                    sumPositions += positions[i] * searcher.getRate();
                }else {
                    sumPositions+=10;
                }
                i++;
            }
        }
        rating = sumPositions/size;

        return rating;
    }

    /**
     * Fill table results
     *
     * @param results links
     */
    public void fillTable(Map<Double, Link> results) {
        // print table header
        int num = 1;
        System.out.println();
        System.out.printf("%-5s%-100s", "Num", "Link");
        for (AbstractSearcher searcher : searchers) {
            System.out.printf("%-8s", searcher.getName());
        }
        System.out.printf("%-10s%n", "Average");
        System.out.println();

        // fill table
        for (Map.Entry<Double, Link> entry : results.entrySet()) {
            Link link = entry.getValue();
            System.out.printf("%-5s%-100s", num++, link.getUrl());
            int[] searchPos = link.getPositions();
            for (int pos : searchPos) {
                System.out.printf("%-8s", pos);
            }
            System.out.printf("%-5.3f%n", entry.getKey());
        }

    }
}
