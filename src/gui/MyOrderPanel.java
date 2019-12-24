package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import bookAndUser.BookOperation;
import bookAndUser.UserList;
import bookAndUser.UserOperation;
import operation.Operation;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;

public class MyOrderPanel extends JPanel {
	JButton btnEditOrder;
	JButton btnBack;

	/**
	 * Create the panel.
	 */
	public MyOrderPanel() {
		setBackground(new Color(95, 158, 160));
		setSize(1080, 720);
		setLayout(null);

		JTextArea textArea = new JTextArea(10, 40);
		textArea.setBackground(new Color(240, 255, 240));
		textArea.setFont(new Font("�L�n������", Font.PLAIN, 18));
		String myBookList = (BookOperation.bookOfUser(UserOperation.whoIsLoggedin()));
		if (!myBookList.equals("")) {
			textArea.setText("User Id :" + UserOperation.whoIsLoggedin() + "\n\n" + myBookList);
		} else {
			textArea.setText("User Id :" + UserOperation.whoIsLoggedin() + "\n\n" + "No order yet.");
		}
		textArea.setSelectionStart(0);
		textArea.setSelectionEnd(0);
		textArea.setEditable(false);
		JScrollPane scroller = new JScrollPane(textArea);
		scroller.setBounds(25, 108, 975, 567);
		scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(scroller);

		btnEditOrder = new JButton("Edit");
		btnEditOrder.setOpaque(true);
		btnEditOrder.setBackground(new Color(32, 178, 170));
		btnEditOrder.setFont(new Font("Agency FB", Font.PLAIN, 48));
		btnEditOrder.setBounds(850, 14, 150, 60);
		add(btnEditOrder);

		btnBack = new JButton("Back");
		btnBack.setOpaque(true);
		btnBack.setBackground(new Color(32, 178, 170));
		btnBack.setFont(new Font("Agency FB", Font.PLAIN, 48));
		btnBack.setBounds(25, 14, 150, 60);
		add(btnBack);

	}
	
	public void activateMyOrderPanel(MainFrame mainframe) {
		mainframe.getContentPane().add(this, BorderLayout.CENTER);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainframe.activateUserMenuPanel();
				setVisible(false);
			}
		});
		btnEditOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainframe.activateEditOrderPanel();
				setVisible(false);
			}
		});
	}
}