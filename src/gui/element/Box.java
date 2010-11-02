package gui.element;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Box extends JPanel {

	public Box(Color color) {
		this.setBackground(color);
		this.setPreferredSize(new Dimension(300,300));
	}	
}