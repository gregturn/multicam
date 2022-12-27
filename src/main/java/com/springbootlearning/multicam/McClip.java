package com.springbootlearning.multicam;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.springbootlearning.Main;
import com.springbootlearning.MulticamUtils;
import com.springbootlearning.recut.AssetClip;
import com.springbootlearning.recut.Event;

public record McClip( //
		@JacksonXmlProperty(isAttribute = true) String ref, //
		@JacksonXmlProperty(isAttribute = true) String name, //
		@JacksonXmlProperty(isAttribute = true) String offset, //
		@JacksonXmlProperty(isAttribute = true) String start, //
		@JacksonXmlProperty(isAttribute = true) String duration, //
		@JacksonXmlProperty(localName = "mc-source") McSource mcSource) {

	public static McClip transform(Event event) {
		return new McClip(MulticamUtils.MULTICAM_REF, MulticamUtils.assetClipName(event.name()), null, null,
				event.project().sequence().duration(), McSource.transform(event));
	}

	public static List<McClip> transform(List<AssetClip> assetClips) {

		return switch (Main.multicamStrategy) {

			case ONE_CAMERA -> assetClips.stream() //
					.map(McClip::originalClip) //
					.collect(Collectors.toList());
			case TWO_CAMERA_ALTERNATING -> IntStream.range(0, assetClips.size()) //
					.mapToObj(i -> {
						return switch (i % 2) {
							case 0 -> originalClip(assetClips.get(i));
							case 1 -> closeupClip(assetClips.get(i));
							default -> throw new IllegalStateException("Unexpected value: " + i % 2);
						};
					})//
					.collect(Collectors.toList());
			case TWO_CAMERA_STAGGER -> IntStream.range(0, assetClips.size()) //
					.mapToObj(i -> {
						return switch (i % 5) {
							case 0 -> originalClip(assetClips.get(i));
							case 1 -> closeupClip(assetClips.get(i));
							case 2 -> originalClip(assetClips.get(i));
							case 3 -> originalClip(assetClips.get(i));
							case 4 -> closeupClip(assetClips.get(i));
							default -> throw new IllegalStateException("Unexpected value: " + i % 2);
						};
					})//
					.collect(Collectors.toList());
			case THREE_CAMERA_1_2_1_2_3 -> IntStream.range(0, assetClips.size()) //
					.mapToObj(i -> {
						return switch (i % 5) {
							case 0 -> originalClip(assetClips.get(i));
							case 1 -> closeupClip(assetClips.get(i));
							case 2 -> originalClip(assetClips.get(i));
							case 3 -> closeupClip(assetClips.get(i));
							case 4 -> zoomLeftClip(assetClips.get(i));
							default -> throw new IllegalStateException("Unexpected value: " + i % 3);
						};
					})//
					.collect(Collectors.toList());
			case FOUR_CAMERA_1_2_1_2_3_1_2_1_2_4 -> IntStream.range(0, assetClips.size()) //
					.mapToObj(i -> {
						return switch (i % 10) {
							case 0 -> originalClip(assetClips.get(i));
							case 1 -> closeupClip(assetClips.get(i));
							case 2 -> originalClip(assetClips.get(i));
							case 3 -> closeupClip(assetClips.get(i));
							case 4 -> zoomLeftClip(assetClips.get(i));
							case 5 -> originalClip(assetClips.get(i));
							case 6 -> closeupClip(assetClips.get(i));
							case 7 -> originalClip(assetClips.get(i));
							case 8 -> closeupClip(assetClips.get(i));
							case 9 -> zoomRightClip(assetClips.get(i));
							default -> throw new IllegalStateException("Unexpected value: " + i % 3);
						};
					})//
					.collect(Collectors.toList());
		};
	}

	private static McClip originalClip(AssetClip assetClip) {
		return pickClipAngle(assetClip, MulticamAngle.ORIGINAL);
	}

	private static McClip closeupClip(AssetClip assetClip) {
		return pickClipAngle(assetClip, MulticamAngle.CLOSEUP);
	}

	private static McClip zoomLeftClip(AssetClip assetClip) {
		return pickClipAngle(assetClip, MulticamAngle.ZOOM_LEFT);
	}

	private static McClip zoomRightClip(AssetClip assetClip) {
		return pickClipAngle(assetClip, MulticamAngle.ZOOM_RIGHT);
	}

	private static McClip pickClipAngle(AssetClip assetClip, MulticamAngle angle) {
		return new McClip(MulticamUtils.MULTICAM_REF, MulticamUtils.assetClipName(assetClip.name()), assetClip.offset(),
				assetClip.start(), assetClip.duration(), new McSource(angle.getAngleID(), MulticamUtils.MULTICAM_ANGLE_SOURCE));
	}

}
