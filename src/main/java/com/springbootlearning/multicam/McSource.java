package com.springbootlearning.multicam;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.springbootlearning.recut.Event;

public record McSource( //
		@JacksonXmlProperty(isAttribute = true) String angleID, //
		@JacksonXmlProperty(isAttribute = true) String srcEnable) {

	public static McSource transform(Event event) {
		return new McSource("a_original", "all");
	}
}
