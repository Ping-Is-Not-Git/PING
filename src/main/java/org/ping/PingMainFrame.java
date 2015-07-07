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
	JPanel base, menu, wiki;
	PingButton winex, winmax, winmin;
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	int w = (int)(dim.width * .5 + 100);
	int h = (int)(dim.height * .5 + 100);
	int x = (dim.width-w)/2;
	int y = (dim.height-h)/2;
	boolean drag = false, max = false;
	static Point mousePoint;
	private final Color DEFAULT_COLOR = Color.WHITE;
	private final Color HOVER_COLOR   = Color.LIGHT_GRAY;
	private final Point maxLoc = new Point (0,0);
	private final Dimension maxSize = new Dimension(dim.width, dim.height);
	private Point miniLoc;
	private Dimension miniSize;

	public PingMainFrame() throws RuntimeException {
		super("Ping: PING is not Git");

		initComponents();
	}

	private void initComponents() throws RuntimeException {
		base = new JPanel();
		menu = new JPanel();
		base.setBackground(DEFAULT_COLOR);

		Dimension d = new Dimension(w, h); // Window Size

		this.setBackground(DEFAULT_COLOR);
		this.setUndecorated(true);
		this.setResizable(false);
		this.setMinimumSize(d); // done to give us a window at the desired size, Bad hack
		this.setSize(d);
		this.setLocation(x, y);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		BorderLayout winLayout = new BorderLayout();
		Container contentPane = this.getContentPane();

		contentPane.setLayout(winLayout);
		contentPane.add(buildWindowFrame(), BorderLayout.PAGE_START);
		contentPane.add(new PingRepoPanel(), BorderLayout.CENTER); // group layout

		addWindowDrag();
	}

	private void addWindowDrag() { // implement Window Drag listeners
		addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e){
				mousePoint = e.getPoint();
			}

			public void mouseReleased(MouseEvent e) {
				try{
					mousePoint = null;
					Point newlocation = e.getLocationOnScreen();
					miniLoc.move(newlocation.x - mousePoint.x, newlocation.y - mousePoint.y);
				} catch (Exception x) {

				}
			}

		});
		addMouseMotionListener(new MouseMotionListener() {
			/*
			 * Do nothing.
			 */
			public void mouseMoved(MouseEvent e) {
				try{}
				catch (Exception x) {
					System.out.println(x.getMessage());
				}
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				try {

					if(!max) { // Keeps window from moving when 
						Point newlocation = e.getLocationOnScreen();
						setLocation(newlocation.x - mousePoint.x, newlocation.y - mousePoint.y);
					} else {
						return;
					}
				} catch (Exception x) {
					System.out.println(x.getMessage());
				} 
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

	/**
	 * 	This constructs the upper left hand corner of the application. 
	 * @return The constructed upper left corner of the window
	 */
	private Component buildWinMenu() { // No layout needed
		JLabel logo = new JLabel (new ImageIcon("src/main/resources/logo/basic_title.png"));
		return logo;
	}

	/**
	 * This is the main Window Option area. 
	 * @return the container for the window option buttons
	 */
	private Container buildWinOpPanel() { 
		JPanel winOpArea = new JPanel();
		Dimension d = new Dimension(120, 40); // Button Area size
		Dimension b = new Dimension(40, 40); // Button Size
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
		exitButton.setPreferredSize(b);

		maximizeButton = new PingWinButton(icons[2], icons[3], "Maximize", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(max) { // Restore Windows small size
					try {
						setBounds(miniLoc.x, miniLoc.y, miniSize.width, miniSize.height);
						max = false;
						repaint();
					} catch (Exception x) {
						System.out.println(x.getMessage());
					}
				} else { // Maximize
					miniLoc = getLocation();
					miniSize = getSize();
					setBounds(maxLoc.x, maxLoc.y, maxSize.width, maxSize.height);
					max = true;
					repaint();
				}
			}
		}, rect);

		maximizeButton.setPreferredSize(b);

		minimizeButton = new PingWinButton(icons[1], icons[1], "Minimize", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setState(JFrame.ICONIFIED);
			}
		}, rect);

		minimizeButton.setPreferredSize(b);

		winOpArea.add(minimizeButton, Component.LEFT_ALIGNMENT);
		winOpArea.add(maximizeButton, Component.RIGHT_ALIGNMENT);
		winOpArea.add(exitButton, Component.CENTER_ALIGNMENT);

		winOpArea.setBackground(Color.white);
		return winOpArea;
	}
}
