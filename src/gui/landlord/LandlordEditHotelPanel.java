package gui.landlord;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import book_Hotel_Room.HotelOperation;
import gui.MainFrame;
import gui.PopFrame;
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

public class LandlordEditHotelPanel extends JPanel {

	JButton btnRoomNumber;
	JButton btnInfo;
	JButton btnDelete;
	JButton btnBackToMenu;
	JPanel OperationalPane;
	JPanel defaultPanel;
	LandlordEditRoomAndPrice ler;
	LandlordEditInformation lef;

	/**
	 * Create the panel.
	 */
	/**
	 * 
	 */
	public LandlordEditHotelPanel() {
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

		btnRoomNumber = new JButton("Change Room Number & Price");
		btnRoomNumber.setFont(new Font("Agency FB", Font.PLAIN, 48));
		btnRoomNumber.setBackground(new Color(240, 255, 240));
		btnRoomNumber.setOpaque(true);
		btnRoomNumber.setBounds(14, 146, 499, 120);
		selectionPane.add(btnRoomNumber);

		btnInfo = new JButton("Change Hotel Information");
		btnInfo.setFont(new Font("Agency FB", Font.PLAIN, 48));
		btnInfo.setBackground(new Color(240, 255, 240));
		btnInfo.setOpaque(true);
		btnInfo.setBounds(14, 279, 499, 120);
		selectionPane.add(btnInfo);

		btnBackToMenu = new JButton("Back To Menu");
		btnBackToMenu.setFont(new Font("Agency FB", Font.PLAIN, 48));
		btnBackToMenu.setBackground(new Color(200, 200, 255));
		btnBackToMenu.setOpaque(true);
		btnBackToMenu.setBounds(14, 412, 499, 240);
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
		ler = new LandlordEditRoomAndPrice();
		lef = new LandlordEditInformation();
//		OperationalPane.add(lef, "name_220055604692900");
		OperationalPane.add(ler, "name_219659037387500");
		ler.setVisible(false);
//		lef.setVisible(false);
		
	}

	public void activateLandlordEditHotelPanel(MainFrame mainframe) {
		mainframe.getContentPane().add(this, BorderLayout.CENTER);

		btnBackToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainframe.activateLandlordMenuPanel();
				setVisible(false);
			}
		});

		btnRoomNumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ler.setVisible(true);
//				lef.setVisible(false);
				defaultPanel.setVisible(false);
			}
		});

		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				ler.setVisible(false);
//				lef.setVisible(true);
//				defaultPanel.setVisible(false);
			}
		});

		ler.btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] newRoomCombination = { ler.single, ler.dual, ler.quad };
				HotelOperation.editHotelRoomAndPrice(ler.hotelId, newRoomCombination, ler.getPrice());
				new PopFrame("Successfully Edited !");
				mainframe.activateLandlordOrderPanel();
				setVisible(false);

			}
		});

//		lef.btnConfirm.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				new EditBook().editCheckInDateAndNight(mod.bookId, mod.getCheckInDate(), checkOutDate);
//				new PopFrame("Successfully Edited !");
//				mainframe.activateMyOrderPanel();
//				setVisible(false);
//
//			}
//		});
		setVisible(true);
	}

}
