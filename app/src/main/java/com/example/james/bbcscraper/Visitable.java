package com.example.james.bbcscraper;

// Allow visit-able objects to accept visitor
public interface Visitable{
	public void accept(Visitor visitor);
}
