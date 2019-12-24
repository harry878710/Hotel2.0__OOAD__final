package testing.gui;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelD extends JPanel {

	public JButton btnGoToA,btnGoToE; 
	/**
	 * Create the panel.
	 */
	public PanelD() {
		setBackground(Color.YELLOW);
		setLayout(null);
		
		JLabel lblPanelD = new JLabel("Panel D");
		lblPanelD.setBounds(186, 65, 46, 15);
		add(lblPanelD);
		
		btnGoToA = new JButton("go to A");
		
		btnGoToA.setBounds(162, 121, 87, 23);
		add(btnGoToA);
		
		btnGoToE = new JButton("go to E");
		
		btnGoToE.setBounds(162, 180, 87, 23);
		add(btnGoToE);

	}
}
