package com.springbootlearning;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.springbootlearning.multicam.MulticamFcpxml;

class RecutUtils {

	static final private XmlMapper mapper;

	static {
		mapper = new XmlMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
	}

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

	static Path convertToMulticamFile(Path path) {

		try {
			// read file
			String recutContent = String.join("", Files.readAllLines(path));

			// Parse XML
			com.springbootlearning.recut.Fcpxml recutFcpxml = mapper.readValue(recutContent,
					com.springbootlearning.recut.Fcpxml.class);

			System.out.println("We parsed: " + recutFcpxml);

			// Transform
			MulticamFcpxml multicamContent = MulticamFcpxml.transform(recutFcpxml);

			System.out.println("We transformed it into: " + multicamContent);

			StringWriter stringWriter = new StringWriter();
			XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
			xmlOutputFactory.setProperty(XMLOutputFactory.IS_REPAIRING_NAMESPACES, Boolean.TRUE);
			XMLStreamWriter sw = xmlOutputFactory.createXMLStreamWriter(stringWriter);

			mapper.writeValue(sw, multicamContent);

			System.out.println(stringWriter);

			return path;
		} catch (IOException | XMLStreamException e) {
			throw new RuntimeException(e);
		}
	}
}
