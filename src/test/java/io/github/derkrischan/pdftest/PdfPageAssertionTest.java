package io.github.derkrischan.pdftest;

import org.junit.Test;

import io.github.derkrischan.pdftest.page.Orientation;
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
	
	@Test
	public void givenPortraitOrientedPdf_shouldMatchPortraitExpectation() {
		PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/dummy.pdf")).page(1).hasPageOrientation(Orientation.PORTRAIT);
	}

	@Test(expected=AssertionError.class)
	public void givenLandscapeOrientedPdf_shouldFailPortraitExpectation() {
		PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/dummy_landscape.pdf")).page(1).hasPageOrientation(Orientation.PORTRAIT);
	}
	
	@Test
	public void givenLandscapeOrientedPdf_shouldMatchLandscapeExpectation() {
		PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/dummy_landscape.pdf")).page(1).hasPageOrientation(Orientation.LANDSCAPE);
	}

	@Test(expected=AssertionError.class)
	public void givenPortraitOrientedPdf_shouldFailLandscapeExpectation() {
		PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/dummy.pdf")).page(1).hasPageOrientation(Orientation.LANDSCAPE);
	}
	
	@Test
    public void givenLandscapeOrientedPdf_shouldMatchLandscapeExpectationForEveryPage() {
        PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/2_page_dummy.pdf")).eachPage(p -> p.hasPageOrientation(Orientation.PORTRAIT));
    }
	
}
