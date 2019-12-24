package gui;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;

public class MainMenu extends JPanel {
	
	private JButton btnSearch;
	private JButton btnLogIn;

	/**
	 * Create the panel.
	 */
	public MainMenu() {
		setLayout(new GridLayout(0, 2, 0, 0));

		btnSearch = new JButton("Search With AGUARE");
		btnSearch.setOpaque(true);
		btnSearch.setBackground(new Color(0, 191, 255));
		btnSearch.setForeground(new Color(0, 0, 0));
		btnSearch.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 32));
		add(btnSearch);

		btnLogIn = new JButton("AGUARE Member");
		btnLogIn.setBackground(new Color(95, 158, 160));
		btnLogIn.setOpaque(true);
		btnLogIn.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 32));
		add(btnLogIn);

		
	}
	
	public void activateMainMenu(MainFrame mainframe) {
		mainframe.getContentPane().add(this, BorderLayout.CENTER);
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainframe.activateLoginPanel();
				setVisible(false);

			}
		});

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainframe.activateSearchPanel();
				setVisible(false);
			}
		});
	}
	
}
