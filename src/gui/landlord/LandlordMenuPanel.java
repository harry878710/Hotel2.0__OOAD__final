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
	JButton btnLandlordOptions;

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

		btnLandlordOptions = new JButton("Landlord Options");
		btnLandlordOptions.setBackground(new Color(95, 158, 160));
		btnLandlordOptions.setFont(new Font("Agency FB", Font.PLAIN, 72));
		add(btnLandlordOptions);
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
		
		btnLandlordOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainframe.activateUserOptionsPanel();
				setVisible(false);
			}
		});

		setVisible(true);
	}

}
