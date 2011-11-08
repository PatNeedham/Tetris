/**
 * Chapter 8: BorderedPanel.java
 * Displays a JLabel in a panel with a colored border.
 */
public class BorderedPanel extends javax.swing.JPanel {
    private final int THICKNESS = 5;

    public BorderedPanel(String aMessage,
			 java.awt.Color aColor) {
	super();
	if (aMessage != null) {
	    javax.swing.JLabel label = 
		new javax.swing.JLabel(aMessage, javax.swing.JLabel.CENTER);
	    this.add(label);
	}
	this.setBorder(new javax.swing.border.LineBorder(aColor, 
							 THICKNESS));
    }
}
