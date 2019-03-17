package io.github.derkrischan.pdftest;

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
	public void testFormatAssertion() {
		PdfAssertions.assertFormatPdf1A("src/test/resources/pdf/dummy.pdf").validatePdfA1bCompliance();
	}
}
