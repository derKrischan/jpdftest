package io.github.derkrischan.pdftest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.Test;

/**
 * Tests for PDF text verification.
 * 
 * @author krischan
 *
 */
public class PdfAssertionParameterTest {

	@Test
	public void givenPdfAsFile_shouldCreatePdfAssertions() {
		PdfAssertions.assertThat(new File("src/test/resources/pdf/dummy.pdf")).pageCount().isEqualTo(1);
	}

	@Test
	public void givenPdfAsFilename_shouldCreatePdfAssertions() {
		PdfAssertions.assertThat("src/test/resources/pdf/dummy.pdf").pageCount().isEqualTo(1);
	}

	@Test
	public void givenPdfAsInputStream_shouldCreatePdfAssertions() {
		PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/dummy.pdf")).pageCount().isEqualTo(1);
	}
	
	@Test
	public void givenPdfAsByteArray_shouldCreatePdfAssertions() throws IOException {
		PdfAssertions.assertThat(Files.readAllBytes(new File("src/test/resources/pdf/dummy.pdf").toPath())).pageCount().isEqualTo(1);
	}
}
