package io.github.derkrischan.pdftest;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.contentstream.PDFStreamEngine;
import org.apache.pdfbox.contentstream.operator.DrawObject;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.contentstream.operator.state.Concatenate;
import org.apache.pdfbox.contentstream.operator.state.Restore;
import org.apache.pdfbox.contentstream.operator.state.Save;
import org.apache.pdfbox.contentstream.operator.state.SetGraphicsStateParameters;
import org.apache.pdfbox.contentstream.operator.state.SetMatrix;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.apache.pdfbox.util.Matrix;
import org.assertj.core.api.FloatAssert;
import org.assertj.core.data.Offset;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.github.derkrischan.pdftest.page.PaperSize;

/**
 * {@link PdfPageAssert} provides several checks on PDF pages via fluent API.
 * 
 * @author krischan
 *
 */
public class PdfPageAssert extends AbstractPdfAssert<PdfPageAssert, PDPage> {

	/**
	 * Package private constructor for {@link PdfPageAssert} to prevent public usage.
	 * 
	 * @param pActualPdfPage the PDF document page under test
	 * @param pPdf the document under test this page belongs to
	 */
	@SuppressFBWarnings("CD_CIRCULAR_DEPENDENCY")
	PdfPageAssert(final PDPage pActualPdfPage, final PDDocument pPdf) {
		super(pActualPdfPage, PdfPageAssert.class, pPdf);
	}

	/**
	 * Extracts texts from the given rectangular region of the PDF document page under test and returns a {@link StringAssert} for it.
	 * 
	 * @param pRegion the region to extract text from
	 * @return a {@link StringAssert} for the text extracted from given region
	 */
	public StringAssert textInRegion(final Rectangle2D pRegion) {
		isNotNull();
		try {
			PDFTextStripperByArea stripper = new PDFTextStripperByArea();
			stripper.addRegion("testRegion", pRegion);
			stripper.extractRegions(actual);
			return new StringAssert(stripper.getTextForRegion("testRegion"), getPdfUnderTest());
		} catch (IOException err) {
			failWithMessage("Unable to extract text from PDF page.");
			return null;
		}
	}

	/**
	 * Checks whether the image loaded from given image file name is found at page under test.
	 * 
	 * @param pExpectedImageFileName the image file name to search for at this page
	 * @return this asserter instance
	 */
	public PdfPageAssert containsImage(final String pExpectedImageFileName) {
		try {
			return containsImage(ImageIO.read(new File(pExpectedImageFileName)));
		} catch (IOException e) {
			failWithMessage("Unable to load expected image from given path '%s'", pExpectedImageFileName);
			return this;
		}
	}
	
	/**
	 * Checks whether the given image is found at page under test.
	 * 
	 * @param pExpectedImage the image to search for at this page
	 * @return this asserter instance
	 */
	public PdfPageAssert containsImage(final BufferedImage pExpectedImage) {
		try {
			List<BufferedImage> images = getImagesFromResources(actual.getResources());
			boolean found = false;
			for (BufferedImage img : images) {
				if (compareImages(img, pExpectedImage)) {
					found = true;
					break;
				}
			}
			if (!found) {
				failWithMessage("Expected image not found at given page.");
			}
		} catch (IOException e) {
			failWithMessage("Error searching for embedded images in PDF page: " + e.getMessage());
		}
		return this;
	}

	/**
	 * Checks whether the current page has the expected paper size (see {@link PaperSize}).
	 * The paper size already includes tolerances defined in DIN standard.
	 * 
	 * @param paperSize the expected paper size
	 * @return this asserter instance
	 */
	public PdfPageAssert hasPaperSize(final PaperSize paperSize) {
		return hasPaperSize(paperSize.getRectangle(), paperSize.getWidthToleranceInMillimeter(), paperSize.getHeightToleranceInMillimeter());
	}
	
	/**
	 * Checks whether the current page has the expected size respecting given tolerance values.
	 * 
	 * @param rectangle the expected paper size
	 * @param widthToleranceInMillimeter the allowed tolerance in millimeter for page width
	 * @param heightToleranceInMillimeter the allowed tolerance in millimeter for page height
	 * @return this asserter instance
	 */
	public PdfPageAssert hasPaperSize(final PDRectangle rectangle, final float widthToleranceInMillimeter, final float heightToleranceInMillimeter) {
		return hasPaperSize(rectangle.getWidth(), rectangle.getHeight(), widthToleranceInMillimeter, heightToleranceInMillimeter);
	}
	
	/**
	 * Checks whether the current page has the expected size respecting given tolerance values.
	 * 
	 * @param width the expected paper width
	 * @param height the expected paper height
	 * @param widthToleranceInMillimeter the allowed tolerance in millimeter for page width
	 * @param heightToleranceInMillimeter the allowed tolerance in millimeter for page height
	 * @return this asserter instance
	 */
	public PdfPageAssert hasPaperSize(final float width, final float height, final float widthToleranceInMillimeter, final float heightToleranceInMillimeter) {
		new FloatAssert(actual.getBBox().getWidth()).isCloseTo(width, Offset.offset(widthToleranceInMillimeter));
		new FloatAssert(actual.getBBox().getHeight()).isCloseTo(height, Offset.offset(heightToleranceInMillimeter));
		return this;
	}
	
	
	/**
	 * Checks whether the image loaded from given image file name is found in given region at page under test.
	 * 
	 * @param pExpectedImageFileName the image file name to search for in specified region at this page
	 * @param pRegion the region to perform checks in
	 * @return this asserter instance
	 */
	public PdfPageAssert containsImageInRegion(final String pExpectedImageFileName, final Rectangle2D pRegion) {
		try {
			return containsImageInRegion(ImageIO.read(new File(pExpectedImageFileName)), pRegion);
		} catch (IOException e) {
			failWithMessage("Unable to load expected image from given path '%s'", pExpectedImageFileName);
			return this;
		}
	}
	
	/**
	 * Checks whether the given image is found in given region at page under test.
	 * 
	 * @param pExpectedImage the image to search for in specified region at this page
	 * @param pRegion the region to perform checks in
	 * @return this asserter instance
	 */
	public PdfPageAssert containsImageInRegion(final BufferedImage pExpectedImage, final Rectangle2D pRegion) {
		try {
			ImageLocationTestEngine engine = new ImageLocationTestEngine(pExpectedImage, pRegion);
			engine.processPage(actual);
			if (!engine.isFound()) {
				failWithMessage("The given image could not be found in the specified area.");
			}
		} catch (IOException e) {
			failWithMessage("Error searching for embedded images in PDF page: " + e.getMessage());
		}
		return this;
	}

	/**
	 * Extracts images from given PD resource. 
	 * 
	 * @param pResources the resource to extract images from
	 * @return {@link List} of extracted images
	 * @throws IOException in case of an error extracting the images
	 */
	private List<BufferedImage> getImagesFromResources(final PDResources pResources) throws IOException {
		List<BufferedImage> images = new ArrayList<>();

		for (COSName xObjectName : pResources.getXObjectNames()) {
			PDXObject xObject = pResources.getXObject(xObjectName);

			if (xObject instanceof PDFormXObject) {
				images.addAll(getImagesFromResources(((PDFormXObject) xObject).getResources()));
			} else if (xObject instanceof PDImageXObject) {
				images.add(((PDImageXObject) xObject).getImage());
			}
		}
		return images;
	}

	/**
	 * Compares two given images pixel wise.
	 * 
	 * @param pImgA first image for comparison
	 * @param pImgB second image for comparison
	 * @return <code>true</code> if the images are identical, <code>false</code> otherwise
	 */
	static boolean compareImages(final BufferedImage pImgA, final BufferedImage pImgB) {
		if (pImgA.getWidth() != pImgB.getWidth() || pImgA.getHeight() != pImgB.getHeight()) {
			return false;
		}
		// Loop over every pixel.
		for (int y = 0; y < pImgA.getHeight(); y++) {
			for (int x = 0; x < pImgA.getWidth(); x++) {
				// Compare the pixels for equality.
				if (pImgA.getRGB(x, y) != pImgB.getRGB(x, y)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Helper class to search for an image resources in a given rectangular area.
	 * 
	 * @author krischan
	 *
	 */
	static class ImageLocationTestEngine extends PDFStreamEngine {

		/** the region to inspect */
		private Rectangle2D region;
		
		/** the image to search for */
		private BufferedImage expectedImage;
		
		/** global flag indicating the search result */
		private boolean found = false;

		/**
		 * Constructor for the {@link ImageLocationTestEngine}.
		 * 
		 * @param pExpectedImage the image to search for
		 * @param pRegion the region to search in
		 */
		ImageLocationTestEngine(final BufferedImage pExpectedImage, final Rectangle2D pRegion) {
			expectedImage = pExpectedImage;
			region = pRegion;
			addOperator(new Concatenate());
	        addOperator(new DrawObject());
	        addOperator(new SetGraphicsStateParameters());
	        addOperator(new Save());
	        addOperator(new Restore());
	        addOperator(new SetMatrix());
		}

		@Override
		protected void processOperator(Operator operator, List<COSBase> operands) throws IOException {
			String operation = operator.getName();
			if ("Do".equals(operation)) {
				COSName objectName = (COSName) operands.get(0);
				PDXObject xobject = getResources().getXObject(objectName);
				if (xobject instanceof PDImageXObject) {
					PDImageXObject image = (PDImageXObject) xobject;
					if (compareImages(expectedImage, image.getImage())) {
						found = isMatrixInRegion(getGraphicsState().getCurrentTransformationMatrix());
					}
				} else if (xobject instanceof PDFormXObject) {
					showForm((PDFormXObject) xobject);
				}
			} else {
				super.processOperator(operator, operands);
			}
		}
		
		/**
		 * Checks whether the given {@link Matrix} lies within the global defined region.
		 * 
		 * @param pMatrix the extracted image matrix
		 * @return <code>true</code> if the given pMatrix lies within global specified region, <code>false</code> otherwise
		 */
		private boolean isMatrixInRegion(final Matrix pMatrix) {
			float x = pMatrix.getTranslateX();
			float y = pMatrix.getTranslateY();
			float imageWidth = pMatrix.getScalingFactorX();
            float imageHeight = pMatrix.getScalingFactorY();
            if (x >= region.getX() && x <= region.getX() + region.getWidth()) {
            	if (y >= region.getY() && y <= region.getY() + region.getHeight()) {
            		if (imageWidth <= region.getWidth() && x + imageWidth <= region.getX() + region.getWidth()) {
            			if (imageHeight <= region.getHeight() && y + imageHeight <= region.getY() + region.getHeight()) {
            				return true;
            			}
            		}
            	}
            }
            return false;
		}
		
		/**
		 * Returns the search result.
		 * @return <code>true</code> if the expected image was found, <code>false</code> otherwise
		 */
		public boolean isFound() {
			return found;
		}
		
		/**
		 * Resets the global search result to <code>false</code>.
		 */
		public void reset() {
			found = false;
		}
	}
}
