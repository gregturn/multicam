package com.springbootlearning.multicam;

import static com.springbootlearning.multicam.MulticamAngle.CLOSEUP;
import static com.springbootlearning.multicam.MulticamAngle.ORIGINAL;
import static com.springbootlearning.multicam.MulticamAngle.ZOOM_LEFT;
import static com.springbootlearning.multicam.MulticamAngle.ZOOM_RIGHT;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.springbootlearning.recut.Library;

@JsonPropertyOrder({ "format", "asset", "media" })
public record Resources(Format format, Asset asset,
		@JacksonXmlProperty(localName = "media") @JacksonXmlElementWrapper(useWrapping = false) List<Media> media) {

	public static Resources transform(com.springbootlearning.recut.Resources resources, Library library) {
		return new Resources(Format.transform(resources.format()), Asset.transform(resources.asset()), //
				List.of( //
						CompoundMedia.transform(resources, library, ORIGINAL.getId(), ORIGINAL.getCompoundClipSuffix()), //
						CompoundMedia.transform(resources, library, CLOSEUP.getId(), CLOSEUP.getCompoundClipSuffix()), //
						CompoundMedia.transform(resources, library, ZOOM_LEFT.getId(), ZOOM_LEFT.getCompoundClipSuffix()), //
						CompoundMedia.transform(resources, library, ZOOM_RIGHT.getId(), ZOOM_RIGHT.getCompoundClipSuffix()), //
						MulticamMedia.transform(resources, library)));
	}
}
