package io.github.derkrischan.pdftest;


import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.assertj.core.api.AbstractCharSequenceAssert;

/**
 * Intermediate StringAssert class works as a bridge between AssertJ's {@link AbstractCharSequenceAssert} and 
 * {@link FluentPdfAsserter}.
 * The class is package private because the main entry point for PDF verifications should be {@link PdfAssertions}.
 * 
 * @author cwc
 *
 */
public class StringAssert extends AbstractCharSequenceAssert<StringAssert, String> implements FluentPdfAsserter {

	/** the PDF document under test */
	private PDDocument pdfUnderTest;
	
	/**
	 * Package private constructor for {@link StringAssert} to prevent public usage.
	 * 
	 * @param pActualString the string value under test
	 * @param pPdf the PDF document under test
	 */
	StringAssert(final String pActualString, final PDDocument pPdf) {
		super(pActualString, StringAssert.class);
		pdfUnderTest = pPdf;
	}

	/**
	 * Chomps the string under test (removes appended line breaks) and returns the {@link StringAssert} again. 
	 * 
	 * @return the {@link StringAssert} working on a chomped instance of the string under test.
	 */
	public StringAssert chomp() {
		return new StringAssert(StringUtils.chomp(actual), getPdfUnderTest());
	}
	
	/**
	 * Trims the string under test and returns the {@link StringAssert} again. 
	 * 
	 * @return the {@link StringAssert} working on a trimed instance of the string under test.
	 */
	public StringAssert trim() {
		return new StringAssert(StringUtils.trim(actual), getPdfUnderTest());
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
}
