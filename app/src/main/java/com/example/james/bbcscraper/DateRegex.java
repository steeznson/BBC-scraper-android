package com.example.james.bbcscraper;


import java.util.regex.Pattern;

// concrete element
public class DateRegex implements Visitable {
	
	// initialize variables
	private String finalDOutput;
	private Pattern date = Pattern.compile("data-datetime=\"[A-Za-z0-9 -;:.,!\"'/$]*\">");


	// accept the visitor
	public void accept(Visitor visitor){
		visitor.visit(this);
	}
	
	// refine regular expression
	public String refineDOutput(String dOutput) {
		// requires StringBuilder object as Strings are immutable in Java 
		StringBuilder sbDOutput = new StringBuilder(dOutput);
		sbDOutput.delete(0, 114);
		finalDOutput = sbDOutput.toString();
		finalDOutput = finalDOutput.replace("\">]", "");
		return finalDOutput;
	}
	
	// getters
	public Pattern getDate() {
		return date;
	}
	
	public String getFinalDOutput() {
		return finalDOutput;
	}

}
