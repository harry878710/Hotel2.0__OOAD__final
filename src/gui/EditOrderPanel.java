package gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import operation.EditBook;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.Color;

public class EditOrderPanel extends JPanel {

	JButton btnRoomNumber;
	JButton btnDate;
	JButton btnDelete;
	JButton btnBackToMenu;
	JPanel OperationalPane;
	JPanel defaultPanel;
	EditOrderDate mod;
	EditOrderRoom mor;
	EditOrderDelete del;

	/**
	 * Create the panel.
	 */
	public EditOrderPanel() {
		setSize(1080, 720);
		setLayout(new GridLayout(0, 2, 0, 0));

		JPanel selectionPane = new JPanel();
		selectionPane.setSize(540, 720);
		selectionPane.setBackground(new Color(95, 158, 160));
		add(selectionPane);
		selectionPane.setLayout(null);

		OperationalPane = new JPanel();
		add(OperationalPane);

		JLabel lblWhatUWant = new JLabel("What U want ?");
		lblWhatUWant.setFont(new Font("Agency FB", Font.PLAIN, 48));
		lblWhatUWant.setHorizontalAlignment(SwingConstants.CENTER);
		lblWhatUWant.setBounds(14, 13, 499, 120);
		selectionPane.add(lblWhatUWant);

		btnRoomNumber = new JButton("Change Room Number");
		btnRoomNumber.setFont(new Font("Agency FB", Font.PLAIN, 48));
		btnRoomNumber.setBackground(new Color(240, 255, 240));
		btnRoomNumber.setOpaque(true);
		btnRoomNumber.setBounds(14, 146, 499, 120);
		selectionPane.add(btnRoomNumber);

		btnDate = new JButton("Change Date");
		btnDate.setFont(new Font("Agency FB", Font.PLAIN, 48));
		btnDate.setBackground(new Color(240, 255, 240));
		btnDate.setOpaque(true);
		btnDate.setBounds(14, 279, 499, 120);
		selectionPane.add(btnDate);

		btnDelete = new JButton("Delete Order");
		btnDelete.setFont(new Font("Agency FB", Font.PLAIN, 48));
		btnDelete.setBackground(new Color(240, 255, 240));
		btnDelete.setOpaque(true);
		btnDelete.setBounds(14, 412, 499, 120);
		selectionPane.add(btnDelete);

		btnBackToMenu = new JButton("Back To Menu");
		btnBackToMenu.setFont(new Font("Agency FB", Font.PLAIN, 48));
		btnBackToMenu.setBackground(new Color(200, 200, 255));
		btnBackToMenu.setOpaque(true);
		btnBackToMenu.setBounds(14, 545, 499, 120);
		selectionPane.add(btnBackToMenu);

		OperationalPane.setLayout(new CardLayout(0, 0));
		OperationalPane.setSize(540, 720);
		defaultPanel = new JPanel();
		defaultPanel.setBackground(new Color(102, 205, 170));
		defaultPanel.setLayout(new BorderLayout(0, 0));
		JLabel label = new JLabel("Choose One!");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Agency FB", Font.PLAIN, 48));
		defaultPanel.add(label);
		OperationalPane.add(defaultPanel, "name_220055604692901");
		mor = new EditOrderRoom();
		del = new EditOrderDelete();
		mod = new EditOrderDate();
		OperationalPane.add(mod, "name_220055604692900");
		OperationalPane.add(mor, "name_219659037387500");
		OperationalPane.add(del, "name_219659090034700");
		mod.setVisible(false);
		mor.setVisible(false);
		del.setVisible(false);

	}

	public void activateEditOrderPanel(MainFrame mainframe) {
		mainframe.getContentPane().add(this, BorderLayout.CENTER);

		btnBackToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainframe.activateUserMenuPanel();
				setVisible(false);
			}
		});

		btnRoomNumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mor.setVisible(true);
				mod.setVisible(false);
				del.setVisible(false);
				defaultPanel.setVisible(false);
			}
		});

		btnDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mor.setVisible(false);
				mod.setVisible(true);
				del.setVisible(false);
				defaultPanel.setVisible(false);
			}
		});

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mor.setVisible(false);
				mod.setVisible(false);
				del.setVisible(true);
				defaultPanel.setVisible(false);
			}
		});

		mor.btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] newRoomCombination = { mor.single, mor.dual, mor.quad };
				int op = (new EditBook()).editRoomCombination(mor.bookId, newRoomCombination);
				// Operation.changeReservation(mor.bookId, mor.single, mor.dual, mor.quad);
				if (op == 0) {
					new PopFrame("Successfully Edited !");
					mainframe.activateMyOrderPanel();
					setVisible(false);
				} else if (op == 1) {
					new PopFrame("Room never enough !");
				} else if (op == 2) {
					new PopFrame("Room not enough !");
				}
			}
		});

		mod.btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String checkOutDate = dateToString(
						calculateCheckOutDate(stringToDate(mod.getCheckInDate()), mod.getNight()));
				int op = (new EditBook()).editCheckInDateAndNight(mod.bookId, mod.getCheckInDate(), checkOutDate);
				if (op == 0) {
					new PopFrame("Successfully Edited !");
					mainframe.activateMyOrderPanel();
					setVisible(false);
				} else if (op == 1) {
					new PopFrame("Room never enough !");
				} else if (op == 2) {
					new PopFrame("Room not enough !");
				}
			}
		});

		del.btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int op = (new EditBook()).deleteBook(del.bookId);
				// Operation.changeReservation(del.bookId);
				if (op == 0) {
					new PopFrame("Successfully deleted !");
					mainframe.activateMyOrderPanel();
					setVisible(false);
				} else {
					new PopFrame("Error ! Something is wrong !");
				}
			}
		});
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
