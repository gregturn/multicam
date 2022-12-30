package com.springbootlearning;

import com.springbootlearning.multicam.MulticamStrategy;

public class MulticamUtils {

	public static final String MULTICAM_REF = "r99";
	public static final String MULTICAM_ANGLE_SOURCE = "all";
	public static final String MULTICAM_SUFFIX = "_multicam";

	/**
	 * Transform the name of an {@literal asset-clip} into a multicam clip name.
	 *
	 * @param assetClip
	 * @return
	 */
	public static String assetClipName(String assetClip) {
		return assetClip + MULTICAM_SUFFIX;
	}

	/**
	 * Transform the name of an {@literal asset} name into a media name.
	 *
	 * @param name
	 * @return
	 */
	public static String mediaClipName(String name) {
		return rootName(name) + MULTICAM_SUFFIX;
	}

	/**
	 * Transform the name of a {@literal asset} into the name of a multicam mc-angle's {@literal asset-clip}.
	 *
	 * @param name
	 * @return
	 */
	public static String rootName(String name) {
		return name.split("\\.")[0];
	}

	/**
	 * Transform an {@literal event} name into a multicam project name.
	 * 
	 * @param name
	 * @return
	 */
	public static String projectName(String name, MulticamStrategy multicamStrategy) {
		return rootName(name) + multicamStrategy.getSuffix();
	}
}
