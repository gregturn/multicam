package com.springbootlearning.multicam;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.springbootlearning.recut.RecutFcpxml;

@JacksonXmlRootElement(localName = "fcpxml")
public record MulticamFcpxml( //
		@JacksonXmlProperty(isAttribute = true) String version, //
		Resources resources, //
		Library library) {

	public static MulticamFcpxml transform(RecutFcpxml recutFcpxml) {
		return new MulticamFcpxml("1.10", Resources.transform(recutFcpxml.resources(), recutFcpxml.library()),
				Library.transform(recutFcpxml.library(), recutFcpxml.resources()));
	}
}
