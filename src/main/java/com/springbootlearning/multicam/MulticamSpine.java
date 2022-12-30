package com.springbootlearning.multicam;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.springbootlearning.recut.Event;

public record MulticamSpine( //
		@JacksonXmlProperty(localName = "mc-clip") @JacksonXmlElementWrapper(useWrapping = false) List<McClip> mcClips)
		implements
			Spine {

	public static MulticamSpine transform(Event event, MulticamStrategy multicamStrategy) {
		return new MulticamSpine(McClip.transform(event.project().sequence().spine().assetClips(), multicamStrategy));
	}
}
