package gui;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import bookAndUser.User;
import bookAndUser.UserList;
import bookAndUser.UserOperation;
import gui.landlord.LandlordMenuPanel;
import gui.landlord.LandlordOrderPanel;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Font;

public class MainFrame {

	private JFrame mainframe;
	private MainMenu mainMenu;
	private SearchPanel sp;
	private LoginPanel lp;
	private UserMenuPanel um;
	private ToBookPanel tbp;
	private MyOrderPanel mop;
	private EditOrderPanel eop;
	private UserOptionsPanel uop;
	private LandlordMenuPanel lmp;
	private LandlordOrderPanel lop;

	Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

	/**
	 * Launch the Hotel GUI application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.mainframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		mainframe = new JFrame("AGUARE");
		mainframe.setSize(1080, 720);
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.getContentPane().setLayout(new BorderLayout(0, 0));
		initialize();
	}

	public void setVisibleOfMainFrameTrue() {
		mainframe.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		mainMenu = new MainMenu();
		mainMenu.activateMainMenu(this);
	}

	public void activateLoginPanel() {
		lp = new LoginPanel();
		lp.activateLoginPanel(this);
	}

	public void activateSearchPanel() {
		sp = new SearchPanel();
		sp.activateSearchPanel(this);
	}

	public void activateUserMenuPanel() {
		um = new UserMenuPanel();
		um.activateUserMenuPanel(this);
	}

	public void activateToBookPanel(BookDeposit bd) {
		tbp = new ToBookPanel();
		tbp.activateToBookPanel(this, bd);
	}

	public void activateMyOrderPanel() {
		mop = new MyOrderPanel();
		mop.activateMyOrderPanel(this);
	}

	public void activateEditOrderPanel() {
		eop = new EditOrderPanel();
		eop.activateEditOrderPanel(this);
	}

	public void activateUserOptionsPanel() {
		uop = new UserOptionsPanel();
		uop.activateUserOptionsPanel(this);
	}

	public void activateLandlordMenuPanel() {
		lmp = new LandlordMenuPanel();
		lmp.activateLandlordMenuPanel(this);
	}
	
	public void activateLandlordOrderPanel() {
		lop = new LandlordOrderPanel();
		lop.activateLandlordOrderPanel(this);
	}

	public Container getContentPane() {
		return mainframe.getContentPane();
	}
}
