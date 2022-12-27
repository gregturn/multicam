package com.springbootlearning.multicam;

public enum MulticamAngle {

	ORIGINAL("a_original"), //
	CLOSEUP("a_close_up"), //
	ZOOM_LEFT("a-zoom-left"), //
	ZOOM_RIGHT("a-zoom-right");

	private String angleID;

	MulticamAngle(String angleID) {
		this.angleID = angleID;
	}

	public String getAngleID() {
		return angleID;
	}
}
