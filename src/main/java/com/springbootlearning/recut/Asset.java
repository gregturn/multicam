package com.springbootlearning.recut;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Asset(String id, String name, String uid, String src, String start, String duration, String hasVideo,
		String format, String audioSources, String audioChannels, String audioRate) {
}
