package org.ping.panels;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import org.ping.git.GitInstance;
import org.ping.objects.*;

@SuppressWarnings("unused")
public class PingSignInPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	// TODO: Add Drop Down menu to change account location
	// Allow for custom locations

	private JLabel user, password;
	private JTextField usern;
	private JPasswordField pass;
	private SignInButton signin;
	private ArrayList<GitInstance> git;

	public PingSignInPanel() {
		user = new JLabel("Login: ");
		usern = new JTextField();
		password = new JLabel("Password: ");
		pass = new JPasswordField();
		signin = new SignInButton("Add Account");

		buildLogin();
	}

	private void buildLogin() {
		// TODO
	}

	private void errorBlank() {
		// TODO
	}

	private void errorInvalid() {
		// TODO
	}
	
	private void error() {
		// TODO
	}

	public void attemptLogin(String u, char[] p) {
		try {
			GitInstance g = GitInstance.getInstance();	
			g.login(u, p);
			git.add(g);
			// Implement Watcher to add Account to List, and build Repo
		} catch (Exception e) {
			errorInvalid();
		}

		// Clean out the password securely
		for(int i = 0; i < p.length; i++) 
			p[i] = 0;
		p = null;
	}

	private class SignInButton extends PingButton {

		private static final long serialVersionUID = 1L;

		public SignInButton(String s) {
			super(s);
		}

		@Override
		protected void doMousePressed() {
			if(usern.getText() != null && pass.getPassword() != null) {
				attemptLogin(usern.getText(), pass.getPassword());
			} else errorBlank();
		}
	}
}
