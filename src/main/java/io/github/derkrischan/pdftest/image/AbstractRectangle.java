package io.github.derkrischan.pdftest.image;

/**
 * An abstract rectangle without specific metrics used to define
 * a lower left point at (x,y) with given width and height.
 * 
 * @author krischan
 *
 */
public abstract class AbstractRectangle {

	/** lower left X coordinate in used metric */
	private final double x;
	
	/** lower left Y coordinate in used metric */
	private final double y;
	
	/** box width in used metric */
	private final double width;
	
	/** box height in used metric */
	private final double height;
	
	/**
	 * 
	 * Constructor for {@link AbstractRectangle} 
	 * 
	 * @param pX lower left X coordinate in used metric
	 * @param pY lower left Y coordinate in used metric
	 * @param pWidth lower left Y coordinate in used metric
	 * @param pHeight box height in used metric
	 */
	public AbstractRectangle(final double pX, final double pY, final double pWidth, final double pHeight) {
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
}
