/**
 * This class builds the GUI for the PING application window
 * Specifically the Frame that holds it
 */

package org.ping;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.ping.git.GitInstance;
import org.ping.objects.PingButton;
import org.ping.objects.PingWinButton;
import org.ping.panels.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author river226
 */

@SuppressWarnings({ "serial", "unused" })
public class PingMainFrame extends JFrame {
	// Define 
	JPanel base, menu, wiki;
	PingButton winex, winmax, winmin;
	private final Color DEFAULT_COLOR = Color.WHITE;
	private final Color HOVER_COLOR   = Color.LIGHT_GRAY;
	
	// Window Dimensions/Position
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	int w = (int)(dim.width * .5 + 100);
	int h = (int)(dim.height * .5 + 100);
	int x = (dim.width-w)/2;
	int y = (dim.height-h)/2;
	
	// Maximize position
	private final Point maxLoc = new Point (0,0);
	private final Dimension maxSize = new Dimension(dim.width, dim.height);
	
	// Normal position
	private Point miniLoc;
	private Dimension miniSize;
	
	// Window Functions
	boolean drag = false, max = false;
	static Point mousePoint;
	

	public PingMainFrame() throws RuntimeException {
		super("Ping: PING is not Git");
		initComponents();
	}

	/**
	 * Defining the Frame
	 * @throws RuntimeException
	 */
	private void initComponents() throws RuntimeException {
		base = new JPanel();
		menu = new JPanel();
		

		// Define Window
		base.setBackground(DEFAULT_COLOR);
		Dimension d = new Dimension(w, h); // Window Size
		this.setBackground(DEFAULT_COLOR);
		this.setUndecorated(true);
		this.setResizable(false);
		this.setMinimumSize(d); // done to give us a window at the desired size, Bad hack
		this.setSize(d);
		this.setLocation(x, y);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Define Layout
		BorderLayout winLayout = new BorderLayout();
		Container contentPane = this.getContentPane();
		
		// Build Window
		contentPane.setLayout(winLayout);
		contentPane.add(buildWindowFrame(), BorderLayout.PAGE_START);
		contentPane.add(new PingRepoPanel(), BorderLayout.CENTER); // group layout

		// Make Window Drag-able 
		addWindowDrag();
	}

	/**
	 * Implement Window Drag listeners
	 */
	private void addWindowDrag() { 
		addMouseListener(new MouseAdapter() {

			// Start Move
			public void mousePressed(MouseEvent e){
				mousePoint = e.getPoint(); // Starting Point
			}

			// End Move
			public void mouseReleased(MouseEvent e) {
				try{
					mousePoint = null;
					Point newlocation = e.getLocationOnScreen(); // Save Location
					miniLoc.move(newlocation.x - mousePoint.x, newlocation.y - mousePoint.y);
				} catch (Exception x) { // TODO: Log Issue

				}
			}

		});
		addMouseMotionListener(new MouseMotionListener() {
			/*
			 * Do nothing.
			 */
			public void mouseMoved(MouseEvent e) {
				try{}
				catch (Exception x) { // TODO: Log Issue
					System.out.println(x.getMessage());
				}
			}

			@Override // Update Position
			public void mouseDragged(MouseEvent e) {
				try {

					if(!max) { // Keeps window from moving when Maximized
						Point newlocation = e.getLocationOnScreen(); // Update location to match new location
						setLocation(newlocation.x - mousePoint.x, newlocation.y - mousePoint.y);
					} else {
						return;
					}
				} catch (Exception x) { // TODO: Log issue
					System.out.println(x.getMessage());
				} 
			}
		});
	}

	// Build Window Title Bar
	
	/**
	 * Build the window title bar
	 * @return Completed Window Title Bar
	 * @throws RuntimeException
	 */
	private Container buildWindowFrame() throws RuntimeException {
		JPanel top = new JPanel();
		BorderLayout layout = new BorderLayout();
		top.setLayout(layout);
		top.add(buildWinMenu(), BorderLayout.LINE_START);
		top.add(buildWinOpPanel(), BorderLayout.LINE_END);
		return top; // build entire window top panel. 
	}

	// Define Menu Area
	
	/**
	 * TODO: Complete
	 * 	This constructs the upper left hand corner of the application. 
	 * @return The constructed upper left corner of the window
	 */
	private Component buildWinMenu() { // No layout needed
		JLabel logo = new JLabel (new ImageIcon("src/main/resources/logo/basic_title.png"));
		return logo;
	}

	// Define Window Option Area
	
	/**
	 * This is the Window Option area, for Customized: 
	 * Minimized Maximized Window Close buttons. 
	 * @return the container for the window option buttons
	 */
	private Container buildWinOpPanel() { 
		JPanel winOpArea = new JPanel();

		// Define Area
		Dimension b = new Dimension(40, 40); // Button Size
		winOpArea.setPreferredSize(new Dimension(120, 40)); // Button Area size
		BoxLayout layout = new BoxLayout(winOpArea, BoxLayout.X_AXIS);
		winOpArea.setLayout(layout);
		
		// Define Button
		Rectangle rect = new Rectangle();
		rect.setSize(10, 10); // Set the button Size
		
		// Define Buttons: 		
		PingButton exitButton = null;
		PingButton maximizeButton = null;
		PingButton minimizeButton = null;
		
		// Grab Icons
		final String[] icons = {"src/main/resources/icons/exit.png", "src/main/resources/icons/min.png", "src/main/resources/icons/max.png", "src/main/resources/icons/restore.png"};
		
		// Build Buttons
		exitButton = buildExitButton(rect, icons);
		exitButton.setPreferredSize(b);
		maximizeButton = buildMaxButton(rect, icons);
		maximizeButton.setPreferredSize(b);
		minimizeButton = buildMinButton(rect, icons);
		minimizeButton.setPreferredSize(b);

		// Build Window Area
		winOpArea.add(minimizeButton, Component.LEFT_ALIGNMENT);
		winOpArea.add(maximizeButton, Component.RIGHT_ALIGNMENT);
		winOpArea.add(exitButton, Component.CENTER_ALIGNMENT);
		winOpArea.setBackground(Color.white);
		
		return winOpArea;
	}

	/**
	 * Handles the building of the Exit button
	 * @param rect Defined Button
	 * @param icons Defined Icon for this button
	 * @return Constructed Exit Button
	 */
	private PingButton buildExitButton(Rectangle rect, final String[] icons) {
		PingButton exitButton;
		
		// Build Button
		exitButton = new PingWinButton(icons[0], icons[0], "Exit", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Confirm that the user intends to close
				if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Do you wish to close PING?", "Close Window", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon(icons[0])))
					System.exit(0); // Close Window
			}
		}, rect);
		
		return exitButton;
	}

	/**
	 * Handles the building of the Minimize button
	 * @param rect Defined Button
	 * @param icons Defined Icon for this button
	 * @return Constructed Minimize Button
	 */
	private PingButton buildMinButton(Rectangle rect, final String[] icons) {
		PingButton minimizeButton;
		
		// Build Button
		minimizeButton = new PingWinButton(icons[1], icons[1], "Minimize", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setState(JFrame.ICONIFIED);
			}
		}, rect);
		
		return minimizeButton;
	}

	/**
	 * Handles the building of the Maximize button
	 * @param rect Defined Button
	 * @param icons Defined Icon for this button
	 * @return Constructed Maximize Button
	 */
	private PingButton buildMaxButton(Rectangle rect, final String[] icons) {
		PingButton maximizeButton;
		
		// Build Button
		maximizeButton = new PingWinButton(icons[2], icons[3], "Maximize", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(max) { // Restore Windows small size
					try {
						setBounds(miniLoc.x, miniLoc.y, miniSize.width, miniSize.height);
						max = false;
						repaint();
					} catch (Exception x) { // TODO: Log Issue
						System.out.println(x.getMessage());
					}
				} else { // Maximize
					miniLoc = getLocation(); // Save Location
					miniSize = getSize(); // Save Size
					setBounds(maxLoc.x, maxLoc.y, maxSize.width, maxSize.height);
					max = true;
					repaint();
				}
			}
		}, rect);
		
		return maximizeButton;
	}
}
