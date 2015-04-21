/**
 * 
 */
package com.hackathon.ping;

import javax.swing.SwingUtilities;

import com.hackathon.ping.git.GitInstance;

/**
 * @author river226
 */
@SuppressWarnings("unused")
public class Ping {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Runnable runCreateAndShowGUI = new Runnable() {
		
			
		public void run() {
			createAndShowGUI();
			}
		};
		SwingUtilities.invokeLater(runCreateAndShowGUI);
	} 


	public static void createAndShowGUI() throws RuntimeException {
		PingMainFrame mainFrame;
		mainFrame = new PingMainFrame();
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
}
