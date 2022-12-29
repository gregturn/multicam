package com.springbootlearning.multicam;

import static com.springbootlearning.MulticamUtils.rootName;

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
						new Angle("Original", MulticamAngle.ORIGINAL.getAngleID(),
								new AssetClip(resources.asset().id(), "0s", rootName(resources.asset().name()), "NDF", "dialogue")),
						new Angle("Close Up", MulticamAngle.CLOSEUP.getAngleID(),
								new AssetClip(resources.asset().id(), "0s", rootName(resources.asset().name()), "NDF", "dialogue")),
						new Angle("Zoom/Left", MulticamAngle.ZOOM_LEFT.getAngleID(),
								new AssetClip(resources.asset().id(), "0s", rootName(resources.asset().name()), "NDF", "dialogue")),
						new Angle("Zoom/Right", MulticamAngle.ZOOM_RIGHT.getAngleID(),
								new AssetClip(resources.asset().id(), "0s", rootName(resources.asset().name()), "NDF", "dialogue"))));
	}
}
