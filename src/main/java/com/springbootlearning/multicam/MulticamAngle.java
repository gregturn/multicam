package com.springbootlearning.multicam;

public enum MulticamAngle {

	ORIGINAL("a_original", "r81", "_original_compound_clip"), //
	CLOSEUP("a_close_up", "r82", "_closeup_compound_clip"), //
	ZOOM_LEFT("a-zoom-left", "r83", "_zoomleft_compound_clip"), //
	ZOOM_RIGHT("a-zoom-right", "r84", "_zoomright_compound_clip");

	private String angleID;
	private String id;
	private String compoundClipSuffix;

	MulticamAngle(String angleID, String id, String compoundClipSuffix) {

		this.angleID = angleID;
		this.id = id;
		this.compoundClipSuffix = compoundClipSuffix;
	}

	public String getAngleID() {
		return angleID;
	}

	public String getId() {
		return id;
	}

	public String getCompoundClipSuffix() {
		return compoundClipSuffix;
	}
}
