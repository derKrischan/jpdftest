package org.capreolus.ver.pdf;

import org.capreolus.ver.pdf.PdfAssertions;
import org.capreolus.ver.pdf.PdfFormatAssert;
import org.junit.Test;

/**
 * Unit tests for {@link PdfFormatAssert}
 * @author cwc
 *
 */
public class PdfFormatAssertionTest {

	@Test(expected=AssertionError.class)
	public void testFormatAssertion() {
		PdfAssertions.assertFormatPdf1A("src/test/resources/pdf/dummy.pdf").validatePdfA1bCompliance();
	}
}
