package io.github.derkrischan.pdftest.page;

import org.apache.pdfbox.pdmodel.common.PDRectangle;

/**
 * Common paper sizes. According to DIN standard the allowed tollerance is 
 * ±1,5mm for measurements up to 150mm, ±2mm for measurements till 600mm and ±3mm
 * for all sizes above.
 * 
 * @author krischan
 *
 */
public enum PaperSize {

	/** size of A0 Paper (841mm × 1189mm).*/
	A0(PDRectangle.A0, 3f, 3f),
	/** size of A1 Paper (594mm × 841mm).*/
	A1(PDRectangle.A1, 2f, 3f),
	/** size of A2 Paper (420mm × 594mm).*/
	A2(PDRectangle.A2, 2f, 2f),
	/** size of A3 Paper (297mm × 420mm).*/
	A3(PDRectangle.A3, 2f, 2f),
	/** size of A4 Paper (210mm × 297mm).*/
	A4(PDRectangle.A4, 2f, 2f),
	/** size of A5 Paper (148mm × 210mm).*/
	A5(PDRectangle.A5, 1.5f, 2f),
	/** size of A6 Paper (105mm × 148mm).*/
	A6(PDRectangle.A6, 1.5f, 1.5f),
	/** size of U.S. Legal, 8,5" x 14".*/
	LEGAL(PDRectangle.LEGAL, 2f, 2f),
	/** size of U.S. Letter, 8,5" x 11".*/
	LETTER(PDRectangle.LETTER, 2f, 2f);
	
	private final PDRectangle rectangle;
	
	private final float widthToleranceInMillimeter;
	
	private final float heightToleranceInMillimeter;
	
	private PaperSize(final PDRectangle rectangle, final float widthToleranceInMillimeter, final float heightToleranceInMillimeter) {
		this.rectangle = rectangle;
		this.widthToleranceInMillimeter = widthToleranceInMillimeter;
		this.heightToleranceInMillimeter = heightToleranceInMillimeter;
	}

	public PDRectangle getRectangle() {
		return rectangle;
	}

	public float getWidthToleranceInMillimeter() {
		return widthToleranceInMillimeter;
	}

	public float getHeightToleranceInMillimeter() {
		return heightToleranceInMillimeter;
	}
}
