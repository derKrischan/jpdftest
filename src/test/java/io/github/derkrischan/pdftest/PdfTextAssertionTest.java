package io.github.derkrischan.pdftest;

import java.awt.geom.Rectangle2D;

import org.junit.Test;

import io.github.derkrischan.pdftest.image.MetricRectangle;

/**
 * Tests for PDF text verification.
 * 
 * @author krischan
 *
 */
public class PdfTextAssertionTest {

	@Test
	public void testAssertPfdTextInRegion() {
		PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/dummy.pdf")).text().chomp().matches("Dummy PDF file")
			.page(1).textInRegion(new Rectangle2D.Double(10.0, 10.0, 600.0, 120.0)).contains("Dummy");
	}

	@Test
	public void testAssertPfdTextInRegionAntrag() {
		PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/pdf_with_image.pdf"))
			.page(1)
			.textInRegion(MetricRectangle.create(14, 197, 164, 6)).chomp().matches("\"I Love PDF\" by Carles Ivanco is licensed under CC BY-NC 4.0. To view a copy of this license, visit:");
	}
	
	@Test
	public void testVerifyPageCount() {
		PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/long_text.pdf"))
			.pageCount().isBetween(5, 7);
	}

}
