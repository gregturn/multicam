package com.springbootlearning;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class RecutUtils {

	static boolean isRecutFile(Path path) {
		return //
		path.toFile().getAbsolutePath().endsWith(".fcpxml") //
				&& //
				version18(path);
	}

	/**
	 * Is this a version 1.8 file? That's Recut's current version that it uses.
	 *
	 * @param path
	 * @return
	 */
	private static boolean version18(Path path) {

		try {
			// read file
			String recutContent = String.join("", Files.readAllLines(path));

			if (recutContent.contains("version=\"1.8\"")) {
				return true;
			}
		} catch (IOException ignored) {}

		return false;
	}
}
