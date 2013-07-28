package com.hackathon.ping.objects;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public abstract class PingButton extends JButton {
	protected final Color defaultColor = new Color(238,238,238);
	protected final Color hoverColor = Color.LIGHT_GRAY;
	
	public PingButton(String text, ImageIcon imageIcon) {
		super(text, imageIcon);
		
		setBackground(defaultColor);
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent a) {
				doMouseEnt();
			}

			@Override
			public void mouseExited(MouseEvent a) {
				doMouseEx();
			}
			
			@Override
			public void mousePressed(MouseEvent a) {
				doMousePressed();
			}
			
		});
		this.setBorderPainted(false);
	}
	
	protected void doMouseEx() {
		this.setBackground(defaultColor);
	}
	
	protected void doMouseEnt() {
		this.setBackground(hoverColor);
	}

	protected abstract void doMousePressed();
}