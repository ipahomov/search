package com.search.task.services;

import com.search.task.link.Link;
import com.search.task.searchers.Searcher;
import org.apache.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

/**
 * Table of result links
 * Prints to console and file
 * Created by IPahomov on 12.07.2016.
 */
public class Table {
    private static final Logger log = Logger.getLogger(ReaderThread.class);
    private static final String FILE = "link_results.txt";
    public static FileWriter writer = null;
    private Set<Link> links;

    public Table(Set<Link> links) {
        this.links = links;
        try {
            writer = new FileWriter(FILE, true);
        } catch (IOException e) {
            log.error("Error file: " + e.getMessage());
        }
    }

    public void printTable() {
        // fill table header
        int num = 1;
        System.out.printf("%-5s%-100s", "Num", "Link");
        for (Searcher searcher : MultiSearcher.searchers) {
            System.out.printf("%-8s", searcher.getName());
        }
        System.out.printf("%-10s%n", "Average");

        // fill table
        for (Link link : this.links) {
            System.out.printf("%-5s%-100s", num++, link.getUrl());
            for (int pos : link.getPositions()) {
                System.out.printf("%-8s", pos);
            }
            System.out.printf("%-5.3f%n", link.getAverage());
        }
    }

    public void writeTableToFile() {
        int count = 0;
        try {
            writer.write("\nNum " + " Average " + " Link for \"" + MultiSearcher.request + "\"\n");
            for (Link link : this.links) {
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
