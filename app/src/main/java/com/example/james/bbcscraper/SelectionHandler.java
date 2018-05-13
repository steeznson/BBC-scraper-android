package com.example.james.bbcscraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class SelectionHandler implements Runnable{
    //vars
    String input;
    String selection;


    @Override
    public void run() {
        try {
            // create new object of Document class and scrape HTML to populate it
            Document doc = null;
            try {
                doc = Jsoup.connect(selection).get();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            // send the HTML input to the visitor
            input = doc.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // getters and setters
    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String getInput() {
        return input;
    }

}
