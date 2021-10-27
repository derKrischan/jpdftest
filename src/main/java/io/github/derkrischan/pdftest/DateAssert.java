package io.github.derkrischan.pdftest;

import java.util.Date;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.assertj.core.api.AbstractDateAssert;

/**
 * Intermediate DateAssert class works as a bridge between AssertJ's {@link AbstractDateAssert} and 
 * {@link FluentPdfAsserter}.
 * The class is package private because the main entry point for PDF verifications should be {@link PdfAssertions}.
 * 
 * @author krischan
 *
 */
public class DateAssert extends AbstractDateAssert<DateAssert> implements FluentPdfAsserter {

	/** the PDF document under test */
	private PDDocument pdfUnderTest;
	
	/**
	 * Package private constructor for {@link DateAssert} to prevent public usage.
	 * 
	 * @param pActualDate the date under test
	 * @param pPdf the PDF document under test
	 */
	DateAssert(final Date pActualDate, final PDDocument pPdf) {
		super(pActualDate, DateAssert.class);
		pdfUnderTest = pPdf;
	}

	@Override
	public PDDocument getPdfUnderTest() {
		return pdfUnderTest;
	}
}
