package com.springbootlearning.multicam;

import static com.springbootlearning.MulticamUtils.rootName;
import static com.springbootlearning.multicam.MulticamAngle.CLOSEUP;
import static com.springbootlearning.multicam.MulticamAngle.ORIGINAL;
import static com.springbootlearning.multicam.MulticamAngle.ZOOM_LEFT;
import static com.springbootlearning.multicam.MulticamAngle.ZOOM_RIGHT;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.springbootlearning.recut.Library;
import com.springbootlearning.recut.Resources;

public record Multicam( //
		@JacksonXmlProperty(isAttribute = true) String format, //
		@JacksonXmlProperty(isAttribute = true) String tcStart, //
		@JacksonXmlProperty(isAttribute = true) String tcFormat, //
		@JacksonXmlProperty(localName = "mc-angle") @JacksonXmlElementWrapper(useWrapping = false) List<Angle> angles) {

	public static Multicam transform(Resources resources, Library library) {
		return new Multicam(resources.format().id(), "0s", null, //
				List.of( //
						new Angle("Original", ORIGINAL.getAngleID(),
								new AssetClip(ORIGINAL.getId(), "0s",
										rootName(resources.asset().name()) + ORIGINAL.getCompoundClipSuffix(), null, null,
										resources.asset().duration())),
						new Angle("Close Up", CLOSEUP.getAngleID(),
								new AssetClip(CLOSEUP.getId(), "0s",
										rootName(resources.asset().name()) + CLOSEUP.getCompoundClipSuffix(), null, null,
										resources.asset().duration())),
						new Angle("Zoom/Left", ZOOM_LEFT.getAngleID(),
								new AssetClip(ZOOM_LEFT.getId(), "0s",
										rootName(resources.asset().name()) + ZOOM_LEFT.getCompoundClipSuffix(), null, null,
										resources.asset().duration())),
						new Angle("Zoom/Right", ZOOM_RIGHT.getAngleID(),
								new AssetClip(ZOOM_RIGHT.getId(), "0s",
										rootName(resources.asset().name()) + ZOOM_RIGHT.getCompoundClipSuffix(), null, null,
										resources.asset().duration()))));
	}
}
