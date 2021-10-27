package io.github.derkrischan.pdftest;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.apache.pdfbox.pdmodel.PDDocument;

/**
 * Interface for fluent PDF testing providing methods that every asserter must implement to switch between pages and the whole document.
 * 
 * @author krischan
 *
 */
public interface FluentPdfAsserter {

	/**
	 * Returns an {@link PdfAssert} for tests on the whole document.
	 * @return a {@link PdfAssert} for the PDF document under test.
	 */
	default PdfAssert document() {
	  return new PdfAssert(getPdfUnderTest());
	}
	
	/**
	 * Returns a {@link PdfPageAssert} for tests on a specific page in the document.
	 * 
	 * @param pPageNumber the PDF page number (starting at 1)
	 * @return a {@link PdfPageAssert} for the given page
	 */
	PdfPageAssert page(final int pPageNumber);
	
	void eachPage(Consumer<? super PdfPageAssert> action);
	
	/**
	 * Getter for the PDF document under test. The document has to be
	 * stored all the time in an instance because otherwise the garbage
	 * collector might destroy an unused Asserter and the PDF document is
	 * closed via {@link AutoCloseable}.
	 *  
	 * @return the PDF document under test
	 */
	PDDocument getPdfUnderTest();
	
	/**
	 * Little helper class to prevent the implementation in every implementing class to return
	 * a {@link PdfPageAssert} for a given page with checks for a valid page number.
	 * 
	 * @author krischan
	 *
	 */
	class FluentPdfAssertionHelper {
		
		/**
		 * Private constructor to prevent instantiation
		 */
		private FluentPdfAssertionHelper() {
			//NOP
		}
		
		/**
		 * Returns a {@link PdfPageAssert} for the given document and page number assuring that the page number is in a valid area.
		 * 
		 * @param pDocument the PDF document
		 * @param pPageNumber the PDF page number (starting at 1)
		 * @return
		 * @throws IllegalArgumentException in case pPageNumber is out of range
		 */
		public static PdfPageAssert getPageAsserterForDocument(final PDDocument pDocument, final int pPageNumber) {
			if (pPageNumber < 1 || pPageNumber > pDocument.getNumberOfPages()) {
				throw new IllegalArgumentException("Illegal page number provided.");
			}
			return new PdfPageAssert(pDocument.getPage(pPageNumber - 1), pDocument);
		}
		
		public static List<PdfPageAssert> getPageAssertersForDocument(final PDDocument pDocument) {
		  List<PdfPageAssert> pages = new ArrayList<>();
	      for (int page = 1; page <= pDocument.getNumberOfPages(); page++) {
	        pages.add(getPageAsserterForDocument(pDocument, page));
	      }
	      return pages;
      }
	}
}
