package com.springbootlearning.multicam;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.springbootlearning.recut.Resources;

public record Media( //
		@JacksonXmlProperty(isAttribute = true) String id, //
		@JacksonXmlProperty(isAttribute = true) String name, //
		Multicam multicam) {

	public static Media transform(Resources resources) {
		return new Media("r99", resources.asset().name().split("\\.")[0] + "_multicam", Multicam.transform(resources));
	}
}
