package com.springbootlearning.recut;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Fcpxml(String version, Resources resources, Library library) {
}
