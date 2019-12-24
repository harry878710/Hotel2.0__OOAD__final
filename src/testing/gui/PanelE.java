package testing.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelE extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelE() {
		setBackground(Color.ORANGE);
		setLayout(null);
		
		JLabel lblPanelE = new JLabel("Panel E");
		lblPanelE.setBounds(188, 81, 46, 15);
		add(lblPanelE);
		
		JButton btnGoToD = new JButton("go to D");
		btnGoToD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnGoToD.setBounds(165, 130, 87, 23);
		add(btnGoToD);

	}
}
