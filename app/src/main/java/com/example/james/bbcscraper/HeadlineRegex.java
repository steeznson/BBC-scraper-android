package com.example.james.bbcscraper;


import java.util.regex.Pattern;


//concrete element
public class HeadlineRegex implements Visitable{
	
	// initialize variables
	private String finalHOutput;
	private Pattern headline = Pattern.compile("<span class=\"title-link__title-text\">[A-Za-z0-9 -;:.,!\"'/$]*</span>");
	
	// accept the visitor
	public void accept(Visitor visitor){
		visitor.visit(this);
	}
	
	// refine regular expression
	public String refineHOutput(String hOutput) {
		// requires StringBuilder object as Strings are immutable in Java
		StringBuilder sbHOutput = new StringBuilder(hOutput);
		sbHOutput.delete(0, 163);
		finalHOutput = sbHOutput.toString();
		finalHOutput = finalHOutput.replace("</span>]", "");
		return finalHOutput;
	}
		
	// getters
	public Pattern getHeadline() {
		return headline;
	}
	
	public String getFinalHOutput() {
		return finalHOutput;
	}

	
} 
