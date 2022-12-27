package com.springbootlearning.recut;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Sequence(String duration, String format, String tcStart, String tcFormat, String audioLayout,
		String audioRate, Spine spine) {
}
