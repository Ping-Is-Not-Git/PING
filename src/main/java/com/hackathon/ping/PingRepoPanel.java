package com.hackathon.ping;

import java.awt.Color;
import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author river
 * This class builds the main page containers. Including the List of User accounts connected to this app, 
 * the list of repo's on the currently selected account, and the Details for a currently selected repo giving 
 * access for the user to go deeper into the account.
 * currently has placeholders for the elements. 
 */
public class PingRepoPanel extends JPanel { // Uses Group Layout
	GroupLayout layout = new GroupLayout(this);

	public PingRepoPanel() {
		this.setLayout(layout);
		buildPanel();
	}

	private void buildPanel() {
		Container account = createContainer("Account", Color.black); // holds the list of accounts
		Container repo = createContainer("Repos", Color.red); // holds the list of repo's for selected account
		Container wiki = createContainer("Wiki", Color.lightGray); // holds the details for a selected repo project

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup( // Layout Sequentially
				layout.createSequentialGroup()
				.addComponent(account)
				.addComponent(repo)
				.addComponent(wiki)
				);
		layout.setVerticalGroup( // Set up parallel group for vertical
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(account)
						.addComponent(repo)
						.addComponent(wiki))
				);
	}

	private Container createContainer(String string, Color color) {
		// Builds a container for the main page. ATM these are place holders 
		JPanel container = new JPanel();
		BoxLayout compLay = new BoxLayout(container, BoxLayout.Y_AXIS);

		container.setLayout(compLay);

		container.setBackground(color); // 2 Buttons to make this visible
		container.add(new JButton("TEST"));
		container.add(new JButton("test"));
		return container;
	}
}
