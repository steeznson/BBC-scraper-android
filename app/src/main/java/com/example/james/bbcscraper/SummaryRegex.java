package com.example.james.bbcscraper;

import java.util.regex.Pattern;

// concrete element
public class SummaryRegex implements Visitable {
	
	// initialize variables
	private String finalSOutput;
	private Pattern summary = Pattern.compile("<p class=\"buzzard__summary\">[A-Za-z0-9 -;:.,!\\\"'/$]*</p>");


	// accept the visitor
	public void accept(Visitor visitor){
		visitor.visit(this);
	}
	
	// refine regular expression
	public String refineSOutput(String sOutput) {
		// requires StringBuilder object as Strings are immutable in Java
		StringBuilder sbSOutput = new StringBuilder(sOutput);
		sbSOutput.delete(0, 143);
		finalSOutput = sbSOutput.toString();
		finalSOutput = finalSOutput.replace("</p>]", "");
		return finalSOutput;
	}
	
	// getters
	public Pattern getSummary() {
		return summary;
	}
	
	public String getFinalSOutput() {
		return finalSOutput;
	}

}
