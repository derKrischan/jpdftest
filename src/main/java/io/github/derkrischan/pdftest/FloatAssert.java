package io.github.derkrischan.pdftest;


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
	public PDDocument getPdfUnderTest() {
		return pdfUnderTest;
	}
}
