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
import operation.InputException;

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
//		mainframe.getContentPane().add(mainMenu, BorderLayout.CENTER);
//		mainMenu.setLayout(new GridLayout(0, 2, 0, 0));
//
//		JButton btnSearch = new JButton("Search With AGUARE");
//		btnSearch.setOpaque(true);
//		btnSearch.setBackground(new Color(0, 191, 255));
//		btnSearch.setForeground(new Color(0, 0, 0));
//		btnSearch.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 32));
//		mainMenu.add(btnSearch);
//
//		JButton btnLogIn = new JButton("AGUARE Member");
//		btnLogIn.setBackground(new Color(95, 158, 160));
//		btnLogIn.setOpaque(true);
//		btnLogIn.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 32));
//		mainMenu.add(btnLogIn);
//
//		btnLogIn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				activateLoginPanel();
//				mainMenu.setVisible(false);
//
//			}
//		});
//
//		btnSearch.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				activateSearchPanel();
//				mainMenu.setVisible(false);
//			}
//		});
	}

	public void activateLoginPanel() {
		lp = new LoginPanel();
		lp.activateLoginPanel(this);
//		mainframe.getContentPane().add(loginpane, BorderLayout.CENTER);
//		// click the Log in button
//		loginpane.getBtnLogIn().addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if (!loginpane.getName().equals("") && !loginpane.getPassword().equals("")) {
//					if (UserOperation.hasUser(loginpane.getName())) {
//						if (UserOperation.checkPassword(loginpane.getName(), loginpane.getPassword())) {
//							for (int i = 0; i < UserList.userList.size(); i++) {
//								if (loginpane.getName().equals(UserList.userList.get(i).getName())) {
//									UserList.userList.get(i).setLoggin(true);
//								} else {
//									UserList.userList.get(i).setLoggin(false);
//								}
//							}
//							activateUserMenuPanel();
//							loginpane.setVisible(false);
//						} else {
//							new PopFrame("Wrong password!");
//						}
//					} else {
//						new PopFrame("U do not exist!");
//					}
//				} else {
//					new PopFrame("Fill all the blanks dude!");
//				}
//			}
//		});
//		// click the Back to Menu button
//		loginpane.getBtnBackToMenu().addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				initialize();
//				loginpane.setVisible(false);
//			}
//		});
//		loginpane.setVisible(true);
	}

	public void activateSearchPanel() {
		sp = new SearchPanel();
		sp.activateSearchPanel(this);
//		SearchPanel sp = new SearchPanel();
//		mainframe.getContentPane().add(sp, BorderLayout.CENTER);
//		sp.btnBackToMenu.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if (UserOperation.anyoneLoggedin()) {
//					activateUserMenuPanel();
//				} else {
//					initialize();
//				}
//				sp.setVisible(false);
//			}
//		});
//		sp.btnBook.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if (sp.hotelId != -1) {
//					try {
//						sp.getDate();
//						ArrayList<Integer> valid = Operation.vacancyHotels(sp.getDate(), sp.numOfNight, sp.numOfPeople,
//								sp.numOfRooms, sp.city);
//						boolean hasRoom = false;
//						for (int i = 0; i < valid.size(); i++) {
//							if (valid.get(i) == sp.hotelId) {
//								hasRoom = true;
//							}
//						}
//						if (hasRoom) {
//
//							activateToBookPanel(new BookDeposit(sp.city, sp.numOfPeople, sp.numOfRooms, sp.getDate(),
//									sp.numOfNight, sp.hotelId));
//							sp.setVisible(false);
//						} else {
//							new PopFrame("There's NO room");
//						}
//					} catch (InputException e1) {
//						new PopFrame(e1.getMessage());
//					}
//				} else {
//					new PopFrame("Make your choice = = ");
//				}
//			}
//		});
//		sp.setVisible(true);
	}

	public void activateUserMenuPanel() {
		um = new UserMenuPanel();
		um.activateUserMenuPanel(this);
//		mainframe.getContentPane().add(um, BorderLayout.CENTER);
//
//		um.btnRoom.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				activateSearchPanel();
//				um.setVisible(false);
//			}
//		});
//
//		um.btnMyOrder.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				activateMyOrderPanel();
//				um.setVisible(false);
//			}
//		});
//		um.btnUserOptions.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				activateUserOptionsPanel();
//				um.setVisible(false);
//			}
//		});
//
//		um.setVisible(true);
	}

	public void activateToBookPanel(BookDeposit bd) {
		tbp = new ToBookPanel();
		tbp.activateToBookPanel(this, bd);
//		mainframe.getContentPane().add(tbp, BorderLayout.CENTER);
//		tbp.textArea.setText(bd.toString());
//		tbp.getBtnBackToSearch().addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				activateSearchPanel();
//				tbp.setVisible(false);
//			}
//		});
//		tbp.getBtnConfirm().addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if (UserOperation.anyoneLoggedin()) {
//					String bookId = Operation.reserve(UserOperation.whoIsLoggedin(), bd.getHotelId(),
//							bd.getCheckInDate(), bd.getNight(), bd.numOfPeopleAndRoom(bd.getPeople(), bd.getRoom())[0],
//							bd.numOfPeopleAndRoom(bd.getPeople(), bd.getRoom())[1],
//							bd.numOfPeopleAndRoom(bd.getPeople(), bd.getRoom())[2]);
//					new PopFrame("BOOK SUCCESS!\nYour book ID is " + bookId);
//					activateUserMenuPanel();
//					tbp.setVisible(false);
//				} else {
//					new PopFrame("Log in FIRST !");
//					LoginPanel loginpane = new LoginPanel();
//					mainframe.getContentPane().add(loginpane, BorderLayout.CENTER);
//					// click the Log in button
//					loginpane.getBtnLogIn().addActionListener(new ActionListener() {
//						public void actionPerformed(ActionEvent e) {
//							if (!loginpane.getName().equals("") && !loginpane.getPassword().equals("")) {
//								if (UserOperation.hasUser(loginpane.getName())) {
//									if (UserOperation.checkPassword(loginpane.getName(), loginpane.getPassword())) {
//										for (int i = 0; i < UserList.userList.size(); i++) {
//											if (loginpane.getName().equals(UserList.userList.get(i).getName())) {
//												UserList.userList.get(i).setLoggin(true);
//											} else {
//												UserList.userList.get(i).setLoggin(false);
//											}
//										}
//										tbp.setVisible(true);
//										loginpane.setVisible(false);
//									} else {
//										new PopFrame("Wrong password!");
//									}
//								} else {
//									new PopFrame("U do not exist!");
//								}
//							} else {
//								new PopFrame("Fill all the blanks dude!");
//							}
//						}
//					});
//					// click the Back to Menu button
//					loginpane.getBtnBackToMenu().addActionListener(new ActionListener() {
//						public void actionPerformed(ActionEvent e) {
//							tbp.setVisible(true);
//							loginpane.setVisible(false);
//						}
//					});
//					loginpane.setVisible(true);
//					tbp.setVisible(false);
//				}
//
//			}
//		});
//		tbp.setVisible(true);
	}

	public void activateMyOrderPanel() {
		mop = new MyOrderPanel();
		mop.activateMyOrderPanel(this);
//		mainframe.getContentPane().add(mop, BorderLayout.CENTER);
//		mop.btnBack.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				activateUserMenuPanel();
//				mop.setVisible(false);
//			}
//		});
//		mop.btnEditOrder.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				activateEditOrderPanel();
//				mop.setVisible(false);
//			}
//		});
	}

	public void activateEditOrderPanel() {
		eop = new EditOrderPanel();
		eop.activateEditOrderPanel(this);
//		mainframe.getContentPane().add(eop, BorderLayout.CENTER);
//
//		eop.btnBackToMenu.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				activateUserMenuPanel();
//				eop.setVisible(false);
//			}
//		});
//
//		eop.btnRoomNumber.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				eop.mor.setVisible(true);
//				eop.mod.setVisible(false);
//				eop.del.setVisible(false);
//				eop.defaultPanel.setVisible(false);
//			}
//		});
//
//		eop.btnDate.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				eop.mor.setVisible(false);
//				eop.mod.setVisible(true);
//				eop.del.setVisible(false);
//				eop.defaultPanel.setVisible(false);
//			}
//		});
//
//		eop.btnDelete.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				eop.mor.setVisible(false);
//				eop.mod.setVisible(false);
//				eop.del.setVisible(true);
//				eop.defaultPanel.setVisible(false);
//			}
//		});
//
//		eop.mor.btnConfirm.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				int op = Operation.changeReservation(eop.mor.bookId, eop.mor.single, eop.mor.dual, eop.mor.quad);
//				if (op == 0) {
//					new PopFrame("Successfully Edited !");
//					activateMyOrderPanel();
//					eop.setVisible(false);
//				} else if (op == 1) {
//					new PopFrame("Room never enough !");
//				} else if (op == 2) {
//					new PopFrame("Room not enough !");
//				}
//			}
//		});
//
//		eop.mod.btnConfirm.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				int op = Operation.changeReservation(eop.mod.bookId, eop.mod.getCheckInDate(),
//						eop.mod.getCheckOutDate());
//				if (op == 0) {
//					new PopFrame("Successfully Edited !");
//					activateMyOrderPanel();
//					eop.setVisible(false);
//				} else if (op == 1) {
//					new PopFrame("Room never enough !");
//				} else if (op == 2) {
//					new PopFrame("Room not enough !");
//				}
//			}
//		});
//
//		eop.del.btnConfirm.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				int op = Operation.changeReservation(eop.del.bookId);
//				if (op == 0) {
//					new PopFrame("Successfully deleted !");
//					activateMyOrderPanel();
//					eop.setVisible(false);
//				} else {
//					new PopFrame("Error ! Something is wrong !");
//				}
//			}
//		});
	}

	public void activateUserOptionsPanel() {
		uop = new UserOptionsPanel();
		uop.activateUserOptionsPanel(this);
//		mainframe.getContentPane().add(uop, BorderLayout.CENTER);
//
//		uop.btnBackToMenu.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				activateUserMenuPanel();
//				uop.setVisible(false);
//			}
//		});
//
//		uop.btnLogOut.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				UserOperation.everyOneloggedOut();
//				initialize();
//				uop.setVisible(false);
//			}
//		});
//
//		uop.btnChangePW.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				new ChangePWFrame().setVisible(true);
//			}
//		});
	}

	public Container getContentPane() {
		return mainframe.getContentPane();
	}
}
