package com.springbootlearning.recut;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Spine(@JacksonXmlProperty(localName = "asset-clip") @JacksonXmlElementWrapper(
		useWrapping = false) List<AssetClip> assetClips) {
}
