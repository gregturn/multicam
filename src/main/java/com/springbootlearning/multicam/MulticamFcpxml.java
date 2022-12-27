package com.springbootlearning.multicam;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "fcpxml")
public record MulticamFcpxml( //
		@JacksonXmlProperty(isAttribute = true) String version, //
		Resources resources, //
		Library library) {

	public static MulticamFcpxml transform(com.springbootlearning.recut.Fcpxml fcpxml) {
		return new MulticamFcpxml("1.10", Resources.transform(fcpxml.resources()), Library.transform(fcpxml.library()));
	}
}
