package com.springbootlearning.multicam;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public record Format( //
		@JacksonXmlProperty(isAttribute = true) String id, //
		@JacksonXmlProperty(isAttribute = true) String frameDuration, //
		@JacksonXmlProperty(isAttribute = true) String width, //
		@JacksonXmlProperty(isAttribute = true) String height, //
		@JacksonXmlProperty(isAttribute = true) String colorSpace) {

	public static Format transform(com.springbootlearning.recut.Format format) {
		return new Format(format.id(), format.frameDuration(), format.width(), format.height(), format.colorSpace());
	}
}
