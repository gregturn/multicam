package com.springbootlearning.multicam;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.springbootlearning.MulticamUtils;
import com.springbootlearning.recut.Library;
import com.springbootlearning.recut.Resources;

public record MulticamMedia( //
		@JacksonXmlProperty(isAttribute = true) String id, //
		@JacksonXmlProperty(isAttribute = true) String name, //
		Multicam multicam) implements Media {

	public static MulticamMedia transform(Resources resources, Library library) {
		return new MulticamMedia(MulticamUtils.MULTICAM_REF, MulticamUtils.mediaClipName(resources.asset().name()),
				Multicam.transform(resources, library));
	}
}
