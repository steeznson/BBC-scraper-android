package com.example.james.bbcscraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.concurrent.Callable;

public class SelectionHandler implements Callable<String> {
    //vars
    String input;
    String selection;

    @Override
    public String call(){
        // create new object of Document class and scrape HTML to populate it
        Document doc = null;
        try {
            doc = Jsoup.connect(selection).get();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        // send the HTML input to the visitor
        input = doc.toString();

        return input;
    }

    // set
    public void setSelection(String selection) {
        this.selection = selection;
    }

}
