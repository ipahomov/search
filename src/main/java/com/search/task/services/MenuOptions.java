package com.search.task.services;

import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Options for main menu of multisearcher
 * Created by IPahomov on 12.07.2016.
 */
public class MenuOptions {
    private static final Logger log = Logger.getLogger(MenuOptions.class);
    public static String request;
    private ReaderThread readerThread;

    public void printOptions() {
        System.out.println("Options:");
        System.out.println("        0. Exit");
        System.out.println("        1. Search");
        System.out.println("Enter your choice number");
    }

    public String search() {
        System.out.println("Please, input search text");
        readerThread = new ReaderThread();
        request = readerThread.getInput();
        log.info("Inputted text: " + request);
        System.out.println("Inputted text: " + request);
        System.out.println("Searching...\n");
        return request;
    }

    public int getChoice() {
        int choice = Integer.MAX_VALUE;
        readerThread = new ReaderThread();
        String input = readerThread.getInput();
        if (input.matches("[\\d]+")) {
            choice = Integer.parseInt(input);
        }
        return choice;
    }

    public void exit() {
        System.out.println("Goodbye!");
        if (null != ReaderThread.reader) {
            try {
                ReaderThread.reader.close();
            } catch (IOException e) {
                log.error("Error close reader: " + e.getMessage());
            }
        }
        if (null != Table.writer) {
            try {
                Table.writer.close();
            } catch (IOException e) {
                log.error("Error close file: " + e.getMessage());
            }
        }
        System.exit(0);
    }
}
