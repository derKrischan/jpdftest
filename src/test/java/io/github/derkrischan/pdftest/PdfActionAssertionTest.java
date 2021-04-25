package io.github.derkrischan.pdftest;

import static io.github.derkrischan.pdftest.ActionType.JAVASCRIPT;

import org.junit.Test;

/**
 * Tests for PDF action verification.
 * 
 * @author krischan
 *
 */
public class PdfActionAssertionTest {

	@Test
	public void given_pdfWithoutJavaScript_when_callContainsNoJavaScript_thenPass() {
		PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/dummy.pdf"))
		    .containsNoActionsOfType(JAVASCRIPT);
	}

	@Test(expected = AssertionError.class)
	public void given_pdfWithJavaScript_when_callContainsNoJavaScript_thenThrowAssertionError() {
		PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/JavaScriptPdf.pdf"))
		    .containsNoActionsOfType(JAVASCRIPT);
	}

	@Test
	public void given_pdfWithJavaScript_when_callContainsJavaScript_thenPass() {
		PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/JavaScriptPdf.pdf"))
		    .containsActionsOfType(JAVASCRIPT);
	}

	@Test(expected = AssertionError.class)
	public void given_pdfWithoutJavaScript_when_callContainsJavaScript_thenThrowAssertionError() {
		PdfAssertions.assertThat(ClassLoader.getSystemResourceAsStream("pdf/dummy.pdf"))
		    .containsActionsOfType(JAVASCRIPT);
	}
}
