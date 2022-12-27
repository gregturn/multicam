package com.springbootlearning.multicam;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.springbootlearning.recut.Event;

public record Spine( //
		@JacksonXmlProperty(localName = "mc-clip") @JacksonXmlElementWrapper(useWrapping = false) List<McClip> mcClips) {

	public static Spine transform(Event event) {
		return new Spine(McClip.transform(event.project().sequence().spine().assetClips()));
	}
}
