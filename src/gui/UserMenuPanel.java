package gui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;

public class UserMenuPanel extends JPanel {
	JButton btnRoom;
	JButton btnMyOrder;
	JButton btnUserOptions;

	/**
	 * Create the panel.
	 */
	public UserMenuPanel() {
		setSize(900, 600);
		setLayout(new GridLayout(3, 1, 0, 0));

		btnRoom = new JButton("Room?");
		btnRoom.setBackground(new Color(95, 158, 160));
		btnRoom.setFont(new Font("Agency FB", Font.PLAIN, 72));
		add(btnRoom);

		btnMyOrder = new JButton("My order");
		btnMyOrder.setBackground(new Color(95, 158, 160));
		btnMyOrder.setFont(new Font("Agency FB", Font.PLAIN, 72));
		add(btnMyOrder);

		btnUserOptions = new JButton("User Options");
		btnUserOptions.setBackground(new Color(95, 158, 160));
		btnUserOptions.setFont(new Font("Agency FB", Font.PLAIN, 72));
		add(btnUserOptions);
	}
	
	public void activateUserMenuPanel(MainFrame mainframe) {
		mainframe.getContentPane().add(this, BorderLayout.CENTER);

		btnRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainframe.activateSearchPanel();
				setVisible(false);
			}
		});

		btnMyOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainframe.activateMyOrderPanel();
				setVisible(false);
			}
		});
		btnUserOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainframe.activateUserOptionsPanel();
				setVisible(false);
			}
		});

		setVisible(true);
	}
}
