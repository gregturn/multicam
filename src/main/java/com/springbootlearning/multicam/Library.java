package com.springbootlearning.multicam;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.springbootlearning.recut.Resources;

public record Library(@JacksonXmlProperty(isAttribute = true) String location, Event event) {

	public static Library transform(com.springbootlearning.recut.Library library, Resources resources,
			MulticamStrategy multicamStrategy) {
		return new Library(library.location(), Event.transform(library.event(), resources, multicamStrategy));
	}
}
