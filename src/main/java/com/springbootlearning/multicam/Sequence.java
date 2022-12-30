package com.springbootlearning.multicam;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.springbootlearning.recut.Event;
import com.springbootlearning.recut.Library;
import com.springbootlearning.recut.Resources;

public record Sequence( //
		@JacksonXmlProperty(isAttribute = true) String duration, //
		@JacksonXmlProperty(isAttribute = true) String format, //
		@JacksonXmlProperty(isAttribute = true) String tcStart, //
		@JacksonXmlProperty(isAttribute = true) String tcFormat, //
		@JacksonXmlProperty(isAttribute = true) String audioLayout, //
		@JacksonXmlProperty(isAttribute = true) String audioRate, //
		Spine spine) {

	public static Sequence transform(Event event, MulticamStrategy multicamStrategy) {
		return new Sequence(event.project().sequence().duration(), event.project().sequence().format(),
				event.project().sequence().tcStart(), event.project().sequence().tcFormat(),
				event.project().sequence().audioLayout(), event.project().sequence().audioRate(),
				MulticamSpine.transform(event, multicamStrategy));
	}

	public static Sequence transformCompoundClip(Resources resources, Library library) {
		return new Sequence(resources.asset().duration(), resources.format().id(),
				library.event().project().sequence().tcStart(), library.event().project().sequence().tcFormat(), null, null,
				CompoundSpine.transform(resources, library));
	}
}
