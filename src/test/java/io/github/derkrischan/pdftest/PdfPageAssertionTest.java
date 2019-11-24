package io.github.derkrischan.pdftest;

import org.junit.Test;

import io.github.derkrischan.pdftest.page.PaperSize;

/**
 * Tests for PDF text verification.
 * 
 * @author krischan
 *
 */
public class PdfPageAssertionTest {

	@Test
	public void testAssertPfdPageSize() {
		PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/dummy.pdf")).page(1).hasPaperSize(PaperSize.A4);
	}

	@Test
	public void testAssertPfdPaperSizeWithCustomTolerance() {
		PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/dummy.pdf")).page(1).hasPaperSize(PaperSize.A4.getRectangle(), 0.5f, 0.5f);
	}
	
}
