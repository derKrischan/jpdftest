package io.github.derkrischan.pdftest;

import org.junit.Test;

/**
 * 
 * Tests for several assertions that can be performed for meta informations
 * stored in PDF.
 * 
 * @author krischan
 *
 */
public class PdfMetaInformationTest {

	@Test
	public void testVerifyAuthor() {
		PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/dummy.pdf"))
		    .author().isNotBlank().matches("Evangelos Vlachogiannis");
	}

	@Test
	public void testVerifyCreator() {
		PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/dummy.pdf"))
		    .creator().matches("Writer");
	}

	@Test
	public void testVerifySubject() {
		PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/dummy.pdf"))
		    .subject().isBlank();
	}

	@Test
	public void testVerifyTitle() {
		PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/dummy.pdf"))
		    .title().isNull();
	}

	@Test
	public void testVerifyProducer() {
		PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/dummy.pdf"))
		    .producer().startsWith("OpenOffice.org");
	}

	@Test
	public void testVerifyCreationDate() {
		PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/dummy.pdf"))
		    .creationDate().isBeforeOrEqualTo("2007-02-24")
		    .isAfter("2007-02-22");
	}

	@Test
	public void testVerifyVersion() {
		PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/dummy.pdf"))
		    .version().isEqualTo(1.4f);
	}
}
