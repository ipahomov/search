package com.search.task.services;

import com.search.task.link.Link;
import com.search.task.searchers.Searcher;
import org.apache.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Search class for search user request in various searcher
 * and print it with rating.
 * Created by IPahomov on 09.07.2016.
 */
public class SearchService {
    private static final Logger log = Logger.getLogger(SearchService.class);
    private static final String FILE = "link_results.txt";
    private List<Searcher> searchers;
    private Integer searchersCount;
    private String request;
    private Set<Link> results;
    private FileWriter writer = null;

    public SearchService(List<Searcher> searchers) {
        this.searchers = searchers;
        this.searchersCount = searchers.size();
        try {
            writer = new FileWriter(FILE, true);
        } catch (IOException e) {
            log.error("Error open file: " + e.getMessage());
        }
    }

    /**
     * Main menu
     */
    public void menu() {
        System.out.println("Welcome to Multisearcher!");
        Boolean needMenu = true;
        Scanner scanner = new Scanner(System.in);
        while (needMenu) {
            printMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 0:
                    if (null != writer) {
                        try {
                            writer.close();
                        } catch (IOException e) {
                            log.error("Error close file: " + e.getMessage());
                        }
                    }
                    System.exit(0);
                    break;
                case 1:
                    startSearch();
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
        Scanner scanner = new Scanner(System.in);
        this.request = scanner.nextLine();
        log.info("Inputted text: " + request);
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
        return null != request && !request.isEmpty();
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
        for (Searcher searcher : this.searchers) {
            List<Link> searcherLinks = searcher.search(request);
            for (Link link : searcherLinks) {
                int[] positions = new int[searchersCount];
                positions[sPosition] = link.getPosition();
                link.setPositions(positions);
                allLinks.add(link);
            }
            sPosition++;
        }
        log.info("All Links: " + allLinks);

        sort(allLinks);
        printTable(results);
        writeToFile(results);
    }

    /**
     * Sort all link to set with ratings
     *
     * @param allLinks from all searchers
     * @return sorted set
     */
    private Set<Link> sort(List<Link> allLinks) {
        results = new TreeSet<Link>();
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
                    sumPositions += this.searchersCount * (searcher.getRating() - 1);
                    size--;
                }
                i++;
            }
        }
        return sumPositions / size;
    }

    /**
     * Fill and print table results
     *
     * @param results links
     */
    private void printTable(Set<Link> results) {
        // fill table header
        int num = 1;
        System.out.printf("%-5s%-100s", "Num", "Link");
        for (Searcher searcher : searchers) {
            System.out.printf("%-8s", searcher.getName());
        }
        System.out.printf("%-10s%n", "Average");

        // fill table
        for (Link link : results) {
            System.out.printf("%-5s%-100s", num++, link.getUrl());
            for (int pos : link.getPositions()) {
                System.out.printf("%-8s", pos);
            }
            System.out.printf("%-5.3f%n", link.getAverage());
        }

    }

    /**
     * Write results to file
     *
     * @param results of request
     */
    private void writeToFile(Set<Link> results) {
        int count = 0;
        try {
            writer.write("\nNum " + " Average " + " Link for \"" + this.request + "\"\n");
            for (Link link : results) {
                writer.write(++count + "  " +
                        +new BigDecimal(link.getAverage()).setScale(2, RoundingMode.UP).doubleValue());
                writer.write("     " + link.getUrl() + "\n");
                writer.flush();
            }
        } catch (IOException e) {
            log.error("Error write to file: " + e.getMessage());
        }
        System.out.println("Results added to file: " + "link_results.txt");
        log.info("Results added to file: " + "link_results.txt");
    }
}
