package com.springbootlearning.recut;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Event(String name, String uid, Project project) {
}
