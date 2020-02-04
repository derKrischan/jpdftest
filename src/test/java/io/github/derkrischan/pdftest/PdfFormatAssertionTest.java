package io.github.derkrischan.pdftest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import io.github.derkrischan.pdftest.PdfAssertions;
import io.github.derkrischan.pdftest.PdfFormatAssert;

/**
 * Unit tests for {@link PdfFormatAssert}
 * @author krischan
 *
 */
public class PdfFormatAssertionTest {

	@Test(expected=AssertionError.class)
	public void givenNonA1Document_shouldReturnAssertionError() {
		PdfAssertions.assertFormatPdf1A("src/test/resources/pdf/dummy.pdf").validatePdfA1bCompliance();
	}

	@Test
	public void givenPdfA1bDocumentAsFileName_shouldPass() {
		PdfAssertions.assertFormatPdf1A("src/test/resources/pdf/PdfA-1b.pdf").validatePdfA1bCompliance();
	}
	
	@Test
	public void givenPdfA1bDocumentAsFile_shouldPass() {
		PdfAssertions.assertFormatPdf1A(new File("src/test/resources/pdf/PdfA-1b.pdf")).validatePdfA1bCompliance();
	}
	
	@Test
	public void givenPdfA1bDocumentAsPath_shouldPass() {
		PdfAssertions.assertFormatPdf1A(Paths.get("src/test/resources/pdf/PdfA-1b.pdf")).validatePdfA1bCompliance();
	}
	
	@Test
	public void givenPdfA1bDocumentAsInputStream_shouldPass() {
		PdfAssertions.assertFormatPdf1A(ClassLoader.getSystemResourceAsStream("pdf/PdfA-1b.pdf")).validatePdfA1bCompliance();
	}
	
	@Test
	public void givenPdfA1bDocumentAsByteArray_shouldPass() throws IOException {
		PdfAssertions.assertFormatPdf1A(Files.readAllBytes(Paths.get("src/test/resources/pdf/PdfA-1b.pdf"))).validatePdfA1bCompliance();
	}
	
	@Test(expected=AssertionError.class)
	public void givenNotExistingFilename_shouldReturnAssertionError() {
		PdfAssertions.assertFormatPdf1A("src/test/resources/pdf/non_existing_pdf.pdf").validatePdfA1bCompliance();
	}
	
	@Test(expected=AssertionError.class)
	public void givenPdfA1bDocument_checkForNotA1bCompliance_shouldReturnAssertionError() {
		PdfAssertions.assertFormatPdf1A(ClassLoader.getSystemResourceAsStream("pdf/PdfA-1b.pdf")).validateNoPdfA1bCompliance();
	}

	public void givenNonPdfA1bDocument_checkForNotA1bCompliance_shouldPass() {
		PdfAssertions.assertFormatPdf1A("src/test/resources/pdf/dummy.pdf").validateNoPdfA1bCompliance();
	}
}
