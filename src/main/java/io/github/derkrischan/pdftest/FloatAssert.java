package io.github.derkrischan.pdftest;


import java.util.Objects;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.assertj.core.api.AbstractFloatAssert;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Intermediate FloatAssert class works as a bridge between AssertJ's{@link AbstractFloatAssert} and 
 * {@link FluentPdfAsserter}.
 * The class is package private because the main entry point for PDF verifications should be {@link PdfAssertions}.
 * 
 * @author krischan
 *
 */
public class FloatAssert extends AbstractFloatAssert<FloatAssert> implements FluentPdfAsserter {

	/** the PDF document under test */
	private PDDocument pdfUnderTest;
	
	/**
	 * Package private constructor for {@link FloatAssert} to prevent public usage.
	 * 
	 * @param pDate the float value under test
	 * @param pPdf the PDF document under test
	 */
	@SuppressFBWarnings("CD_CIRCULAR_DEPENDENCY")
	FloatAssert(final Float pActualFloat, final PDDocument pPdf) {
		super(pActualFloat, FloatAssert.class);
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
		if (!(obj instanceof FloatAssert)) {
			return false;
		}
		FloatAssert other = (FloatAssert) obj;
		return Objects.equals(pdfUnderTest, other.pdfUnderTest);
	}

}
