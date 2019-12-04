package io.github.derkrischan.pdftest;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;

import org.assertj.core.util.CheckReturnValue;

/**
 * Central collection of public accessible PDF asserters. 
 * 
 * @author krischan
 *
 */
public final class PdfAssertions {

	static {
		System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");
	}
	
	/**
	 * Private constructor to prevent instantiation of utility class.
	 */
	private PdfAssertions() {
		//NOP
	}
	
	/**
	 * The standard setting for the font cache is a new directory under users home.
	 * This is often not accessible by a Java process that may run as a different user.
	 * Therefore it is redirected to system temporary directory where it is much more 
	 * likely that the process has write access.
	 */
	static {
		if (System.getProperty("pdfbox.fontcache") == null) {
			System.setProperty("pdfbox.fontcache", System.getProperty("java.io.tmpdir", "/tmp"));
		}
	}
	
	/**
	 * Creates a {@link PdfAssert} for the given PDF document from {@link File}.
	 * 
	 * @param file the {@link File} for the PDF document to test
	 * @return a new instance of {@link PdfAssert} if pFile is a valid PDF
	 */
	@CheckReturnValue
	public static PdfAssert assertThat(final File file) {
		return PdfAssert.assertThat(file);
	}

	/**
	 * Creates a {@link PdfAssert} for the given PDF document from {@link File}.
	 * 
	 * @param file the {@link File} for the PDF document to test
	 * @param password user password to open the document
	 * @return a new instance of {@link PdfAssert} if pFile is a valid PDF
	 */
	@CheckReturnValue
	public static PdfAssert assertThat(final File file, final String password) {
		return PdfAssert.assertThat(file, password);
	}

	/**
	 * Creates a {@link PdfAssert} for the given PDF document from file name as string.
	 * 
	 * @param fileName the string representation for the PDF document to test
	 * @return a new instance of {@link PdfAssert} if pFileName represents a valid PDF
	 */
	@CheckReturnValue
	public static PdfAssert assertThat(final String fileName) {
		return PdfAssert.assertThat(fileName);
	}

	/**
	 * Creates a {@link PdfAssert} for the given PDF document from file name as string.
	 * 
	 * @param fileName the string representation for the PDF document to test
	 * @param password user password to open the document
	 * @return a new instance of {@link PdfAssert} if pFileName represents a valid PDF
	 */
	@CheckReturnValue
	public static PdfAssert assertThat(final String fileName, final String password) {
		return PdfAssert.assertThat(fileName, password);
	}

	/**
	 * Creates a {@link PdfAssert} for the given PDF document from {@link InputStream}.
	 * 
	 * @param inputStream the {@link InputStream} for the PDF document to test
	 * @return a new instance of {@link PdfAssert} from PDF stream
	 */
	@CheckReturnValue
	public static PdfAssert assertThat(final InputStream inputStream) {
		return PdfAssert.assertThat(inputStream);
	}

	/**
	 * Creates a {@link PdfAssert} for the given PDF document from {@link InputStream}.
	 * 
	 * @param inputStream the {@link InputStream} for the PDF document to test
	 * @param password user password to open the document
	 * @return a new instance of {@link PdfAssert} from PDF stream
	 */
	@CheckReturnValue
	public static PdfAssert assertThat(final InputStream inputStream, final String password) {
		return PdfAssert.assertThat(inputStream, password);
	}

	/**
	 * Creates a {@link PdfAssert} for the given PDF document from array of bytes.
	 * 
	 * @param bytes the PDF document to test as byte array
	 * @return a new instance of {@link PdfAssert} from PDF byte array
	 */
	@CheckReturnValue
	public static PdfAssert assertThat(final byte[] bytes) {
		return PdfAssert.assertThat(bytes);
	}

	/**
	 * Creates a {@link PdfAssert} for the given PDF document from array of bytes.
	 * 
	 * @param bytes the PDF document to test as byte array
	 * @param password user password to open the document
	 * @return a new instance of {@link PdfAssert} from PDF byte array
	 */
	@CheckReturnValue
	public static PdfAssert assertThat(final byte[] bytes, final String password) {
		return PdfAssert.assertThat(bytes, password);
	}
	
	/**
	 * Creates a {@link PdfAssert} for the given PDF document from {@link Path}.
	 * 
	 * @param path the PDF document to test as {@link Path}
	 * @return a new instance of {@link PdfAssert} from PDF byte array
	 */
	@CheckReturnValue
	public static PdfAssert assertThat(final Path path) {
		return PdfAssert.assertThat(path);
	}

	/**
	 * Creates a {@link PdfAssert} for the given PDF document from {@link Path}.
	 * 
	 * @param path the PDF document to test as {@link Path}
	 * @param password user password to open the document
	 * @return a new instance of {@link PdfAssert} from PDF byte array
	 */
	@CheckReturnValue
	public static PdfAssert assertThat(final Path path, final String password) {
		return PdfAssert.assertThat(path, password);
	}

	/**
	 * Creates a {@link PdfFormatAssert} for the given PDF document from file name as string.
	 * 
	 * @param fileName the string representation for the PDF document to validate
	 * @return a new instance of {@link PdfAssert} if pFileName represents a valid PDF path
	 */
	@CheckReturnValue
	public static PdfFormatAssert assertFormatPdf1A(final String fileName) {
		return PdfFormatAssert.assertThat(fileName);
	}
	
	
}
