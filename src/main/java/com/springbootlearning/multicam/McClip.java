package com.springbootlearning.multicam;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.springbootlearning.recut.AssetClip;
import com.springbootlearning.recut.Event;

public record McClip( //
		@JacksonXmlProperty(isAttribute = true) String ref, //
		@JacksonXmlProperty(isAttribute = true) String name, //
		@JacksonXmlProperty(isAttribute = true) String offset, //
		@JacksonXmlProperty(isAttribute = true) String start, //
		@JacksonXmlProperty(isAttribute = true) String duration, //
		@JacksonXmlProperty(localName = "mc-source") McSource mcSource) {

	public static McClip transform(Event event) {
		return new McClip("r99", event.name() + "_multicam", null, null, event.project().sequence().duration(),
				McSource.transform(event));
	}

	public static List<McClip> transform(List<AssetClip> assetClips) {
		return assetClips.stream() //
				.map(McClip::transform) //
				.collect(Collectors.toList());
	}

	private static McClip transform(AssetClip assetClip) {
		return new McClip("r99", assetClip.name() + "_multicam", assetClip.offset(), assetClip.start(),
				assetClip.duration(), new McSource("a_original", "all"));
	}
}
