package com.search.task.services;

import com.search.task.searchers.AbstractSearcher;

import java.util.*;

/**
 * Created by IPahomov on 09.07.2016.
 */
public class SearchService {
    private List<AbstractSearcher> searchers;
    private Integer searchersCount;
    private Set<Link> results;
    public static Boolean needMenu = true;
    Scanner scanner;

    public SearchService(List<AbstractSearcher> searchers) {
        this.searchers = searchers;
        this.searchersCount = searchers.size();
        scanner = new Scanner(System.in);
    }

    /**
     * Main menu
     */
    public void menu() {
        System.out.println("Welcome to multisearcher!");
        while (needMenu) {
            printMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    startSearch();
                    break;
                default:
                    needMenu = true;
                    break;
            }
            needMenu = true;
        }
    }

    /**
     * Menu options
     */
    private void printMenu() {
        System.out.println(" Options:");
        System.out.println("        0. Exit");
        System.out.println("        1. Search");
    }

    /**
     * Start multisearcher
     */
    private void startSearch() {
        System.out.println("Please, input search text");
        Scanner sc = new Scanner(System.in);
        String request = sc.nextLine();
        if (validate(request)) {
            System.out.println("Inputted text: " + request);
            System.out.println("Searching...\n");
            search(request);
        } else {
            System.out.println("Enter correct request, please!");
        }
    }

    /**
     * Validate requested search text
     *
     * @param request text
     * @return true or false
     */
    private boolean validate(String request) {
        if (null != request && !request.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Search request in all searchers
     *
     * @param request of user
     */
    private void search(String request) {
        // add all links
        List<Link> allLinks = new ArrayList<Link>();
        int sPosition = 0;
        for (AbstractSearcher searcher : this.searchers) {
            List<Link> searcherLinks = searcher.search(request);
            for (Link link : searcherLinks) {
                int[] positions = new int[searchersCount];
                positions[sPosition] = link.getPosition();
                link.setPositions(positions);
                allLinks.add(link);
            }
            sPosition++;
        }

        sort(allLinks);
        printTable(results);

        // temp for debug
        System.out.println("ALL LINKS");
        for (Link link : allLinks)
            System.out.println(link.toString());

    }

    /**
     * Sort all link to set with ratings
     *
     * @param allLinks from all searchers
     * @return sorted set
     */
    private Set<Link> sort(List<Link> allLinks) {
        results = new TreeSet<Link>(new LinkComp());
        int allLinksSize = allLinks.size();
        for (int i = 0; i < allLinksSize; i++) {
            Link link1 = allLinks.get(i);

            for (int j = i + 1; j < allLinksSize; j++) {
                Link link2 = allLinks.get(j);
                if (link1.getUrl().equals(link2.getUrl())) {
                    int[] updateArr = link1.getPositions();
                    for (int m = 0; m < updateArr.length; m++) {
                        updateArr[m] += link2.getPositions()[m];
                    }
                    link1.setPositions(updateArr);
                }
            }
            link1.setAverage(rate(link1));
            results.add(link1);
        }

        return results;
    }

    /**
     * Rate request results to sort fo table
     *
     * @param link result
     * @return rating of link
     */
    private Double rate(Link link) {
        double rating;
        int[] positions = link.getPositions();
        int size = positions.length;
        double sumPositions = 0;
        int i = 0;
        while (i < size) {
            for (AbstractSearcher searcher : this.searchers) {
                if (positions[i] != 0) {
                    sumPositions += positions[i] * searcher.getRate();
                } else {
                    sumPositions += this.searchersCount * (searcher.getRate() - 1);
                    size--;
                }
                i++;
            }
        }
        rating = sumPositions / size;

        return rating;
    }

    /**
     * Fill and print table results
     *
     * @param results links
     */
    private void printTable(Set<Link> results) {
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
        for (Link link : results) {
            System.out.printf("%-5s%-100s", num++, link.getUrl());
            int[] searchPos = link.getPositions();
            for (int pos : searchPos) {
                System.out.printf("%-8s", pos);
            }
            System.out.printf("%-5.3f%n", link.getAverage());
        }

    }
}
