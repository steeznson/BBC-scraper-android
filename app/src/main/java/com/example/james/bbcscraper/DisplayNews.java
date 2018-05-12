package com.example.james.bbcscraper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class DisplayNews extends AppCompatActivity {

    // variables
    String selection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_news);
    }

    // methods for buttons
    public void selectUK(View view) {
        selection = "http://www.bbc.co.uk/news/uk/";
        handleSelection(selection);
    }

    public void selectPolitics (View view){
        selection = "http://bbc.co.uk/news/politics/";
        handleSelection(selection);
    }

    public void selectBusiness (View view){
        selection = "http://bbc.co.uk/news/business/";
        handleSelection(selection);
    }

    public void selectHealth (View view){
        selection = "http://bbc.co.uk/news/health/";
        handleSelection(selection);
    }

    public void selectScience (View view){
        selection = "http://bbc.co.uk/news/science_and_environment/";
        handleSelection(selection);
    }

    public void selectTech(View view) {
        selection = "http://bbc.co.uk/news/technology/";
        handleSelection(selection);
    }

    public void handleSelection(String selection) {

        // initialize complex objects
        HeadlineRegex headlineRegex = new HeadlineRegex();
        SummaryRegex summaryRegex = new SummaryRegex();
        DateRegex dateRegex = new DateRegex();
        RegexVisitor visitor = new RegexVisitor();
        DisplayNews display = new DisplayNews();
        TextView viewNews = (TextView) findViewById (R.id.newsView);


        // initialize primitive objects
        String finalHOutput;
        String finalSOutput;
        String finalDOutput;
        String output;

        // create new object of Document class and scrape HTML to populate it
        Document doc = null;
        try {
            doc = Jsoup.connect(selection).get();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        // send the HTML input to the visitor
        String input = doc.toString();
        visitor.setInput(input);

        // visit the regular expression
        visitor.visit(headlineRegex);
        finalHOutput = visitor.getFinalHOutput();
        visitor.visit(summaryRegex);
        finalSOutput = visitor.getFinalSOutput();
        visitor.visit(dateRegex);
        finalDOutput = visitor.getFinalDOutput();
        visitor.createOutput(finalHOutput, finalSOutput, finalDOutput);

        // get the output from the visitor
        output = visitor.getOutput();

        // print it in the text output
        viewNews.setText(output);
    }



}