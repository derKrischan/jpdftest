package io.github.derkrischan.pdftest.image;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.imageio.ImageIO;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

/**
 * The PdfImageExtractor is a small helper to batch extract all images found in a given PDF document and stores them to a given folder.
 * This might be helpful for creating test data from already manually proven PDF files.
 * Create a small unit test calling this helper or start the simple main method appended to this class to get the images. 
 * 
 * @author krischan
 *
 */
public final class PdfImageExtracter {

	/** atomic counter used to number the exported images */
	private static AtomicInteger counter = new AtomicInteger(0);
	
	/**
	 * Private constructor to prevent instantiation of utility class.
	 */
	private PdfImageExtracter() {
		//NOP
	}
	
	/**
	 * Static method that exports all images found in given PDF document file path to given folder.
	 * 
	 * @param pPdfFileName name of the PDF file path
	 * @param pExportFolderName name of the image export folder
	 * @throws IOException in case of an error extracting or storing the images from PDF
	 */
	public static void extractAllImagesFromPdfToFolder(String pPdfFileName, String pExportFolderName) throws IOException {
		PDDocument doc = Loader.loadPDF(new File(pPdfFileName));
		for (PDPage page : doc.getPages()) {
			extractImagesFromResource(page.getResources(), pExportFolderName);
		}
	}
	
	/**
	 * Extracts all images from a PDResource (e.g. a PDPage).
	 * 
	 * @param pResources the PDResource to extract images from.
	 * @param pExportFolderName the export folder name
	 * @throws IOException in case of an exception processing the resource or storing an image
	 */
	private static void extractImagesFromResource(final PDResources pResources, final String pExportFolderName) throws IOException {
		for (COSName xObjectName : pResources.getXObjectNames()) {
			PDXObject xObject = pResources.getXObject(xObjectName);

			if (xObject instanceof PDFormXObject) {
				extractImagesFromResource(((PDFormXObject) xObject).getResources(), pExportFolderName);
			} else if (xObject instanceof PDImageXObject) {
				ImageIO.write(((PDImageXObject) xObject).getImage(), "png", new File(pExportFolderName + "img-" + counter.getAndIncrement() + ".png"));
			}
		}
	}
	
	/**
	 * Simple starter for this utility that takes two arguments and passes them to extractAllImagesFromPdfToFolder.
	 * 
	 * @param pArgs the first argument should be the path to the PDF file, the second one should be the export folder path.
	 * @throws IOException in case of an error 
	 */
	public static void main(final String[] pArgs) throws IOException {
		PdfImageExtracter.extractAllImagesFromPdfToFolder(pArgs[0], pArgs[1]);
	}
}
