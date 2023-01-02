package com.springbootlearning.multicam;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.springbootlearning.MulticamUtils.rootName;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.springbootlearning.recut.Library;
import com.springbootlearning.recut.Resources;

public record CompoundSpine(@JacksonXmlProperty(localName = "asset-clip") @JsonInclude(NON_EMPTY) AssetClip assetClip,
		@JacksonXmlProperty(localName = "ref-clip") @JsonInclude(NON_EMPTY) AssetClip refClip) implements Spine {

	public static Spine transform(String compoundId, Resources resources, Library library) {

		if (compoundId.equals(MulticamAngle.ORIGINAL.getId())) {
			return new CompoundSpine(new AssetClip(resources.asset().id(), "0s", rootName(resources.asset().name()), "NDF",
					"dialogue", resources.asset().duration()), null);
		}

		return new CompoundSpine(null, new AssetClip(MulticamAngle.ORIGINAL.getId(), "0s",
				rootName(resources.asset().name()), null, null, resources.asset().duration()));
	}
}
