package com.springbootlearning.multicam;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.springbootlearning.recut.Event;

public record Sequence( //
		@JacksonXmlProperty(isAttribute = true) String duration, //
		@JacksonXmlProperty(isAttribute = true) String format, //
		@JacksonXmlProperty(isAttribute = true) String tcStart, //
		@JacksonXmlProperty(isAttribute = true) String tcFormat, //
		@JacksonXmlProperty(isAttribute = true) String audioLayout, //
		@JacksonXmlProperty(isAttribute = true) String audioRate, //
		Spine spine) {

	public static Sequence transform(Event event) {
		return new Sequence(event.project().sequence().duration(), event.project().sequence().format(),
				event.project().sequence().tcStart(), event.project().sequence().tcFormat(),
				event.project().sequence().audioLayout(), event.project().sequence().audioRate(), Spine.transform(event));
	}
}
