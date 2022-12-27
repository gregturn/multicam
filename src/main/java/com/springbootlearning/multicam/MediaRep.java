package com.springbootlearning.multicam;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.springbootlearning.recut.Asset;

public record MediaRep( //
		@JacksonXmlProperty(isAttribute = true) String kind, //
		@JacksonXmlProperty(isAttribute = true) String src) {

	public static MediaRep transform(Asset asset) {
		return new MediaRep("original-media", asset.src());
	}
}
