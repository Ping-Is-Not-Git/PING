package org.ping.panels;

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import org.ping.git.GitInstance;
import org.ping.objects.AccountButton;

@SuppressWarnings("unused")
public class PingAccountList extends Container{
	
	/**
	 * Keep Eclipse Happy 
	 */
	private static final long serialVersionUID = 1L;
	
	private GitInstance login;
	private JTabbedPane accounts;
	private PingSignInPanel signin;
	private ArrayList<PingRepoList> repos;

	public PingAccountList(FlowLayout f) {
		this.setLayout(f);
		signin = new PingSignInPanel();
		buildAccountList();
	}

	private void buildAccountList() {
		// TODO Auto-generated method stub
		accounts = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT);
		accounts.add("New Account", signin);
		//accounts.add(new JLabel("Test"));
		this.add(accounts);
	}
}