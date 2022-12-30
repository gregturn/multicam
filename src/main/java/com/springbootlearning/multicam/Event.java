package com.springbootlearning.multicam;

import static com.springbootlearning.multicam.MulticamAngle.CLOSEUP;
import static com.springbootlearning.multicam.MulticamAngle.ORIGINAL;
import static com.springbootlearning.multicam.MulticamAngle.ZOOM_LEFT;
import static com.springbootlearning.multicam.MulticamAngle.ZOOM_RIGHT;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.springbootlearning.recut.Resources;

public record Event( //
		@JacksonXmlProperty(isAttribute = true) String name, //
		@JacksonXmlProperty(localName = "ref-clip") @JacksonXmlElementWrapper(
				useWrapping = false) List<AssetClip> compoundClips, //
		@JacksonXmlProperty(localName = "mc-clip") McClip mcClipWholeClip, //
		Project project) {

	public static Event transform(com.springbootlearning.recut.Event event, Resources resources,
			MulticamStrategy multicamStrategy) {
		return new Event(event.name() + multicamStrategy.getSuffix(), List.of( //
				new AssetClip(ORIGINAL.getId(), null, event.name() + ORIGINAL.getCompoundClipSuffix(), null, null,
						resources.asset().duration()),
				new AssetClip(CLOSEUP.getId(), null, event.name() + CLOSEUP.getCompoundClipSuffix(), null, null,
						resources.asset().duration()),
				new AssetClip(ZOOM_LEFT.getId(), null, event.name() + ZOOM_LEFT.getCompoundClipSuffix(), null, null,
						resources.asset().duration()),
				new AssetClip(ZOOM_RIGHT.getId(), null, event.name() + ZOOM_RIGHT.getCompoundClipSuffix(), null, null,
						resources.asset().duration())),
				McClip.transform(event), Project.transform(event, multicamStrategy));
	}
}
