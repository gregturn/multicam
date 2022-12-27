package com.springbootlearning.multicam;

public record Resources(Format format, Asset asset, Media media) {

	public static Resources transform(com.springbootlearning.recut.Resources resources) {
		return new Resources(Format.transform(resources.format()), Asset.transform(resources.asset()), Media.transform(resources));
	}
}
