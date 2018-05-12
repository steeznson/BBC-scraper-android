package com.example.james.bbcscraper;


// plan out the visitor's route
public interface Visitor{
	// parse headline
	public String visit(HeadlineRegex headlineRegex);
	// parse summary
	public String visit(SummaryRegex summaryRegex);
	// parse date
	public String visit(DateRegex dateRegex);
}