package gui.landlord;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import gui.MainFrame;

public class LandlordMenuPanel extends JPanel {
	JButton btnMyHotels;
	JButton btnAllOrders;
	JButton btnLandlordAccountOptions;

	/**
	 * Create the panel.
	 */
	public LandlordMenuPanel() {
		setSize(900, 600);
		setLayout(new GridLayout(3, 1, 0, 0));

		btnMyHotels = new JButton("Hotels I Own");
		btnMyHotels.setBackground(new Color(95, 158, 160));
		btnMyHotels.setFont(new Font("Agency FB", Font.PLAIN, 72));
		add(btnMyHotels);

		btnAllOrders = new JButton("All Orders To Deal With");
		btnAllOrders.setBackground(new Color(95, 158, 160));
		btnAllOrders.setFont(new Font("Agency FB", Font.PLAIN, 72));
		add(btnAllOrders);

		btnLandlordAccountOptions = new JButton("Landlord Options");
		btnLandlordAccountOptions.setBackground(new Color(95, 158, 160));
		btnLandlordAccountOptions.setFont(new Font("Agency FB", Font.PLAIN, 72));
		add(btnLandlordAccountOptions);
	}

	public void activateLandlordMenuPanel(MainFrame mainframe) {
		mainframe.getContentPane().add(this, BorderLayout.CENTER);

		btnMyHotels.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainframe.activateLandlordHotelsPanel();
				setVisible(false);
			}
		});
		
		
		btnAllOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainframe.activateLandlordOrderPanel();
				setVisible(false);
			}
		});
		
		btnLandlordAccountOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainframe.activateLandlordAccountOptionPanel();
				setVisible(false);
			}
		});

		setVisible(true);
	}

}
