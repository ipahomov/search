package com.search.task.services;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Reader thread for user inputs
 * Created by IPahomov on 12.07.2016.
 */
public class ReaderThread extends Thread {
    private static final Logger log = Logger.getLogger(ReaderThread.class);
    private Boolean isRead = true;
    public static BufferedReader reader;
    private String input;

    public ReaderThread() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        start();
        try {
            join();
        } catch (InterruptedException e) {
            log.error("Error interrupted reader thread: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        while (isRead) {
            try {
                input = reader.readLine();
                if (null != input && !input.isEmpty())
                    isRead = false;
                else {
                    System.out.println("Enter correct data!");
                }
            } catch (IOException e) {
                log.error("Error in thread: " + e.getMessage());
            }
        }
    }

    public String getInput() {
        return input;
    }

}
