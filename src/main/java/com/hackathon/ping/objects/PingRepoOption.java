package com.hackathon.ping.objects;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class PingRepoOption extends PingButton {
	
	private boolean on;
	
	public PingRepoOption(String text, ImageIcon imageIcon) {
		super(text, imageIcon);
		// TODO Auto-generated constructor stub
		on = false;
	}
	
	/**
	 * @return the current on state
	 */
	public boolean setOn(){
		this.setBG();
		return on = on ? false : true;
	}
	
	private void setBG() {
		if(on) this.setBackground(Color.white);
		else this.setBackground(Color.lightGray);
	}

	public boolean isOn() {
		return on;
	}

	@Override
	protected void doMousePressed() {
		// TODO Auto-generated method stub
		this.setOn();
	}
}
