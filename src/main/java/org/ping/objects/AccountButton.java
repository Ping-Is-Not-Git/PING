package org.ping.objects;

import java.awt.Image;
import javax.swing.JButton;

import org.ping.git.GitInstance;


public class AccountButton extends JButton {

	/**
	 * Keep Eclipse Happy
	 */
	private static final long serialVersionUID = 1L;

	
	private String hub, alias;
	private Image pandora;
	private GitInstance octocat;
	
	public AccountButton(GitInstance git) {
		this.hub = "Add New Account";
		this.alias = "";
		this.pandora = null; // TODO Change to image for adding account
		this.octocat = git;
		GenerateButton();
	}
	
	public AccountButton(GitInstance git, String site) {
		this.hub = site;
		this.alias = "";
		this.pandora = null;
		this.octocat = git;
		GenerateButton();
	}
	
	public AccountButton(GitInstance git, String site, String name) {
		this.hub = site;
		this.alias = name;
		this.pandora = null;
		this.octocat = git;
		GenerateButton();
	}
	
	public AccountButton(GitInstance git, String site, String name, Image avatar) {
		this.hub = site;
		this.alias = name;
		this.pandora = avatar;
		this.octocat = git;
		GenerateButton();
	}
	
	public void GenerateButton() {
		
	}
}
