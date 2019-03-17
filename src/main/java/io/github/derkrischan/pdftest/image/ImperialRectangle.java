package io.github.derkrischan.pdftest.image;

import java.awt.geom.Rectangle2D;

/**
 * 
 * A rectangle defined in Anglo-Saxon inches (imperial units).
 * 
 * @author krischan
 *
 */
public class ImperialRectangle {

	/** pixel to inch fraction (1 point = 1/72 inches) */ 
	private static final double POINT_INCH_FRACTION = 72.0; 
	
	/** lower left X coordinate in inches */
	private final double x;
	
	/** lower left y coordinate in inches */
	private final double y;
	
	/** box width in inches */
	private final double width;
	
	/** box height in inches */
	private final double height;
	
	/**
	 * Constructor for {@link ImperialRectangle}.
	 * 
	 * @param pX lower left X coordinate in inches
	 * @param pY lower left y coordinate in inches
	 * @param pWidth box width in inches
	 * @param pHeight box height in inches
	 */
	public ImperialRectangle(final double pX, final double pY, final double pWidth, final double pHeight) {
		x = pX;
		y = pY;
		width = pWidth;
		height = pHeight;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	/**
	 * 
	 * Returns a Java {@link Rectangle2D} transformed from inches to points.
	 * 
	 * @return the corresponding {@link Rectangle2D} with points
	 */
	public Rectangle2D toRect2D() {
		return new Rectangle2D.Double(x * POINT_INCH_FRACTION, y * POINT_INCH_FRACTION, width * POINT_INCH_FRACTION, height * POINT_INCH_FRACTION);
	}
	
	/**
	 * Creates a Java {@link Rectangle2D} transformed from given inches to points.
	 * 
	 * @param pX lower left X coordinate in inches
	 * @param pY lower left y coordinate in inches
	 * @param pWidth box width in inches
	 * @param pHeight box height in inches
	 * @return the corresponding {@link Rectangle2D} with points
	 */
	public static Rectangle2D create(final double pX, final double pY, final double pWidth, final double pHeight) {
		return new Rectangle2D.Double(pX * POINT_INCH_FRACTION, pY * POINT_INCH_FRACTION, pWidth * POINT_INCH_FRACTION, pHeight * POINT_INCH_FRACTION);
	}
}
