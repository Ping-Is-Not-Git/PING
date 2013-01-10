package com.hackathon.ping;

import java.awt.Color;
import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PingRepoPanel extends JPanel { // Uses Group Layout
	GroupLayout layout = new GroupLayout(this);

	public PingRepoPanel() {
		this.setLayout(layout);
		buildPanel();
	}

	private void buildPanel() {
		Container account = createContainer("Account", Color.black);
		Container repo = createContainer("Repos", Color.red);
		Container wiki = createContainer("Wiki", Color.lightGray);

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addComponent(account)
				.addComponent(repo)
				.addComponent(wiki)
				);
	}

	private Container createContainer(String string, Color color) {
		JPanel container = new JPanel();
		BoxLayout compLay = new BoxLayout(container, BoxLayout.Y_AXIS);

		container.setLayout(compLay);

		container.setBackground(color);
		container.add(new JButton("TEST"));
		container.add(new JButton("test"));
		return container;
	}
}
