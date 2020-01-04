package gui.landlord;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import bookAndUser.BookOperation;
import bookAndUser.UserList;
import bookAndUser.UserOperation;
import gui.MainFrame;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class LandlordOrderPanel extends JPanel {
	//JButton btnEditOrder;
	JButton btnBack;

	/**
	 * Create the panel.
	 */
	public LandlordOrderPanel() {
		setBackground(new Color(95, 158, 160));
		setSize(1080, 720);
		setLayout(null);

		JTextArea textArea_My_Hotels = new JTextArea(10, 40);
		textArea_My_Hotels.setBackground(new Color(240, 255, 240));
		textArea_My_Hotels.setFont(new Font("MS PMincho", Font.PLAIN, 18));
		String myBookList = (BookOperation.bookOfUser(UserOperation.whoIsLoggedin()));
		if (!myBookList.equals("")) {
			textArea_My_Hotels.setText("User Id :" + UserOperation.whoIsLoggedin() + "\n\n" + myBookList);
		} else {
			textArea_My_Hotels.setText("User Id :" + UserOperation.whoIsLoggedin() + "\n\n" + "No order yet.");
		}
		textArea_My_Hotels.setSelectionStart(0);
		textArea_My_Hotels.setSelectionEnd(0);
		textArea_My_Hotels.setEditable(false);
		JScrollPane scroller = new JScrollPane(textArea_My_Hotels);
		scroller.setBounds(25, 108, 975, 567);
		scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(scroller);

//		btnEditOrder = new JButton("Edit");
//		btnEditOrder.setOpaque(true);
//		btnEditOrder.setBackground(new Color(32, 178, 170));
//		btnEditOrder.setFont(new Font("Agency FB", Font.PLAIN, 48));
//		btnEditOrder.setBounds(850, 14, 150, 60);
//		add(btnEditOrder);

		btnBack = new JButton("Back");
		btnBack.setOpaque(true);
		btnBack.setBackground(new Color(32, 178, 170));
		btnBack.setFont(new Font("Agency FB", Font.PLAIN, 48));
		btnBack.setBounds(25, 14, 150, 60);
		add(btnBack);
		
		JLabel lblNewLabel = new JLabel("View orders of this hotel");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel.setBounds(220, 14, 291, 60);
		add(lblNewLabel);
		
		JComboBox comboBox_HotelId = new JComboBox();
		comboBox_HotelId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		comboBox_HotelId.setBounds(460, 14, 250, 62);
		add(comboBox_HotelId);

	}

	public void activateLandlordOrderPanel(MainFrame mainframe) {
		System.out.println("LandlordOrderPanel");
		mainframe.getContentPane().add(this, BorderLayout.CENTER);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainframe.activateLandlordMenuPanel();
				setVisible(false);
			}
		});
//		btnEditOrder.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				mainframe.activateEditOrderPanel();
//				setVisible(false);
//			}
//		});
	}
}