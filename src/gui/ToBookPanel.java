package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import bookAndUser.UserList;
import bookAndUser.UserOperation;
import operation.SearchAndBook;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToBookPanel extends JPanel {
	JButton btnBackToSearch;
	JButton btnConfirm;
	JTextArea textArea;

	public JButton getBtnConfirm() {
		return btnConfirm;
	}

	public JButton getBtnBackToSearch() {
		return btnBackToSearch;
	}

	/**
	 * Create the panel.
	 */
	public ToBookPanel() {
		setSize(1080, 720);
		setBackground(new Color(135, 206, 235));
		setLayout(null);

		textArea = new JTextArea();
		textArea.setFont(new Font("�L�n������", Font.PLAIN, 24));
		textArea.setBackground(new Color(240, 248, 255));
		textArea.setEditable(false);
		textArea.setBounds(180, 152, 720, 403);
		add(textArea);

		btnBackToSearch = new JButton("BACK TO SEARCH");
		btnBackToSearch.setFont(new Font("Agency FB", Font.PLAIN, 36));
		btnBackToSearch.setBackground(new Color(153, 204, 255));
		btnBackToSearch.setBounds(14, 13, 250, 70);
		add(btnBackToSearch);

		btnConfirm = new JButton("CONFIRM");
		btnConfirm.setFont(new Font("Agency FB", Font.PLAIN, 36));
		btnConfirm.setBackground(new Color(153, 204, 255));
		btnConfirm.setBounds(467, 568, 148, 66);
		add(btnConfirm);

		JLabel titleLabel = new JLabel("Confirm the details of your  order");
		titleLabel.setForeground(new Color(0, 0, 139));
		titleLabel.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 32));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(14, 69, 1052, 70);
		add(titleLabel);

	}

	public void activateToBookPanel(MainFrame mainframe, BookDeposit bd) {
		mainframe.getContentPane().add(this, BorderLayout.CENTER);
		textArea.setText(bd.toString());
		getBtnBackToSearch().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainframe.activateSearchPanel();
				setVisible(false);
			}
		});
		getBtnConfirm().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (UserOperation.anyoneLoggedin()) {
					String bookId = new SearchAndBook().commitBook(bd.getCheckInDate(), bd.getNight(), bd.getHotelId(),
							UserOperation.whoIsLoggedin(), bd.getRoomCombination());
					new PopFrame("BOOK SUCCESS!\nYour book ID is " + bookId);
					mainframe.activateUserMenuPanel();
					setVisible(false);
				} else {
					new PopFrame("Log in FIRST !");
					LoginPanel loginpane = new LoginPanel();
					mainframe.getContentPane().add(loginpane, BorderLayout.CENTER);
					// click the Log in button
					loginpane.getBtnLogIn().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (!loginpane.getName().equals("") && !loginpane.getPassword().equals("")) {
								if (UserOperation.hasUser(loginpane.getName())) {
									if (UserOperation.checkPassword(loginpane.getName(), loginpane.getPassword())) {
										for (int i = 0; i < UserList.userList.size(); i++) {
											if (loginpane.getName().equals(UserList.userList.get(i).getName())) {
												UserList.userList.get(i).setLoggin(true);
											} else {
												UserList.userList.get(i).setLoggin(false);
											}
										}
										setVisible(true);
										loginpane.setVisible(false);
									} else {
										new PopFrame("Wrong password!");
									}
								} else {
									new PopFrame("U do not exist!");
								}
							} else {
								new PopFrame("Fill all the blanks dude!");
							}
						}
					});
					// click the Back to Menu button
					loginpane.getBtnBackToMenu().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							setVisible(true);
							loginpane.setVisible(false);
						}
					});
					loginpane.setVisible(true);
					setVisible(false);
				}

			}
		});
		setVisible(true);
	}

}