package com.springbootlearning.recut;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "asset-clip")
public record AssetClip(String name, String offset, String ref, String start, String duration, String audioRole,
		String format, String tcFormat) {
}
