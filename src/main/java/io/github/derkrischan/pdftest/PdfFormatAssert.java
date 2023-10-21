package io.github.derkrischan.pdftest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.apache.pdfbox.preflight.ValidationResult;
import org.apache.pdfbox.preflight.ValidationResult.ValidationError;
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
public class PdfFormatAssert extends AbstractAssert<PdfFormatAssert, File> {

	/** error message string used in failures */
	private static final String PARSE_FILE_ERROR_MESSAGE = " cannot be parsed for check of PDF/A-1b validity: ";
	
	/**
	 * Package private constructor to prevent public instantiation.
	 * The asserter should be created from {@link PdfAssertions}.  
	 * 
	 * @param pdf the PDF file name
	 */
	PdfFormatAssert(final File pdf) {
		super(pdf, PdfFormatAssert.class);
	}
	
	/**
	 * Package private static asserter to create a new instance Of this asserter.
	 * 
	 * @param fileName the PDF file name
	 * @return a new instance of {@link PdfFormatAssert}
	 */
	@CheckReturnValue
	static PdfFormatAssert assertThat(final String fileName) {
		return new PdfFormatAssert(new File(fileName));
	}
	
	/**
	 * Package private static asserter to create a new instance Of this asserter.
	 * 
	 * @param pdf the PDF file
	 * @return a new instance of {@link PdfFormatAssert}
	 */
	@CheckReturnValue
	static PdfFormatAssert assertThat(final File pdf) {
			return new PdfFormatAssert(pdf);
	}
	
	/**
	 * Package private static asserter to create a new instance Of this asserter.
	 * 
	 * @param pdfPath the PDF file path
	 * @return a new instance of {@link PdfFormatAssert}
	 */
	@CheckReturnValue
	static PdfFormatAssert assertThat(final Path pdfPath) {
		return new PdfFormatAssert(pdfPath.toFile());
	}
	
	/**
	 * Checks that the PDF document is NOT PDF/A-1b compliant.
	 * 
	 * @return this asserters instance
	 */
	public PdfFormatAssert validateNoPdfA1bCompliance() {
		try { 
				ValidationResult result = PreflightParser.validate(actual);
		    if (result.isValid()) {
		    	String errorMessage = actual + " is a valid document conforming PDF/A-1b specification.";
		    	failWithMessage(errorMessage);
		    }
		} catch (IOException e) {
			failWithMessage(actual + PARSE_FILE_ERROR_MESSAGE + e.getMessage());
		}
		return this;
	}
	
	/**
	 * Checks that the PDF document is PDF/A-1b compliant.
	 * 
	 * @return this asserters instance
	 */
	public PdfFormatAssert validatePdfA1bCompliance() {
		try { 
				ValidationResult result = PreflightParser.validate(actual);
				if (!result.isValid()) {
		    	StringBuilder errorMessage = new StringBuilder(actual.toString()).append(" is not a valid document conforming PDF/A-1b specification.");
		    	for (ValidationError validationError : result.getErrorsList()) {
		    		errorMessage.append("\n").append(validationError.getDetails());
		    	}
		    	failWithMessage(errorMessage.toString());
		    }
		} catch (IOException e) {
			failWithMessage(actual + PARSE_FILE_ERROR_MESSAGE + e.getMessage());
		}
		return this;
	}
}
