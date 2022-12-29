package com.springbootlearning.multicam;

import static com.springbootlearning.MulticamUtils.rootName;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.springbootlearning.recut.Library;
import com.springbootlearning.recut.Resources;

public record CompoundSpine(@JacksonXmlProperty(localName = "asset-clip") AssetClip assetClip) implements Spine {

	public static Spine transform(Resources resources, Library library) {
		return new CompoundSpine(new AssetClip(resources.asset().id(), "0s", rootName(resources.asset().name()), "NDF",
				"dialogue", resources.asset().duration()));
	}
}
