package com.springbootlearning;

import static org.assertj.core.api.Assertions.*;

import picocli.CommandLine;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class MainTests {
	private Main app = new Main();
	private CommandLine cmd = new CommandLine(app);
	private StringWriter sw = new StringWriter();

	@BeforeEach
	void init() {
		cmd.setOut(new PrintWriter(sw));
	}

	@Test
	void printsVersion() {

		int exitCode = cmd.execute("-V");
		assertThat(exitCode).isEqualTo(0);
		assertThat(sw.toString()) //
				.isNotBlank() //
				.isEqualToIgnoringWhitespace("1.0-SNAPSHOT");
	}

	@Test
	void convertsRecutFcpXmlToMulticamFcpXml() {

		int exitCode = cmd.execute("src/test/resources", "--verbose");
		assertThat(exitCode).isEqualTo(0);
		String output = sw.toString();
		System.out.println(output);
	}

	@Disabled
	@Test
	void writesMulticamFcpXmlToFile() {

		int exitCode = cmd.execute("src/test/resources", "-o .");
		assertThat(exitCode).isEqualTo(0);
		String output = sw.toString();
		System.out.println(output);
	}
}
