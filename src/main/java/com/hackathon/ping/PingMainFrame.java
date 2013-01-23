/**
 * This class builds the GUI for the PING application window
 * Specifically the Frame that holds it
 */

package com.hackathon.ping;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.hackathon.ping.git.GitInstance;
import com.hackathon.ping.objects.PingButton;
import com.hackathon.ping.objects.PingWinButton;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author river226
 */

@SuppressWarnings("serial")
public class PingMainFrame extends JFrame {
	JPanel base, menu, wiki;
	PingButton winex, winmax, winmin;
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	int w = (int)(dim.width * .5 + 100);
	int h = (int)(dim.height * .5 + 100);
	int x = (dim.width-w)/2;
	int y = (dim.height-h)/2;
	boolean drag = false;
	static Point mousePoint;
	private final Color DEFAULT_COLOR = Color.WHITE;
	private final Color HOVER_COLOR   = Color.LIGHT_GRAY;

	public PingMainFrame() throws RuntimeException {
		super("Ping: PING is not Git");

		initComponents();
	}

	private void initComponents() throws RuntimeException {
		base = new JPanel();
		menu = new JPanel();
		base.setBackground(Color.WHITE);
		Dimension d = new Dimension(w, h);

		setUndecorated(true);
		setResizable(false);
		setMinimumSize(d); // done to give us a window at the desired size, Bad hack
		setSize(d);
		setLocation(x, y);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		BorderLayout winLayout = new BorderLayout();
		Container contentPane = this.getContentPane();
		
		contentPane.setLayout(winLayout);
		contentPane.add(buildWindowFrame(), BorderLayout.PAGE_START);
		contentPane.add(new PingRepoPanel(), BorderLayout.PAGE_END); // group layout

		addWindowDrag();
	}

	private void addWindowDrag() { // implement Window Drag listeners
		addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e){
				mousePoint = e.getPoint();
			}

			public void mouseReleased(MouseEvent e) {
				mousePoint = null;
			}
			
		});
		addMouseMotionListener(new MouseMotionListener() {
			/*
			 * Do nothing.
			 */
			public void mouseMoved(MouseEvent e) {}

			@Override
			public void mouseDragged(MouseEvent e) {
				Point newlocation = e.getLocationOnScreen();

				setLocation(newlocation.x - mousePoint.x, newlocation.y - mousePoint.y);
			}
		});
	}

	//TODO: Fix ugly hard code
	private Container buildWindowFrame() throws RuntimeException { // May need to switch to grid layout
		JPanel top = new JPanel();
		BorderLayout layout = new BorderLayout();
		top.setLayout(layout);
		top.add(buildWinMenu(), BorderLayout.LINE_START);
		top.add(buildWinOpPanel(), BorderLayout.LINE_END);
		return top; // build entire window top panel. 
	}

	private Component buildWinMenu() { // No layout needed
		JMenu men = new JMenu(); // TODO
		return men;
	}

	private Container buildWinOpPanel() { 
		JPanel winOpArea = new JPanel();
		Dimension d = new Dimension(120, 40);
		winOpArea.setPreferredSize(d);
		BoxLayout layout = new BoxLayout(winOpArea, BoxLayout.X_AXIS);
		winOpArea.setLayout(layout);
		Rectangle rect = new Rectangle();
		rect.setSize(10, 10); // Set the button Size
		PingButton exitButton = null;
		PingButton maximizeButton = null;
		PingButton minimizeButton = null;
		final String[] icons = {"src/main/resources/icons/exit.png", "src/main/resources/icons/min.png", "src/main/resources/icons/max.png", "src/main/resources/icons/restore.png"};
			exitButton = new PingWinButton(icons[0], icons[0], "Exit", new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Do you wish to close PING?", "Close Window", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon(icons[0])))
						System.exit(0);
						//throw new RuntimeException("windows close");
				}
			}, rect);

			maximizeButton = new PingWinButton(icons[2], icons[3], "Maximize", new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setSize(dim.width, dim.height);
				}
			}, rect);

			minimizeButton = new PingWinButton(icons[1], icons[1], "Minimize", new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setState(JFrame.ICONIFIED);
				}
			}, rect);

			winOpArea.add(minimizeButton, Component.LEFT_ALIGNMENT);
			winOpArea.add(maximizeButton, Component.RIGHT_ALIGNMENT);
			winOpArea.add(exitButton, Component.CENTER_ALIGNMENT);

			winOpArea.setBackground(Color.white);
		return winOpArea;
	}
}
