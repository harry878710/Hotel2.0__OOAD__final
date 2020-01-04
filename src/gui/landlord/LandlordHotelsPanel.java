package gui.landlord;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import book_Hotel_Room.BookOperation;
import book_Hotel_Room.HotelOperation;
import member.LandlordOperation;
import member.TouristOperation;
import gui.MainFrame;

public class LandlordHotelsPanel extends JPanel {

	JButton btnAdd;
	JButton btnBack;

	/**
	 * Create the panel.
	 */
	public LandlordHotelsPanel() {
		setBackground(new Color(95, 158, 160));
		setSize(1080, 720);
		setLayout(null);

		JTextArea textArea = new JTextArea(10, 40);
		textArea.setBackground(new Color(240, 255, 240));
		textArea.setFont(new Font("Dialog", Font.PLAIN, 18));
		String myHotelList = (LandlordOperation.showAllMyHotel(LandlordOperation.whoIsLoggedin()));
		if (!myHotelList.equals("")) {
			textArea.setText("Landlord Id :" + LandlordOperation.whoIsLoggedin() + "\n\n" + myHotelList);
		} else {
			textArea.setText("Landlord Id :" + LandlordOperation.whoIsLoggedin() + "\n\n" + "No Hotel yet.");
		}
		textArea.setSelectionStart(0);
		textArea.setSelectionEnd(0);
		textArea.setEditable(false);
		JScrollPane scroller = new JScrollPane(textArea);
		scroller.setBounds(25, 108, 975, 567);
		scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(scroller);

		btnAdd = new JButton("Add");
		btnAdd.setOpaque(true);
		btnAdd.setBackground(new Color(32, 178, 170));
		btnAdd.setFont(new Font("Agency FB", Font.PLAIN, 48));
		btnAdd.setBounds(850, 14, 150, 60);
		add(btnAdd);

		btnBack = new JButton("Back");
		btnBack.setOpaque(true);
		btnBack.setBackground(new Color(32, 178, 170));
		btnBack.setFont(new Font("Agency FB", Font.PLAIN, 48));
		btnBack.setBounds(25, 14, 150, 60);
		add(btnBack);

	}

	public void activateLandlordHotelsPanel(MainFrame mainframe) {
		mainframe.getContentPane().add(this, BorderLayout.CENTER);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainframe.activateLandlordMenuPanel();
				setVisible(false);
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainframe.activateLandlordAddHotelPanel();
				setVisible(false);
			}
		});
	}

}
