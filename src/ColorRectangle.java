/**
 * Chapter 7.6*: alternative design for ColorRectangle
 */
public class ColorRectangle extends ColorShape {
    public ColorRectangle(java.awt.Color aBorderColor, 
			  java.awt.Color aFillColor){
	super(new java.awt.geom.Rectangle2D.Double());
	super.setBorderColor(aBorderColor);
	super.setFillColor(aFillColor);
    }
}
