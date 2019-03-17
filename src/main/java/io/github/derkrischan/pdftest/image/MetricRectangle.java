package io.github.derkrischan.pdftest.image;

import java.awt.geom.Rectangle2D;

/**
 * 
 * A rectangle defined in millimeters.
 * 
 * @author krischan
 *
 */
public class MetricRectangle {

	/** pixel to millimeter fraction (1 mm = 2.835 points) */
	private static final double POINT_MM_FRACTION = 2.834645669291339; 
	
	/** lower left X coordinate in millimeters */
	private final double x;
	
	/** lower left Y coordinate in millimeters */
	private final double y;
	
	/** box width in millimeters */
	private final double width;
	
	/** box height in millimeters */
	private final double height;
	
	/**
	 * 
	 * Constructor for {@link MetricRectangle} 
	 * 
	 * @param pX lower left X coordinate in millimeters
	 * @param pY lower left Y coordinate in millimeters
	 * @param pWidth lower left Y coordinate in millimeters
	 * @param pHeight box height in millimeters
	 */
	public MetricRectangle(final double pX, final double pY, final double pWidth, final double pHeight) {
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
	 * Returns a Java {@link Rectangle2D} transformed from millimeters to points.
	 * 
	 * @return the corresponding {@link Rectangle2D} with points
	 */
	public Rectangle2D toRect2D() {
		return new Rectangle2D.Double(x * POINT_MM_FRACTION, y * POINT_MM_FRACTION, width * POINT_MM_FRACTION, height * POINT_MM_FRACTION);
	}
	
	/**
	 * 
	 * Creates a Java {@link Rectangle2D} transformed from given millimeters to points.
	 * 
	 * @param pX lower left X coordinate in millimeters
	 * @param pY lower left y coordinate in millimeters
	 * @param pWidth box width in millimeters
	 * @param pHeight box height in millimeters
	 * @return the corresponding {@link Rectangle2D} with points
	 */
	public static Rectangle2D create(final double pX, final double pY, final double pWidth, final double pHeight) {
		return new Rectangle2D.Double(pX * POINT_MM_FRACTION, pY * POINT_MM_FRACTION, pWidth * POINT_MM_FRACTION, pHeight * POINT_MM_FRACTION);
	}
}
