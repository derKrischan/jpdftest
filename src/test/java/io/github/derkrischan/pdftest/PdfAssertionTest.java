package io.github.derkrischan.pdftest;

import org.junit.Test;

import io.github.derkrischan.pdftest.page.Orientation;
import io.github.derkrischan.pdftest.page.PaperSize;

/**
 * Tests for PDF document assertions.
 * 
 * @author krischan
 *
 */
public class PdfAssertionTest {

	@Test
	public void testAssertPfdPageSize() {
		PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/dummy.pdf")).hasPaperSize(PaperSize.A4);
	}

	@Test
	public void testAssertPfdPaperSizeWithCustomTolerance() {
		PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/dummy.pdf")).hasPaperSize(PaperSize.A4.getRectangle(), 0.5f, 0.5f);
	}
	
	@Test
	public void givenPortraitOrientedPdf_shouldMatchPortraitExpectation() {
		PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/dummy.pdf")).hasPageOrientation(Orientation.PORTRAIT);
	}

	@Test(expected=AssertionError.class)
	public void givenLandscapeOrientedPdf_shouldFailPortraitExpectation() {
		PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/dummy_landscape.pdf")).hasPageOrientation(Orientation.PORTRAIT);
	}
	
	@Test
	public void givenLandscapeOrientedPdf_shouldMatchLandscapeExpectation() {
		PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/dummy_landscape.pdf")).hasPageOrientation(Orientation.LANDSCAPE);
	}

	@Test(expected=AssertionError.class)
	public void givenPortraitOrientedPdf_shouldFailLandscapeExpectation() {
		PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/dummy.pdf")).hasPageOrientation(Orientation.LANDSCAPE);
	}
	
}
