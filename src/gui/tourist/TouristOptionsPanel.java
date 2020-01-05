package gui.tourist;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import gui.ChangePWFrame;
import gui.MainFrame;
import member.TouristOperation;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;

public class TouristOptionsPanel extends JPanel {
	JButton btnChangePW;
	JButton btnBackToMenu;
	JButton btnLogOut;
	/**
	 * Create the panel.
	 */
	public TouristOptionsPanel() {
		setSize(1200, 900);
		setLayout(new GridLayout(3, 0, 0, 0));
		
		btnChangePW = new JButton("Change Password");
		btnChangePW.setBackground(new Color(95, 158, 160));
		btnChangePW.setFont(new Font("Agency FB", Font.PLAIN, 72));
		add(btnChangePW);
		
		btnBackToMenu = new JButton("Back");
		btnBackToMenu.setBackground(new Color(95, 138, 160));
		btnBackToMenu.setFont(new Font("Agency FB", Font.PLAIN, 72));
		add(btnBackToMenu);
		
		btnLogOut = new JButton("Log Out");
		btnLogOut.setBackground(new Color(75, 158, 160));
		btnLogOut.setFont(new Font("Agency FB", Font.PLAIN, 72));
		add(btnLogOut);

	}
	public void activateUserOptionsPanel(MainFrame mainframe) {
		mainframe.getContentPane().add(this, BorderLayout.CENTER);

		btnBackToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainframe.activateUserMenuPanel();
				setVisible(false);
			}
		});

		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TouristOperation.everyOneloggedOut();
				mainframe.initialize();
				setVisible(false);
			}
		});

		btnChangePW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChangePWFrame().setVisible(true);
			}
		});
	}

}
