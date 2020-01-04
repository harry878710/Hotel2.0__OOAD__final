package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;

import gui.landlord.LandlordAddHotelPanel;
import gui.landlord.LandlordHotelsPanel;
import gui.landlord.LandlordMenuPanel;
import gui.landlord.LandlordOrderPanel;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

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
	private LandlordHotelsPanel lhp;
	private LandlordAddHotelPanel lahp;
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

	public void activateLandlordHotelsPanel() {
		lhp = new LandlordHotelsPanel();
		lhp.activateLandlordHotelsPanel(this);
	}

	public void activateLandlordAddHotelPanel() {
		lahp = new LandlordAddHotelPanel();
		lahp.activateLandlordAddHotelPanel(this);
	}

	public Container getContentPane() {
		return mainframe.getContentPane();
	}
}
