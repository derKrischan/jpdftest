package org.capreolus.ver.pdf;

import java.io.File;

import org.capreolus.ver.pdf.PdfAssertions;
import org.capreolus.ver.pdf.image.MetricRectangle;
import org.junit.Test;

/**
 * Unit tests for embedded image verification in PDFs. 
 * 
 * @author cwc
 *
 */
public class PdfImageAssertionTest {

	@Test
	public void testAssertImageAtPage() throws Exception {
		File pdf = new File("src/test/resources/pdf/pdf_with_image.pdf");
		PdfAssertions.assertThat(pdf).page(1).containsImage("src/test/resources/img/i_love_pdf.png");
	}

	@Test
	public void testAssertImageAtPageInRegion() throws Exception {
		File pdf = new File("src/test/resources/pdf/pdf_with_image.pdf");
		PdfAssertions.assertThat(pdf).page(1).containsImageInRegion("src/test/resources/img/i_love_pdf.png", MetricRectangle.create(0, 110, 400, 200));
	}
}
