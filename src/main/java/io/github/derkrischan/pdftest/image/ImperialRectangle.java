package io.github.derkrischan.pdftest.image;

import java.awt.geom.Rectangle2D;

/**
 * 
 * A rectangle defined in Anglo-Saxon inches (imperial units).
 * 
 * @author krischan
 *
 */
public class ImperialRectangle extends AbstractRectangle {

	/** pixel to inch fraction (1 point = 1/72 inches) */ 
	private static final double POINT_INCH_FRACTION = 72.0; 
	
	/**
	 * Constructor for {@link ImperialRectangle}.
	 * 
	 * @param x lower left X coordinate in inches
	 * @param y lower left y coordinate in inches
	 * @param width box width in inches
	 * @param height box height in inches
	 */
	public ImperialRectangle(final double x, final double y, final double width, final double height) {
		super(x, y, width, height);
	}
	
	/**
	 * 
	 * Returns a Java {@link Rectangle2D} transformed from inches to points.
	 * 
	 * @return the corresponding {@link Rectangle2D} with points
	 */
	public Rectangle2D toRect2D() {
		return new Rectangle2D.Double(getX() * POINT_INCH_FRACTION, getY() * POINT_INCH_FRACTION, getWidth() * POINT_INCH_FRACTION, getHeight() * POINT_INCH_FRACTION);
	}
	
	/**
	 * Creates a Java {@link Rectangle2D} transformed from given inches to points.
	 * 
	 * @param x lower left X coordinate in inches
	 * @param y lower left y coordinate in inches
	 * @param width box width in inches
	 * @param height box height in inches
	 * @return the corresponding {@link Rectangle2D} with points
	 */
	public static Rectangle2D create(final double x, final double y, final double width, final double height) {
		return new Rectangle2D.Double(x * POINT_INCH_FRACTION, y * POINT_INCH_FRACTION, width * POINT_INCH_FRACTION, height * POINT_INCH_FRACTION);
	}
}
