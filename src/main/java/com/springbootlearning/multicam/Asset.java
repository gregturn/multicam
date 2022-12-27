package com.springbootlearning.multicam;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public record Asset( //
		@JacksonXmlProperty(isAttribute = true) String id, //
		@JacksonXmlProperty(isAttribute = true) String name, //
		@JacksonXmlProperty(isAttribute = true) String uid, //
		@JacksonXmlProperty(isAttribute = true) String start, //
		@JacksonXmlProperty(isAttribute = true) String duration, //
		@JacksonXmlProperty(isAttribute = true) String hasVideo, //
		@JacksonXmlProperty(isAttribute = true) String format, //
		@JacksonXmlProperty(isAttribute = true) String hasAudio, //
		@JacksonXmlProperty(isAttribute = true) String audioSources, //
		@JacksonXmlProperty(isAttribute = true) String audioChannels, //
		@JacksonXmlProperty(isAttribute = true) String audioRate, //
		@JacksonXmlProperty(localName = "media-rep") MediaRep mediaRep) {

	public static Asset transform(com.springbootlearning.recut.Asset asset) {
		return new Asset(asset.id(), asset.name(), asset.uid(), asset.start(), asset.duration(), asset.hasVideo(),
				asset.format(), asset.hasVideo(), asset.audioSources(), asset.audioChannels(), asset.audioRate(),
				MediaRep.transform(asset));
	}
}
