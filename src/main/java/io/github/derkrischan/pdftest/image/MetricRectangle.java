package io.github.derkrischan.pdftest.image;

import java.awt.geom.Rectangle2D;

/**
 * 
 * A rectangle defined in millimeters.
 * 
 * @author krischan
 *
 */
public class MetricRectangle extends AbstractRectangle {

	/** pixel to millimeter fraction (1 mm = 2.835 points) */
	private static final double POINT_MM_FRACTION = 2.834645669291339; 
	
	/**
	 * 
	 * Constructor for {@link MetricRectangle} 
	 * 
	 * @param x lower left X coordinate in millimeters
	 * @param y lower left Y coordinate in millimeters
	 * @param width lower left Y coordinate in millimeters
	 * @param height box height in millimeters
	 */
	public MetricRectangle(final double x, final double y, final double width, final double height) {
		super(x, y, width, height);
	}
	
	/**
	 * 
	 * Returns a Java {@link Rectangle2D} transformed from millimeters to points.
	 * 
	 * @return the corresponding {@link Rectangle2D} with points
	 */
	public Rectangle2D toRect2D() {
		return new Rectangle2D.Double(getX() * POINT_MM_FRACTION, getY() * POINT_MM_FRACTION, getWidth() * POINT_MM_FRACTION, getHeight() * POINT_MM_FRACTION);
	}
	
	/**
	 * 
	 * Creates a Java {@link Rectangle2D} transformed from given millimeters to points.
	 * 
	 * @param x lower left X coordinate in millimeters
	 * @param y lower left y coordinate in millimeters
	 * @param width box width in millimeters
	 * @param height box height in millimeters
	 * @return the corresponding {@link Rectangle2D} with points
	 */
	public static Rectangle2D create(final double x, final double y, final double width, final double height) {
		return new Rectangle2D.Double(x * POINT_MM_FRACTION, y * POINT_MM_FRACTION, width * POINT_MM_FRACTION, height * POINT_MM_FRACTION);
	}
}
