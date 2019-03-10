package io.github.derkrischan.pdftest;

import java.io.File;
import java.io.InputStream;

import org.assertj.core.util.CheckReturnValue;

/**
 * Central collection of public accessible PDF asserters. 
 * 
 * @author cwc
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
	 * @param pFile the {@link File} for the PDF document to test
	 * @return a new instance of {@link PdfAssert} if pFile is a valid PDF
	 */
	@CheckReturnValue
	public static PdfAssert assertThat(final File pFile) {
		return PdfAssert.assertThat(pFile);
	}

	/**
	 * Creates a {@link PdfAssert} for the given PDF document from file name as string.
	 * 
	 * @param pFileName the string representation for the PDF document to test
	 * @return a new instance of {@link PdfAssert} if pFileName represents a valid PDF
	 */
	@CheckReturnValue
	public static PdfAssert assertThat(final String pFileName) {
		return PdfAssert.assertThat(pFileName);
	}

	/**
	 * Creates a {@link PdfAssert} for the given PDF document from {@link InputStream}.
	 * 
	 * @param pInputStream the {@link InputStream} for the PDF document to test
	 * @return a new instance of {@link PdfAssert} from PDF stream
	 */
	@CheckReturnValue
	public static PdfAssert assertThat(final InputStream pInputStream) {
		return PdfAssert.assertThat(pInputStream);
	}

	/**
	 * Creates a {@link PdfAssert} for the given PDF document from array of bytes.
	 * 
	 * @param pBytes the PDF document to test as byte array
	 * @return a new instance of {@link PdfAssert} from PDF byte array
	 */
	@CheckReturnValue
	public static PdfAssert assertThat(final byte[] pBytes) {
		return PdfAssert.assertThat(pBytes);
	}

	/**
	 * Creates a {@link PdfFormatAssert} for the given PDF document from file name as string.
	 * 
	 * @param pFileName the string representation for the PDF document to validate
	 * @return a new instance of {@link PdfAssert} if pFileName represents a valid PDF path
	 */
	@CheckReturnValue
	public static PdfFormatAssert assertFormatPdf1A(final String pFileName) {
		return PdfFormatAssert.assertThat(pFileName);
	}
	
	
}
