package com.example.james.bbcscraper;

import java.util.regex.Pattern;
import java.util.regex.Matcher;;

public class RegexVisitor implements Visitor {
	private String input;
	private String output;
	private String hOutput;
	private String finalHOutput;
	private String sOutput;
	private String finalSOutput;
	private String dOutput;
	private String finalDOutput;
	private boolean hFinds;
	private boolean sFinds;
	private boolean dFinds;

	// collect data about the regular expressions
	// use this data to return a matching String
	public String visit(HeadlineRegex headlineRegex) {
		Pattern headline = headlineRegex.getHeadline();
		Matcher hMatcher = headline.matcher(input);
		// find sub-string that contains pattern
		hFinds = hMatcher.find();		
		if (hFinds == true ) {
			hOutput = hMatcher.toString();
		} else {
			hOutput = "Error";
		}
		// refine output to remove Object info and tags
		headlineRegex.refineHOutput(hOutput);
		finalHOutput = headlineRegex.getFinalHOutput();
		
		// return String
		return finalHOutput;
	}
	
	public String visit(SummaryRegex summaryRegex){
		Pattern summary = summaryRegex.getSummary();
		Matcher sMatcher = summary.matcher(input);
		// find sub-string that contains pattern
		sFinds = sMatcher.find();		
		if (sFinds == true ) {
			sOutput = sMatcher.toString();
		} else {
			sOutput = "Error";
		}
		// refine output to remove Object info and tags
		summaryRegex.refineSOutput(sOutput);
		finalSOutput = summaryRegex.getFinalSOutput();
		return finalSOutput;
	}
	
	public String visit(DateRegex dateRegex){
		Pattern date = dateRegex.getDate();
		Matcher dMatcher = date.matcher(input);
		// find sub-string that contains pattern
		dFinds = dMatcher.find();		
		if (dFinds == true ) {
			dOutput = dMatcher.toString();
		} else {
			dOutput = "Error";
		}
		// refine output to remove object info and tags
		dateRegex.refineDOutput(dOutput);
		finalDOutput = dateRegex.getFinalDOutput();
		return finalDOutput;
	}
	
	// concatenate the outputs
	public String createOutput(String finalHOutput, String finalSOutput, String finalDOutput){
		output = "\n" + finalHOutput + "\n" + finalSOutput + "\n" + finalDOutput; 
		return output;
	}
	
	// getters and setters
	// collect input
	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}
	
	public String getFinalHOutput() {
		return finalHOutput;
	}

	public String getFinalSOutput() {
		return finalSOutput;
	}

	public String getFinalDOutput() {
		return finalDOutput;
	}

	public String getOutput() {
		return output;
	}
	
}
