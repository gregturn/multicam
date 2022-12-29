package com.springbootlearning.multicam;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public record Angle( //
		@JacksonXmlProperty(isAttribute = true) String name, //
		@JacksonXmlProperty(isAttribute = true) String angleID, //
		@JacksonXmlProperty(localName = "ref-clip") AssetClip assetClip) {
}
