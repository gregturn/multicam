package com.springbootlearning.multicam;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.springbootlearning.MulticamUtils;
import com.springbootlearning.recut.Event;

public record Project( //
		@JacksonXmlProperty(isAttribute = true) String name, //
		Sequence sequence) {

	public static Project transform(Event event) {
		return new Project(MulticamUtils.projectName(event.name()), Sequence.transform(event));
	}
}
