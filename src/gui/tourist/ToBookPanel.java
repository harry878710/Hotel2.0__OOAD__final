package gui.tourist;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import gui.LoginPanel;
import gui.MainFrame;
import gui.PopFrame;
import member.Tourist;

import member.TouristOperation;

import operation.SearchAndBook;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
				if (TouristOperation.anyoneLoggedin()) {
					String checkOutDate = dateToString(
							calculateCheckOutDate(stringToDate(bd.getCheckInDate()), bd.getNight()));
					String bookId = new SearchAndBook().commitBook(bd.getCheckInDate(), checkOutDate, bd.getHotelId(),
							TouristOperation.whoIsLoggedin(), bd.getRoomCombination());
					new PopFrame("BOOK SUCCESS!\nYour book ID is " + bookId);
					mainframe.activateUserMenuPanel();
					setVisible(false);
				} else {
					new PopFrame("Log in as tourist first!");
					LoginPanel loginpane = new LoginPanel(1);
					mainframe.getContentPane().add(loginpane, BorderLayout.CENTER);
					// click the Log in button
					loginpane.getBtnLogIn().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int op = TouristOperation.userLogin(loginpane.getName(), loginpane.getPassword());
							switch (op) {
							case 0:
								setVisible(true);
								loginpane.setVisible(false);
								break;
							case 1:
								new PopFrame("error: Incorrect password.");
								break;
							case 2:
								new PopFrame("error: Unable to find this. Please check your user name.");
								break;
							case 3:
								new PopFrame("error: Please fill all the blanks.");
								break;
							default:
								new PopFrame("error: Fatal error.");

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

	private String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		return sdf.format(date);
	}

	private Date calculateCheckOutDate(Date checkInDate, int night) {
		Date toReturn = new Date(checkInDate.getTime());
		for (int i = 0; i < night; i++) {
			toReturn = nextDate(toReturn);
		}
		return toReturn;
	}

	private Date nextDate(Date thisDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(thisDate);
		c.add(Calendar.DATE, 1);
		Date nextDate = c.getTime();
		return nextDate;
	}

	private Date stringToDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		return sdf.parse(str, new ParsePosition(0));
	}
}