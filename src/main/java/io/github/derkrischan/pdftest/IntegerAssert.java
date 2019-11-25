package io.github.derkrischan.pdftest;


import java.util.Objects;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.assertj.core.api.AbstractIntegerAssert;

/**
 * Intermediate IntegerAssert class works as a bridge between AssertJ's {@link AbstractIntegerAssert} and 
 * {@link FluentPdfAsserter}.
 * The class is package private because the main entry point for PDF verifications should be {@link PdfAssertions}.
 * 
 * @author krischan
 *
 */
public class IntegerAssert extends AbstractIntegerAssert<IntegerAssert> implements FluentPdfAsserter {

	/** the PDF document under test */
	private PDDocument pdfUnderTest;
	
	/**
	 * Package private constructor for {@link IntegerAssert} to prevent public usage.
	 * 
	 * @param actualInt the integer value under test
	 * @param pPdf the PDF document under test
	 */
	IntegerAssert(final Integer actualInt, final PDDocument pPdf) {
		super(actualInt, IntegerAssert.class);
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

	@Deprecated()
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof IntegerAssert)) {
			return false;
		}
		IntegerAssert other = (IntegerAssert) obj;
		return Objects.equals(pdfUnderTest, other.pdfUnderTest);
	}

}
