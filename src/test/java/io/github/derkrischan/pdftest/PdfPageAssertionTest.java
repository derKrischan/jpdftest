package io.github.derkrischan.pdftest;

import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.junit.Test;

import io.github.derkrischan.pdftest.image.MetricRectangle;
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
	public void givenPageWithDinA4Size_whenCheckForPaperSizeDinA4_thenShouldPass() {
		PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/dummy.pdf")).page(1).hasPaperSize(PaperSize.A4);
	}

	@Test(expected=AssertionError.class)
	public void givenPageWithDinA4Size_whenCheckForPaperSizeDinA4Minus2Millimeter_thenShouldFail() {
	  PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/dummy.pdf")).page(1).hasPaperSize(new PDRectangle(PaperSize.A4.getRectangle().getWidth() - 2f, PaperSize.A4.getRectangle().getHeight() + 2f));
	}

	@Test
	public void givenPageWithDinA4Size_whenCheckForPaperSizeDinA4WithTolerance_thenShouldPass() {
	  PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/dummy.pdf")).page(1).hasPaperSize(PaperSize.A4.getRectangle(), 0.5f, 0.5f);
	}
	
	@Test(expected=AssertionError.class)
	public void givenPageWithDinA4Size_whenCheckForPaperSizeDinA3_thenShouldFail() {
	  PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/dummy.pdf")).page(1).hasPaperSize(PaperSize.A3);
	}
	
	@Test(expected=AssertionError.class)
	public void givenPageWithDinA4Size_whenCheckForPaperSizeDinA3WithToleranceOf5Millimeter_thenShouldFail() {
	  PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/dummy.pdf")).page(1).hasPaperSize(PaperSize.A3.getRectangle(), 5f, 5f);
	}
	
	@Test
	public void givenPageWithDinA4Size_whenCheckForPaperSizeDinA3WithToleranceOf350Millimeter_thenShouldPass() {
	  PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/dummy.pdf")).page(1).hasPaperSize(PaperSize.A3.getRectangle(), 350f, 350f);
	}
	
	@Test
	public void givenPortraitOrientedPdf_whenTestForPortraitOrientation_thenShouldPass() {
		PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/dummy.pdf")).page(1).hasPageOrientation(Orientation.PORTRAIT);
	}

	@Test(expected=AssertionError.class)
	public void givenLandscapeOrientedPdf_whenTestForPortraitOrientation_thenShouldFail() {
		PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/dummy_landscape.pdf")).page(1).hasPageOrientation(Orientation.PORTRAIT);
	}
	
	@Test
	public void givenLandscapeOrientedPdf_whenTestForLandscapeOrientation_thenShouldPass() {
		PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/dummy_landscape.pdf")).page(1).hasPageOrientation(Orientation.LANDSCAPE);
	}

	@Test(expected=AssertionError.class)
	public void givenPortraitOrientedPdf_whenTestForLandscapeOrientation_thenShouldFail() {
		PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/dummy.pdf")).page(1).hasPageOrientation(Orientation.LANDSCAPE);
	}
	
	@Test
    public void givenLandscapeOrientedPdf_shouldMatchLandscapeExpectationForEveryPage() {
        PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/2_page_dummy.pdf")).eachPage(p -> p.hasPageOrientation(Orientation.PORTRAIT));
    }
	
	@Test(expected=AssertionError.class)
    public void givenTwoPagePdfWithDifferentTexts_shouldFailMatchTextInEveryPage() {
        PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/2_page_dummy.pdf")).eachPage(p -> p.textInRegion(MetricRectangle.create(0, 0, 164, 310)).contains("Page 1"));
    }
	
	@Test
    public void givenTwoPagePdfWithSameTextPart_shouldMatchTextInEveryPage() {
        PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/2_page_dummy.pdf")).eachPage(p -> p.textInRegion(MetricRectangle.create(0, 0, 164, 310)).contains("2_page_dummy.md"));
    }
	
}
