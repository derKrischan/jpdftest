package io.github.derkrischan.pdftest;

import java.util.Objects;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.assertj.core.api.AbstractAssert;

/**
 * Abstract base class for PDF assertion granting the implementation of {@link FluentPdfAsserter} with navigation between PDF pages and back to the whole document.
 * 
 * @author krischan
 *
 * @param <S> the "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *          target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *          for more details.
 * @param <A> the type of the "actual" value.
 */
public abstract class AbstractPdfAssert<S extends AbstractAssert<S, A>, A> extends AbstractAssert<S, A> implements FluentPdfAsserter {

	/** the PDF Document under test */
	private PDDocument pdfUnderTest;
	
	/**
	 * Constructor for {@link AbstractPdfAssert} that stores the given pdfUnderTest.
	 * 
	 * @param actual the actual value
	 * @param selfType the own asserter type
	 * @param pPdfUnderTest the PDF document under test
	 */
	public AbstractPdfAssert(A actual, Class<?> selfType, final PDDocument pPdfUnderTest) {
		super(actual, selfType);
		pdfUnderTest = pPdfUnderTest;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(pdfUnderTest);
		return result;
	}

	@Deprecated()
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof AbstractPdfAssert)) {
			return false;
		}
		AbstractPdfAssert<?, ?> other = (AbstractPdfAssert<?, ?>) obj;
		return Objects.equals(pdfUnderTest, other.pdfUnderTest);
	}

}
