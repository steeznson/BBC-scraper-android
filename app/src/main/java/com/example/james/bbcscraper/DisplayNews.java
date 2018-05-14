package com.example.james.bbcscraper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DisplayNews extends AppCompatActivity {

    // global variable
    String selection;
    String input;

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

    // collect whole news story
    public void handleSelection(String selection){

        SelectionHandler handler = new SelectionHandler();
        handler.setSelection(this.selection);

        // download html on secondary thread
        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<String> html = es.submit(handler); // html is null

        // prepare and print input
        try {
            this.input = html.get();

        }catch (Exception e){
                e.printStackTrace();
        }
        printOutput(input);
    }

    // extract and print output
    public void printOutput(String input){
        // complex objects
        HeadlineRegex headlineRegex = new HeadlineRegex();
        SummaryRegex summaryRegex = new SummaryRegex();
        DateRegex dateRegex = new DateRegex();
        RegexVisitor visitor = new RegexVisitor();

        // primitive objects
        String output;
        String finalHOutput;
        String finalSOutput;
        String finalDOutput;

        visitor.setInput(input);

        // visit the regular expressions
        visitor.visit(headlineRegex);
        finalHOutput = visitor.getFinalHOutput();
        visitor.visit(summaryRegex);
        finalSOutput = visitor.getFinalSOutput();
        visitor.visit(dateRegex);
        finalDOutput = visitor.getFinalDOutput();
        visitor.createOutput(finalHOutput, finalSOutput, finalDOutput);

        // get the output from the visitor
        output = visitor.getOutput();

        // print output
        TextView viewNews = (TextView) findViewById (R.id.newsView);
        viewNews.setText(output);
    }


}
