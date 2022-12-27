package com.springbootlearning.multicam;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.springbootlearning.MulticamUtils;
import com.springbootlearning.recut.Resources;

public record Media( //
		@JacksonXmlProperty(isAttribute = true) String id, //
		@JacksonXmlProperty(isAttribute = true) String name, //
		Multicam multicam) {

	public static Media transform(Resources resources) {
		return new Media(MulticamUtils.MULTICAM_REF, MulticamUtils.mediaClipName(resources.asset().name()),
				Multicam.transform(resources));
	}
}
