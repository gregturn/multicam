package com.springbootlearning;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.commons.io.filefilter.WildcardFileFilter;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.springbootlearning.multicam.MulticamFcpxml;
import com.springbootlearning.multicam.MulticamStrategy;
import com.springbootlearning.picocli.PropertiesFileVersionProvider;
import com.springbootlearning.recut.RecutFcpxml;

@Command(name = "multicam", mixinStandardHelpOptions = true, versionProvider = PropertiesFileVersionProvider.class)
public class Main implements Callable<Integer> {

	@Parameters(index = "0", //
			description = "directory to search") //
	private Path path;

	@Option(names = { "-o", "--out" }, //
			description = { //
					"file to write results to", //
					"If absent, will output results to console." }) //
	private Path outputPath;

	@Option(names = { "-s", "--strategy" }, //
			arity = "0..1", //
			description = { //
					"Strategy for varying the multicam clip angles.", //
					"If absent, it will apply all of them.", //
					"Valid values: ${COMPLETION-CANDIDATES}" }) //
	private MulticamStrategy multicamStrategy;

	@Option(names = { "-v", "--verbose" }, //
			defaultValue = "false", //
			description = "Log extra details.") //
	public static boolean verbose;

	static final private XmlMapper mapper;

	static {
		mapper = new XmlMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
	}

	@Override
	public Integer call() throws Exception {

		WildcardFileFilter filter = new WildcardFileFilter("*.fcpxml");

		try (Stream<Path> stream = Files.walk(path)) {

			List<MulticamStrategy> multicamStrategies = Optional.ofNullable(multicamStrategy) //
					.map(List::of) //
					.orElseGet(() -> List.of(MulticamStrategy.TWO_CAMERA_ALTERNATING));

			stream //
					.filter(path -> filter.accept(path.toFile())) //
					.filter(RecutUtils::isRecutFile) //
					.forEach(path1 -> multicamStrategies
							.forEach(multicamStrategy1 -> convertRecutFcpXmlToMulticamFcpXml(path1, multicamStrategy1, outputPath)));

			return 0;
		} catch (NoSuchFileException e) {
			System.err.println("specified directory does not exist");
		} catch (IOException e) {
			System.err.println("Ooops, an error happened " + e.getMessage());
		}

		return 1;
	}

	public static void main(String[] args) {
		int exitCode = new CommandLine(new Main()).execute(args);
		System.exit(exitCode);
	}

	private static Path convertRecutFcpXmlToMulticamFcpXml(Path path, MulticamStrategy multicamStrategy,
			Path outputPath) {

		try {
			// read file
			String recutContent = String.join("", Files.readAllLines(path));

			// Parse XML
			RecutFcpxml recutFcpxml = mapper.readValue(recutContent, RecutFcpxml.class);

			if (verbose) {
				System.out.println("We parsed: " + recutFcpxml);
			}

			// Transform
			MulticamFcpxml multicamContent = MulticamFcpxml.transform(recutFcpxml, multicamStrategy);

			if (verbose) {
				System.out.println("We transformed it into: " + multicamContent);
			}

			StringWriter stringWriter = new StringWriter();
			XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
			xmlOutputFactory.setProperty(XMLOutputFactory.IS_REPAIRING_NAMESPACES, Boolean.TRUE);
			XMLStreamWriter sw = xmlOutputFactory.createXMLStreamWriter(stringWriter);

			sw.writeStartDocument("UTF-8", "1.0");
			sw.writeCharacters("\n");
			sw.writeComment("\n"
					+ "This FCPXML file is a transformation of Recut (https://springbootlearning.com/recut), a tool used to cut out silence.\n\n"
					+ "All of Recut's cut clips are replaced with multicam clips, cut at the same points, with the "
					+ multicamStrategy + "(" + multicamStrategy.getDescription() + ")" + " strategy applied.\n\n"
					+ "You are free during your edit, to switch up any of the angles selected. "
					+ "You must also apply the actual transformations inside the multicam clip.\n\n"
					+ "-Greg L. Turnquist (https://youtube.com/@SpringBootLearning)\n\n");
			sw.writeCharacters("\n");

			mapper.writeValue(sw, multicamContent);

			if (outputPath == null) {
				if (verbose) {
					System.out.println("Writing multicam FCPXML to console...");
				}
				System.out.println(stringWriter);
			} else {
				String multicamFcpXmlFilename = path.getFileName().toString().split("\\.")[0] + multicamStrategy.getSuffix()
						+ ".fcpxml";
				Path destination = outputPath.resolve(multicamFcpXmlFilename);

				System.out.println("Writing to " + destination);

				Files.write(destination, stringWriter.toString().lines().collect(Collectors.toList()), StandardCharsets.UTF_8,
						StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			}

			return outputPath;

		} catch (IOException | XMLStreamException e) {
			throw new RuntimeException(e);
		}
	}
}
