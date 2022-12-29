package com.springbootlearning.multicam;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

public record AssetClip( //
		@JacksonXmlProperty(isAttribute = true) String ref, //
		@JacksonXmlProperty(isAttribute = true) String offset, //
		@JacksonXmlProperty(isAttribute = true) String name, //
		@JacksonXmlProperty(isAttribute = true) String tcFormat, //
		@JacksonXmlProperty(isAttribute = true) String audioRole, //
		@JacksonXmlProperty(isAttribute = true) String duration) {
}
