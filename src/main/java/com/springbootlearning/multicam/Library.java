package com.springbootlearning.multicam;

public record Library(String location, Event event) {

	public static Library transform(com.springbootlearning.recut.Library library) {
		return new Library(library.location(), Event.transform(library.event()));
	}
}
