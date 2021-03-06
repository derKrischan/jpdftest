package io.github.derkrischan.pdftest;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import javax.activation.DataSource;
import javax.activation.FileDataSource;

import org.apache.pdfbox.preflight.PreflightDocument;
import org.apache.pdfbox.preflight.ValidationResult;
import org.apache.pdfbox.preflight.ValidationResult.ValidationError;
import org.apache.pdfbox.preflight.parser.PreflightParser;
import org.apache.pdfbox.preflight.utils.ByteArrayDataSource;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.internal.Failures;
import org.assertj.core.util.CheckReturnValue;

/**
 * Asserter that does NOT work on a parsed PDF document instance. 
 * Instead it uses the PdfBox preflight parser to validate the given PDF
 * for conformity to PDF/A-1b standard.
 * 
 * @author krischan
 *
 */
public class PdfFormatAssert extends AbstractAssert<PdfFormatAssert, DataSource> {

	/** error message string used in failures */
	private static final String PARSE_FILE_ERROR_MESSAGE = " cannot be parsed for check of PDF/A-1b validity: ";
	
	/**
	 * Package private constructor to prevent public instantiation.
	 * The asserter should be created from {@link PdfAssertions}.  
	 * 
	 * @param pdf the PDF file name
	 */
	PdfFormatAssert(final DataSource pdf) {
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
		return new PdfFormatAssert(new FileDataSource(fileName));
	}
	
	/**
	 * Package private static asserter to create a new instance Of this asserter.
	 * 
	 * @param pdfStream the PDF file as input stream
	 * @return a new instance of {@link PdfFormatAssert}
	 * @throws IllegalArgumentException in case the given input stream is not a valid PDF document
	 */
	@CheckReturnValue
	static PdfFormatAssert assertThat(final InputStream pdfStream) {
		try {
			return new PdfFormatAssert(new ByteArrayDataSource(pdfStream));
		} catch (IOException ioException) {
			throw new IllegalArgumentException("Given input stream is not a valid PDF document.", ioException);
		}
		
	}
	
	/**
	 * Package private static asserter to create a new instance Of this asserter.
	 * 
	 * @param pdf the PDF file as byte array
	 * @return a new instance of {@link PdfFormatAssert}
	 * @throws IllegalArgumentException in case the given byte array is not a valid PDF document
	 */
	@CheckReturnValue
	static PdfFormatAssert assertThat(final byte[] pdf) {
		try {
			return new PdfFormatAssert(new ByteArrayDataSource(new ByteArrayInputStream(pdf)));
		} catch (IOException ioException) {
			throw new IllegalArgumentException("Given byte array is not a valid PDF document.", ioException);
		}
		
	}
	
	/**
	 * Package private static asserter to create a new instance Of this asserter.
	 * 
	 * @param pdf the PDF file
	 * @return a new instance of {@link PdfFormatAssert}
	 */
	@CheckReturnValue
	static PdfFormatAssert assertThat(final File pdf) {
			return new PdfFormatAssert(new FileDataSource(pdf));
	}
	
	/**
	 * Package private static asserter to create a new instance Of this asserter.
	 * 
	 * @param pdfPath the PDF file path
	 * @return a new instance of {@link PdfFormatAssert}
	 */
	@CheckReturnValue
	static PdfFormatAssert assertThat(final Path pdfPath) {
		return new PdfFormatAssert(new FileDataSource(pdfPath.toFile()));
	}
	
	/**
	 * Checks that the PDF document is NOT PDF/A-1b compliant.
	 * 
	 * @return this asserters instance
	 */
	public PdfFormatAssert validateNoPdfA1bCompliance() {
		PreflightParser preflightParser = createPreflightParser();
		try (PreflightDocument document = preflightParser.getPreflightDocument()) { 
			document.validate();

		    // Get validation result
			ValidationResult result = document.getResult();
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
		PreflightParser preflightParser = createPreflightParser();
		try (PreflightDocument document = preflightParser.getPreflightDocument()) { 
			document.validate();

		    // Get validation result
			ValidationResult result = document.getResult();
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
	
	private PreflightParser createPreflightParser() {
		try {
			PreflightParser preflightParser = new PreflightParser(actual);
			preflightParser.parse();
			return preflightParser;
		} catch (IOException e) {
			throw Failures.instance().failure(actual + PARSE_FILE_ERROR_MESSAGE + e.getMessage()); 
		}
	}

}
