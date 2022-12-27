package com.springbootlearning.recut;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Format(String id, String frameDuration, String width, String height, String colorSpace) {
}
