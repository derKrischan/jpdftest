package io.github.derkrischan.pdftest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.assertj.core.api.Fail;
import org.assertj.core.util.CheckReturnValue;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.github.derkrischan.pdftest.page.Orientation;
import io.github.derkrischan.pdftest.page.PaperSize;

/**
 * {@link PdfAssert} provides several checks on PDF elements via fluent API.
 * 
 * @author krischan
 *
 */
public class PdfAssert extends AbstractPdfAssert<PdfAssert, PDDocument> {

	/** standard error message for missing password exceptions */
	private static final String MISSING_PASSWORD_ERROR_MSG = "Unable to open because of missing password: ";

	/** standard error message for wrong password exceptions */
	private static final String WRONG_PASSWORD_ERROR_MSG = "Unable to open because of wrong password: ";
	
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
		return assertThat(fileName, null);
	}
	
	/**
	 * Package private static asserter that creates a new instance of {@link PdfAssert} 
	 * for the given PDF document file name if the file is a valid path.
	 * 
	 * @param fileName the PDF document file name
	 * @param password user password to open the document
	 * @return a new instance of {@link PdfAssert} for the given PDF document
	 */
	@CheckReturnValue
	static PdfAssert assertThat(final String fileName, final String password) {
		return assertThat(new File(fileName), password);
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
		return assertThat(file, null);
	}
	
	/**
	 * Package private static asserter that creates a new instance of {@link PdfAssert} 
	 * for the given PDF document file if the file is valid.
	 * 
	 * @param file the PDF document file
	 * @param password user password to open the document
	 * @return a new instance of {@link PdfAssert} for the given PDF document
	 */
	@CheckReturnValue
	static PdfAssert assertThat(final File file, final String password) {
		if (file == null) {
			throw new IllegalArgumentException("PDF file cannot be NULL.");
		}
		if (!file.exists()) {
			throw new IllegalArgumentException("PDF file does not exist.");
		}
		PDDocument doc = null;
		try {
			doc = PDDocument.load(file, password);
			doc.getDocument().setWarnMissingClose(false);
		} catch (InvalidPasswordException e) {
			if (StringUtils.isBlank(password)) {
				Fail.fail(MISSING_PASSWORD_ERROR_MSG + e.getMessage());
			} else {
				Fail.fail(WRONG_PASSWORD_ERROR_MSG + e.getMessage());
			}
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
		return assertThat(inputStream, null);
	}
	
	/**
	 * Package private static asserter that creates a new instance of {@link PdfAssert} 
	 * for the given PDF input stream if the stream content is a valid PDF.
	 * 
	 * @param inputStream the PDF document input stream
	 * @param password user password to open the document
	 * @return a new instance of {@link PdfAssert} for the given PDF document
	 */
	@CheckReturnValue
	static PdfAssert assertThat(final InputStream inputStream, final String password) {
		PDDocument doc = null;
		try {
			doc = PDDocument.load(inputStream, password);
			doc.getDocument().setWarnMissingClose(false);
		} catch (InvalidPasswordException e) {
			if (StringUtils.isBlank(password)) {
				Fail.fail(MISSING_PASSWORD_ERROR_MSG + e.getMessage());
			} else {
				Fail.fail(WRONG_PASSWORD_ERROR_MSG + e.getMessage());
			}
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
		return assertThat(bytes, null);
	}
	
	/**
	 * Package private static asserter that creates a new instance of {@link PdfAssert} 
	 * for the given PDF as byte array if the array content is a valid PDF.
	 * 
	 * @param bytes the PDF document as byte array
	 * @param password user password to open the document
	 * @return a new instance of {@link PdfAssert} for the given PDF document
	 */
	@CheckReturnValue
	static PdfAssert assertThat(final byte[] bytes, final String password) {
		PDDocument doc = null;
		try {
			doc = PDDocument.load(bytes, password);
			doc.getDocument().setWarnMissingClose(false);
		} catch (InvalidPasswordException e) {
			if (StringUtils.isBlank(password)) {
				Fail.fail(MISSING_PASSWORD_ERROR_MSG + e.getMessage());
			} else {
				Fail.fail(WRONG_PASSWORD_ERROR_MSG + e.getMessage());
			}
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
		return assertThat(path, null);
	}

	/**
	 * Package private static asserter that creates a new instance of {@link PdfAssert} 
	 * for the given PDF as {@link Path}.
	 * 
	 * @param pBytes the PDF document as byte array
	 * @param password user password to open the document
	 * @return a new instance of {@link PdfAssert} for the given PDF document
	 */
	@CheckReturnValue
	static PdfAssert assertThat(final Path path, final String password) {
		return assertThat(path.toFile(), password);
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
	
	/**
	 * Checks that all pages of the whole document have the expected paper size (see {@link PaperSize}).
	 * The paper size already includes tolerances defined in DIN standard.
	 * 
	 * @param paperSize the expected paper size
	 * @return this asserter instance
	 */
	public PdfAssert hasPaperSize(final PaperSize paperSize) {
		return hasPaperSize(paperSize.getRectangle(), paperSize.getWidthToleranceInMillimeter(), paperSize.getHeightToleranceInMillimeter());
	}
	
	/**
	 * Checks that all pages of the whole document have the expected size respecting given tolerance values.
	 * 
	 * @param rectangle the expected paper size
	 * @param widthToleranceInMillimeter the allowed tolerance in millimeter for page width
	 * @param heightToleranceInMillimeter the allowed tolerance in millimeter for page height
	 * @return this asserter instance
	 */
	public PdfAssert hasPaperSize(final PDRectangle rectangle, final float widthToleranceInMillimeter, final float heightToleranceInMillimeter) {
		return hasPaperSize(rectangle.getWidth(), rectangle.getHeight(), widthToleranceInMillimeter, heightToleranceInMillimeter);
	}
	
	/**
	 * Checks that all pages of the whole document have the expected size respecting given tolerance values.
	 * 
	 * @param width the expected paper width
	 * @param height the expected paper height
	 * @param widthToleranceInMillimeter the allowed tolerance in millimeter for page width
	 * @param heightToleranceInMillimeter the allowed tolerance in millimeter for page height
	 * @return this asserter instance
	 */
	public PdfAssert hasPaperSize(final float width, final float height, final float widthToleranceInMillimeter, final float heightToleranceInMillimeter) {
		isNotNull();
		for (PDPage page : getPdfUnderTest().getPages()) {
			new PdfPageAssert(page, getPdfUnderTest()).hasPaperSize(width, height, widthToleranceInMillimeter, heightToleranceInMillimeter);
		}
		return this;
	}
	
	/**
	 * Checks the page orientation as displayed for expectation (see {@link Orientation}).
	 * The check also respects page rotation. A page with portrait dimensions and rotated clock wise 90°
	 * will be reported as a page with landscape orientation.
	 * 
	 * @param orientation the expected page orientation
	 * @return this asserter instance
	 */
	public PdfAssert hasPageOrientation(final Orientation orientation) {
		isNotNull();
		for (PDPage page : getPdfUnderTest().getPages()) {
			new PdfPageAssert(page, getPdfUnderTest()).hasPageOrientation(orientation);
		}
		return this;
	}
	
}
