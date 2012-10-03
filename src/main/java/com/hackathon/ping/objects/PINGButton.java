package main.java.com.hackathon.ping.objects;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PINGButton extends JButton {
	
	private final Color defaultColor, hoverColor;
	private int option; // 1 - exit, 2 - max, 3 - min
	// other options
	
	/**
	 * Keeping Eclipse Happy
	 */
	private static final long serialVersionUID = 1L;

	public PINGButton(String text, int op) {
		super(text);
		
		defaultColor = Color.WHITE;
		hoverColor = Color.CYAN;
		this.setBackground(defaultColor);
		addMouse();
		this.setBorderPainted(false);
	}
	
	public PINGButton(String text, Color main, Color hover, int op) {
		super(text);
		
		defaultColor = main;
		hoverColor = hover;
		super.setBackground(defaultColor);
		addMouse();
		this.setBorderPainted(false);
	}
	
	private void addMouse() {
		super.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent a) { /* NOTHING */}

			@Override
			public void mouseEntered(MouseEvent a) {
				doMouseEnt();
			}

			@Override
			public void mouseExited(MouseEvent a) {
				doMouseEx();
			}

			@Override
			public void mousePressed(MouseEvent arg0) {/* NOTHING */}

			@Override
			public void mouseReleased(MouseEvent arg0) {/* NOTHING */}
			
		});
	}
	
	public void doMouseEx() {
		this.setBackground(defaultColor);
	}
	
	public void doMouseEnt() {
		this.setBackground(hoverColor);
	}
}