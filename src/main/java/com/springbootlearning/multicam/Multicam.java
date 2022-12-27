package com.springbootlearning.multicam;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.springbootlearning.recut.Resources;

public record Multicam( //
		@JacksonXmlProperty(isAttribute = true) String format, //
		@JacksonXmlProperty(isAttribute = true) String tcStart, //
		@JacksonXmlProperty(isAttribute = true) String tcFormat, //
		@JacksonXmlProperty(localName = "mc-angle") @JacksonXmlElementWrapper(useWrapping = false) List<Angle> angles) {

	public static Multicam transform(Resources resources) {
		return new Multicam(resources.format().id(), "0s", "NDF", //
				List.of( //
						new Angle("Original", "a_original",
								new AssetClip(resources.asset().id(), "0s", name(resources.asset().name()), "NDF", "dialogue")),
						new Angle("Zoom/Left", "a-zoom-left",
								new AssetClip(resources.asset().id(), "0s", name(resources.asset().name()), "NDF", "dialogue")),
						new Angle("Zoom/Right", "a-zoom-right",
								new AssetClip(resources.asset().id(), "0s", name(resources.asset().name()), "NDF", "dialogue")),
						new Angle("Close Up", "a_close_up",
								new AssetClip(resources.asset().id(), "0s", name(resources.asset().name()), "NDF", "dialogue"))));
	}

	static private String name(String name) {
		return name.split("\\.")[0];
	}
}
