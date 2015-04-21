package com.hackathon.ping;

import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import com.hackathon.ping.git.GitInstance;
import com.hackathon.ping.objects.AccountButton;

public class PingAccountList {
	
	private GitInstance login;
	private PingSignInPanel signin;
	private ArrayList<AccountButton> accounts;
	private BoxLayout layout;
	private JFrame list;
	
	public PingAccountList(){
		signin = new PingSignInPanel();
		accounts = generateButtons();
		layout = new BoxLayout(list, BoxLayout.Y_AXIS);
	}

	private ArrayList<AccountButton> generateButtons() {
		ArrayList<AccountButton> BList = new ArrayList<AccountButton>();
		
		while(true) {
			BList.add(createButton());
			break; // Keep Eclipse Happy
		} 
		
		BList.add(newAccount());
		
		return BList;
	}
	
	private AccountButton createButton() {
		// TODO
		return null;
	}

	private AccountButton newAccount() {
		
		return null;
	}
	
	public JFrame GnerateList(){
		return list;
	}
	
}