package com.springbootlearning.multicam;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.springbootlearning.recut.Library;
import com.springbootlearning.recut.Resources;

public record CompoundMedia(//
		@JacksonXmlProperty(isAttribute = true) String id, //
		@JacksonXmlProperty(isAttribute = true) String name, //
		Sequence sequence) implements Media {

	public static CompoundMedia transform(Resources resources, Library library, String compoundId, String suffix) {
		return new CompoundMedia(compoundId, library.event().name() + suffix, Sequence.transformCompoundClip(resources, library));
	}
}
