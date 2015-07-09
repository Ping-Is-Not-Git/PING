/**
 * 
 */
package org.ping;

import javax.swing.SwingUtilities;

import org.ping.git.GitInstance;


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


	/**
	 * @throws RuntimeException
	 */
	public static void createAndShowGUI() throws RuntimeException {
		PingMainFrame mainFrame;
		mainFrame = new PingMainFrame();
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
}
