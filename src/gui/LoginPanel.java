package gui;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import bookAndUser.UserList;
import bookAndUser.UserOperation;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import java.awt.Color;

public class LoginPanel extends JPanel {
	private JTextField textFieldName;
	private JPasswordField textFieldPassword;
	private JButton btnLogIn;
	private JButton btnBackToMenu;

	public String getName() {
		return textFieldName.getText();
	}

	public String getPassword() {
		return new String(textFieldPassword.getPassword());
	}

	public JButton getBtnLogIn() {
		return btnLogIn;
	}

	public JButton getBtnBackToMenu() {
		return btnBackToMenu;
	}

	/**
	 * Create the panel.
	 */
	public LoginPanel() {
		setBackground(new Color(95, 158, 160));
		setSize(1200, 900);
		setLayout(null);

		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Arial Black", Font.PLAIN, 48));
		textFieldName.setBackground(new Color(240, 255, 240));
		textFieldName.setBounds(370, 76, 500, 150);
		textFieldName.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent arg0) {
				if ((int) arg0.getKeyChar() == 10) {
					btnLogIn.doClick();
				}

			}

		});
		add(textFieldName);
		textFieldName.setColumns(10);

		textFieldPassword = new JPasswordField();
		textFieldPassword.setFont(new Font("Arial Black", Font.PLAIN, 48));
		textFieldPassword.setBackground(new Color(240, 255, 240));
		textFieldPassword.setBounds(370, 248, 500, 150);
		textFieldPassword.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {
				if ((int) arg0.getKeyChar() == 10) {
					btnLogIn.doClick();
				}
			}
		});
		add(textFieldPassword);
		textFieldPassword.setColumns(10);

		JLabel userName = new JLabel("Name");
		userName.setFont(new Font("Agency FB", Font.PLAIN, 60));
		userName.setHorizontalAlignment(SwingConstants.CENTER);
		userName.setBounds(103, 76, 263, 150);
		add(userName);
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Agency FB", Font.PLAIN, 60));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(103, 248, 263, 150);
		add(lblNewLabel_1);

		JButton btnJoin = new JButton("JOIN");
		btnJoin.setFont(new Font("Agency FB", Font.PLAIN, 60));
		btnJoin.setBackground(new Color(32, 178, 170));
		btnJoin.setBounds(103, 544, 767, 120);
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JoinFrame().setVisible(true);
			}
		});
		btnLogIn = new JButton("LOG IN");
		btnLogIn.setFont(new Font("Agency FB", Font.PLAIN, 60));
		btnLogIn.setBackground(new Color(32, 178, 170));
		btnLogIn.setBounds(103, 411, 767, 120);
		add(btnLogIn);
		add(btnJoin);

		btnBackToMenu = new JButton("BACK");
		btnBackToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBackToMenu.setFont(new Font("Agency FB", Font.PLAIN, 48));
		btnBackToMenu.setBackground(new Color(95, 158, 160));
		btnBackToMenu.setBounds(105, 13, 194, 59);
		add(btnBackToMenu);
	}

	public void activateLoginPanel(MainFrame mainframe) {
		mainframe.getContentPane().add(this, BorderLayout.CENTER);
		// click the Log in button
		getBtnLogIn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!getName().equals("") && !getPassword().equals("")) {
					if (UserOperation.hasUser(getName())) {
						if (UserOperation.checkPassword(getName(), getPassword())) {
							for (int i = 0; i < UserList.userList.size(); i++) {
								if (getName().equals(UserList.userList.get(i).getName())) {
									UserList.userList.get(i).setLoggin(true);
								} else {
									UserList.userList.get(i).setLoggin(false);
								}
							}
							mainframe.activateUserMenuPanel();
							setVisible(false);
						} else {
							new PopFrame("Wrong password!");
						}
					} else {
						new PopFrame("U do not exist!");
					}
				} else {
					new PopFrame("Fill all the blanks dude!");
				}
			}
		});
		// click the Back to Menu button
		getBtnBackToMenu().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainframe.initialize();
				setVisible(false);
			}
		});
		setVisible(true);
	}
}