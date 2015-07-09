package org.ping.objects;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.Rectangle;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class PingWinButton extends PingButton {
	protected Icon mainImage;
	Icon secondImage;
	
	public PingWinButton(String icon, String second, String text, ActionListener a, Rectangle r) {
		super("", new ImageIcon(icon));
		this.addActionListener(a);
		this.setBounds(r);
		this.setFocusable(false); // Disable Tab Navigation
		secondImage = new ImageIcon(second);
		mainImage = super.getIcon();
	}
	


	protected void doMousePressed() {
		this.setIcon(this.getIcon().equals(mainImage) ? secondImage : mainImage);
	}
}
