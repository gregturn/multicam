package com.springbootlearning.multicam;

public enum MulticamStrategy {

	ONE_CAMERA("single camera, " + "you are required to create all other angles inside Final Cut Pro.",
			"_multicam_one_camera"), //
	TWO_CAMERA_ALTERNATING(
			"two camera, "
					+ "with one angle being the original and the second, a zoomed in closeup, alternating in every other cut",
			"_multicam_two_camera_alternating"), //
	TWO_CAMERA_STAGGER(
			"twp camera, "
					+ "with one angle being the original, and the second, a zoomed in closeup, with a staggering pattern",
			"_multicam_two_camera_stagger"), //
	THREE_CAMERA_1_2_1_2_3(
			"three camera, " + "with first angle being the original, " + "second being zoomed in, "
					+ "third being zoomed in and shifted to the left, "
					+ "with an alternating pattern and the third angle only every other cycle",
			"_multicam_three_camera_1_2_1_2_3"), //
	FOUR_CAMERA_1_2_1_2_3_1_2_1_2_4("four camera, " + "with first angle being the original, " + "second being zoomed in, "
			+ "third being zoomed in and shifted to the left, " + "fourth being zoomed in to the right, "
			+ "with an alternating pattern and the zoomed in angles only happening every other cycle, switching between left and right",
			"_multicam_four_camera_1_2_1_2_3_1_2_1_2_4");

	private String description;
	private String suffix;

	MulticamStrategy(String description, String suffix) {

		this.description = description;
		this.suffix = suffix;
	}

	public String getDescription() {
		return description;
	}

	public String getSuffix() {
		return suffix;
	}
}
