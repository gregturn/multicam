package com.springbootlearning.multicam;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public record Event( //
					 @JacksonXmlProperty(isAttribute = true) String name, //
					 @JacksonXmlProperty(localName = "mc-clip") McClip mcClipWholeClip, //
					 Project project) {

	public static Event transform(com.springbootlearning.recut.Event event) {
		return new Event(event.name() + "_multi_cam", McClip.transform(event), Project.transform(event));
	}
}
