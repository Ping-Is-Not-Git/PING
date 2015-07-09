package org.ping.panels;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author river
 * This class builds the main page containers. Including the List of User accounts connected to this app, 
 * the list of repo's on the currently selected account, and the Details for a currently selected repo giving 
 * access for the user to go deeper into the account.
 * currently has place holders for the elements. 
 */
@SuppressWarnings("unused")
public class PingRepoPanel extends JPanel { 

	/**
	 *  Keep Eclipse Happy
	 */
	private static final long serialVersionUID = 1L;

	// Define Globals
	private FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
	private PingAccountList accounts;

	// Constructor
	public PingRepoPanel() {
		this.setLayout(layout);
		buildPanel();
	}

	/**
	 * 
	 */
	private void buildPanel() {

		//this.setMinimumSize(new Dimension(500, 500));
		this.setBackground(Color.white);
		
		accounts = new PingAccountList(layout);
		this.add(accounts);
	}
}
