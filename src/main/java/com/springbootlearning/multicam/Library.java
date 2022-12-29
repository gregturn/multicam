package com.springbootlearning.multicam;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public record Library(@JacksonXmlProperty(isAttribute = true) String location, Event event) {

	public static Library transform(com.springbootlearning.recut.Library library) {
		return new Library(library.location(), Event.transform(library.event()));
	}
}
