package io.github.derkrischan.pdftest;

import java.util.Date;
import java.util.Objects;

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
	public PdfAssert document() {
		return new PdfAssert(getPdfUnderTest());
	}

	@Override
	public PdfPageAssert page(int pPageNumber) {
		return FluentPdfAssertionHelper.getPageAsserterForDocument(getPdfUnderTest(), pPageNumber);
	}

	@Override
	public PDDocument getPdfUnderTest() {
		return pdfUnderTest;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(pdfUnderTest);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof DateAssert)) {
			return false;
		}
		DateAssert other = (DateAssert) obj;
		return Objects.equals(pdfUnderTest, other.pdfUnderTest);
	}
}
