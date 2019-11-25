package io.github.derkrischan.pdftest;

import java.io.IOException;

import org.apache.pdfbox.preflight.PreflightDocument;
import org.apache.pdfbox.preflight.ValidationResult;
import org.apache.pdfbox.preflight.parser.PreflightParser;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.util.CheckReturnValue;

/**
 * Asserter that does NOT work on a parsed PDF document instance. 
 * Instead it uses the PdfBox preflight parser to validate the given PDF
 * for conformity to PDF/A-1b standard.
 * 
 * @author krischan
 *
 */
public class PdfFormatAssert extends AbstractAssert<PdfFormatAssert, String> {

	/**
	 * Package private constructor to prevent public instantiation.
	 * The asserter should be created from {@link PdfAssertions}.  
	 * 
	 * @param pFileName the PDF file name
	 */
	PdfFormatAssert(final String pFileName) {
		super(pFileName, PdfFormatAssert.class);
	}
	
	/**
	 * Package private static asserter to create a new instance Of this asserter.
	 * 
	 * @param pFileName the PDF file name
	 * @return a new instance of {@link PdfFormatAssert}
	 */
	@CheckReturnValue
	static PdfFormatAssert assertThat(String pFileName) {
		return new PdfFormatAssert(pFileName);
	}
	
	/**
	 * Checks that the PDF document that corresponds to given file path
	 * when this asserter was created, is PDF/A-1b compliant.
	 * 
	 * @return this asserters instance
	 */
	public PdfFormatAssert validatePdfA1bCompliance() {
		PreflightParser preflightParser = null;
		try {
			preflightParser = new PreflightParser(actual);
			preflightParser.parse();
		} catch (IOException e) {
			failWithMessage(actual + " cannot be parsed for check of PDF/A-1b validity: " + e.getMessage());
			return this;
		}
		try (PreflightDocument document = preflightParser.getPreflightDocument()) { 
			document.validate();

		    // Get validation result
			ValidationResult result = document.getResult();
		    if (!result.isValid()) {
		    	failWithMessage(actual + " ist kein valides Dokument nach PDF/A-1b Spezifikation");
		    }
		} catch (IOException e) {
			failWithMessage(actual + " cannot be parsed for check of PDF/A-1b validity: " + e.getMessage());
		}
		return this;
	}

}
