package io.github.derkrischan.pdftest;

import org.junit.Test;

import io.github.derkrischan.pdftest.image.MetricRectangle;

/**
 * Unit tests for embedded image verification in PDFs. 
 * 
 * @author krischan
 *
 */
public class PdfImageAssertionTest {

	@Test
	public void testAssertImageAtPage() throws Exception {
		PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/pdf_with_image.pdf"))
			.page(1).containsImage("src/test/resources/img/i_love_pdf.png");
	}

	@Test
	public void testAssertImageAtPageInRegion() throws Exception {
		PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/pdf_with_image.pdf"))
			.page(1).containsImageInRegion("src/test/resources/img/i_love_pdf.png", MetricRectangle.create(0, 110, 400, 200));
	}
}
