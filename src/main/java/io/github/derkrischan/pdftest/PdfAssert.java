package io.github.derkrischan.pdftest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.assertj.core.api.Fail;
import org.assertj.core.util.CheckReturnValue;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * {@link PdfAssert} provides several checks on PDF elements via fluent API.
 * 
 * @author krischan
 *
 */
public class PdfAssert extends AbstractPdfAssert<PdfAssert, PDDocument> {

	/** standard error message for missing password exceptions */
	private static final String MISSING_PASSWORD_ERROR_MSG = "Unable to open because of missing password: ";
	
	/**
	 * Package private constructor for {@link PdfAssert} to prevent public usage.
	 * 
	 * @param actualPdf the PDF document under test
	 */
	@SuppressFBWarnings("CD_CIRCULAR_DEPENDENCY")
	PdfAssert(final PDDocument actualPdf) {
		super(actualPdf, PdfAssert.class, actualPdf);
	}

	/**
	 * Package private static asserter that creates a new instance of {@link PdfAssert} 
	 * for the given PDF document file name if the file is a valid path.
	 * 
	 * @param fileName the PDF document file name
	 * @return a new instance of {@link PdfAssert} for the given PDF document
	 */
	@CheckReturnValue
	static PdfAssert assertThat(final String fileName) {
		return assertThat(new File(fileName));
	}
	
	/**
	 * Package private static asserter that creates a new instance of {@link PdfAssert} 
	 * for the given PDF document file if the file is valid.
	 * 
	 * @param file the PDF document file
	 * @return a new instance of {@link PdfAssert} for the given PDF document
	 */
	@CheckReturnValue
	static PdfAssert assertThat(final File file) {
		if (file == null) {
			throw new IllegalArgumentException("PDF file cannot be NULL.");
		}
		if (!file.exists()) {
			throw new IllegalArgumentException("PDF file does not exist.");
		}
		PDDocument doc = null;
		try {
			doc = PDDocument.load(file);
			doc.getDocument().setWarnMissingClose(false);
		} catch (InvalidPasswordException e) {
			Fail.fail(MISSING_PASSWORD_ERROR_MSG + e.getMessage());
		} catch (IOException e) {
			Fail.fail("Unable to open file " + file.getName() + ": " + e.getMessage());
		}
		return new PdfAssert(doc);
	}

	/**
	 * Package private static asserter that creates a new instance of {@link PdfAssert} 
	 * for the given PDF input stream if the stream content is a valid PDF.
	 * 
	 * @param inputStream the PDF document input stream
	 * @return a new instance of {@link PdfAssert} for the given PDF document
	 */
	@CheckReturnValue
	static PdfAssert assertThat(final InputStream inputStream) {
		PDDocument doc = null;
		try {
			doc = PDDocument.load(inputStream);
		} catch (InvalidPasswordException e) {
			Fail.fail(MISSING_PASSWORD_ERROR_MSG + e.getMessage());
		} catch (IOException e) {
			Fail.fail("Unable to read PDF from InputStream: " + e.getMessage());
		}
		return new PdfAssert(doc);
	}

	/**
	 * Package private static asserter that creates a new instance of {@link PdfAssert} 
	 * for the given PDF as byte array if the array content is a valid PDF.
	 * 
	 * @param bytes the PDF document as byte array
	 * @return a new instance of {@link PdfAssert} for the given PDF document
	 */
	@CheckReturnValue
	static PdfAssert assertThat(final byte[] bytes) {
		PDDocument doc = null;
		try {
			doc = PDDocument.load(bytes);
		} catch (InvalidPasswordException e) {
			Fail.fail(MISSING_PASSWORD_ERROR_MSG + e.getMessage());
		} catch (IOException e) {
			Fail.fail("Unable to read PDF from bytes: " + e.getMessage());
		}
		return new PdfAssert(doc);
	}
	
	/**
	 * Package private static asserter that creates a new instance of {@link PdfAssert} 
	 * for the given PDF as {@link Path}.
	 * 
	 * @param pBytes the PDF document as byte array
	 * @return a new instance of {@link PdfAssert} for the given PDF document
	 */
	@CheckReturnValue
	static PdfAssert assertThat(final Path path) {
		return assertThat(path.toFile());
	}
	
	/**
	 * Extracts the page count of the PDF document under test and returns an {@link IntegerAssert} for it.
	 * 
	 * @return an {@link IntegerAssert} for the documents page number
	 */
	public IntegerAssert pageCount() {
		isNotNull();
		return new IntegerAssert(actual.getNumberOfPages(), getPdfUnderTest());
	}
	
	/**
	 * Extracts the author information of the PDF document under test and returns a {@link StringAssert} for it.
	 * 
	 * @return a {@link StringAssert} for the documents author information
	 */
	public StringAssert author() {
		isNotNull();
		return new StringAssert(actual.getDocumentInformation().getAuthor(), getPdfUnderTest());
	}

	/**
	 * Extracts the version information of the PDF document under test and returns a {@link FloatAssert} for it.
	 * 
	 * @return a {@link FloatAssert} for the documents version information
	 */
	public FloatAssert version() {
		isNotNull();
		return new FloatAssert(actual.getVersion(), getPdfUnderTest());
	}

	/**
	 * Extracts the creator information of the PDF document under test and returns a {@link StringAssert} for it.
	 * 
	 * @return a {@link StringAssert} for the documents creator information
	 */
	public StringAssert creator() {
		isNotNull();
		return new StringAssert(actual.getDocumentInformation().getCreator(), getPdfUnderTest());
	}

	/**
	 * Extracts the subject information of the PDF document under test and returns a {@link StringAssert} for it.
	 * 
	 * @return a {@link StringAssert} for the documents subject information
	 */
	public StringAssert subject() {
		isNotNull();
		return new StringAssert(actual.getDocumentInformation().getSubject(), getPdfUnderTest());
	}

	/**
	 * Extracts the title information of the PDF document under test and returns a {@link StringAssert} for it.
	 * 
	 * @return a {@link StringAssert} for the documents title information
	 */
	public StringAssert title() {
		isNotNull();
		return new StringAssert(actual.getDocumentInformation().getTitle(), getPdfUnderTest());
	}

	/**
	 * Extracts the producer information of the PDF document under test and returns a {@link StringAssert} for it.
	 * 
	 * @return a {@link StringAssert} for the documents producer information
	 */
	public StringAssert producer() {
		isNotNull();
		return new StringAssert(actual.getDocumentInformation().getProducer(), getPdfUnderTest());
	}
	
	/**
	 * Extracts the creation date information of the PDF document under test and returns a {@link DateAssert} for it.
	 * 
	 * @return a {@link DateAssert} for the documents creation date information
	 */
	public DateAssert creationDate() {
		return new DateAssert(actual.getDocumentInformation().getCreationDate().getTime(), actual);
	}
	
	/**
	 * Extracts the whole text of the PDF document under test and returns a {@link StringAssert} for it.
	 * 
	 * @return a {@link StringAssert} for the documents text
	 */
	public StringAssert text() {
		return textBetweenPages(1, actual.getNumberOfPages());
	}
	
	/**
	 * Extracts the text between given pages from the PDF document under test and returns a {@link StringAssert} for it.
	 * 
	 * @param startPage the start page to extract text from (inclusive)
	 * @param endPage the end page to extract text from (inclusive)
	 * @return a {@link StringAssert} for the documents text between given pages
	 */
	public StringAssert textBetweenPages(final int startPage, final int endPage) {
		isNotNull();
		if (startPage < 1 || startPage > endPage || endPage > actual.getNumberOfPages()) {
			failWithMessage("Illegal start- and end page provided.");
			return null;
		}
		try {
			PDFTextStripper stripper = new PDFTextStripper();
			stripper.setStartPage(startPage);
			stripper.setEndPage(endPage);
			return new StringAssert(stripper.getText(actual), getPdfUnderTest());
		} catch (IOException err) {
			failWithMessage("Unable to extract text from PDF page: " + err.getMessage());
			return null;
		}
	}
	
}
