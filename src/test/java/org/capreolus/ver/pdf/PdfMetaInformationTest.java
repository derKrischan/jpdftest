package org.capreolus.ver.pdf;

import org.capreolus.ver.pdf.PdfAssertions;
import org.junit.Test;

/**
 * 
 * Tests for several assertions that can be performed for meta informations stored in PDF.
 * 
 * @author cwc
 *
 */
public class PdfMetaInformationTest {

	@Test
	public void testVerifyAuthor() {
		PdfAssertions.assertThat("src/test/resources/pdf/dummy.pdf")
		.author().isNotBlank().matches("Evangelos Vlachogiannis");
	}

	@Test
	public void testVerifyCreator() {
		PdfAssertions.assertThat("src/test/resources/pdf/dummy.pdf")
		.creator().matches("Writer");
	}

	@Test
	public void testVerifySubject() {
		PdfAssertions.assertThat("src/test/resources/pdf/dummy.pdf")
		.subject().isBlank();
	}

	@Test
	public void testVerifyTitle() {
		PdfAssertions.assertThat("src/test/resources/pdf/dummy.pdf")
		.title().isNull();
	}

	@Test
	public void testVerifyProducer() {
		PdfAssertions.assertThat("src/test/resources/pdf/dummy.pdf")
		.producer().startsWith("OpenOffice.org");
	}

	@Test
	public void testVerifyCreationDate() {
		PdfAssertions.assertThat("src/test/resources/pdf/dummy.pdf")
		.creationDate().isBeforeOrEqualsTo("2007-02-24")
		.isAfter("2007-02-22");
	}

	@Test
	public void testVerifyVersion() {
		PdfAssertions.assertThat("src/test/resources/pdf/dummy.pdf")
		.version().isEqualTo(1.4f);
	}
}
