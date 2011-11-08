/**
 * Chapter 7: ColorShape.java
 * An alternative implementation of smart shapes
 * based on containment and delegation.
 */
public abstract class ColorShape {
    private java.awt.Color _borderColor, _fillColor; // attributes
    private double _rotation;    
    private final int STROKE_WIDTH = 2; 
    private java.awt.geom.RectangularShape _shape; // component


    public ColorShape (java.awt.geom.RectangularShape s) {
	_borderColor = java.awt.Color.white;
	_fillColor = java.awt.Color.white;
	_rotation = 0;
	_shape = s;
    }

    // methods implementing color methods
    public void setBorderColor (java.awt.Color aColor) {
	_borderColor = aColor;
    }

    public void setFillColor (java.awt.Color aColor) {
	_fillColor = aColor;
    } 

    // methods implementing rotation methods
    public double getCenterX () {
	return _shape.getCenterX();
    }
    public double getCenterY () {
	return _shape.getCenterY();
    }
    public void setRotation (double anAngle) {
	_rotation = anAngle;
    }
    public double getRotation () {
	return _rotation;
    }

    // methods that let shape know boundaries of movement
public double getMaxX () {
	return _shape.getMaxX();
    }

    public double getMaxY () {
	return _shape.getMaxY();
    }

    public double getX() {
	return _shape.getX();
    }

    public double getY() {
	return _shape.getY();
    }

    // code implementing location methods
public void setLocation (int x, int y) {
	_shape.setFrame (x, y, _shape.getWidth(), 
                       _shape.getHeight());
    }

    // code implementing size methods
public void setSize (int width, int height) {
	 _shape.setFrame(_shape.getX(), _shape.getY(), 
                      width, height);
    }

    // code implementing draw and fill methods
public void draw (java.awt.Graphics2D aBrush) {
	java.awt.Color savedColor = aBrush.getColor();
	aBrush.setColor(_borderColor);
	java.awt.Stroke savedStroke = aBrush.getStroke();
	aBrush.setStroke(new java.awt.BasicStroke(STROKE_WIDTH));
     aBrush.rotate(_rotation, 
                   _shape.getCenterX(),_shape.getCenterY());
	aBrush.draw(_shape);
     aBrush.rotate(-_rotation, 
                   _shape.getCenterX(),_shape.getCenterY());
	aBrush.setStroke(savedStroke);
	aBrush.setColor(savedColor);
    }

    public void fill (java.awt.Graphics2D aBrush){
	java.awt.Color savedColor = aBrush.getColor();
	aBrush.setColor(_fillColor);
     aBrush.rotate(_rotation, 
                   _shape.getCenterX(),_shape.getCenterY());
	aBrush.fill(_shape);
     aBrush.rotate(-_rotation, 
                   _shape.getCenterX(),_shape.getCenterY());
	aBrush.setColor(savedColor);
    }

}
