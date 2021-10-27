package io.github.derkrischan.pdftest;


import java.util.function.Consumer;

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
    public void eachPage(Consumer<? super PdfPageAssert> action) {
        FluentPdfAssertionHelper.getPageAssertersForDocument(getPdfUnderTest()).forEach(action);
    }
	
	@Override
	public PDDocument getPdfUnderTest() {
		return pdfUnderTest;
	}
}
