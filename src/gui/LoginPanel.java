package gui;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import member.LandlordOperation;
 
import member.TouristOperation;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JRadioButton;

public class LoginPanel extends JPanel {
	private String identity = "no_identity";
	private JTextField textFieldName;
	private JPasswordField textFieldPassword;

	private JButton btnLogIn;
	private JButton btnBackToMenu;

	private ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtn_Tourist;
	private JRadioButton rdbtn_Landlord;

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

		rdbtn_Tourist = new JRadioButton("I'm Tourist");
		rdbtn_Tourist.setBounds(916, 88, 105, 23);
		add(rdbtn_Tourist);
		buttonGroup.add(rdbtn_Tourist);
		rdbtn_Tourist.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				identity = rdbtn_Tourist.getText();
			}
		});

		rdbtn_Landlord = new JRadioButton("I'm Landlord");
		rdbtn_Landlord.setBounds(916, 156, 105, 23);
		add(rdbtn_Landlord);
		buttonGroup.add(rdbtn_Landlord);
		rdbtn_Landlord.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				identity = rdbtn_Landlord.getText();
			}
		});
	}

	public void activateLoginPanel(MainFrame mainframe) {
		mainframe.getContentPane().add(this, BorderLayout.CENTER);
		// click the Log in button
		getBtnLogIn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(identity);
				switch (identity) {
				case "I'm Tourist":
					int op = TouristOperation.userLogin(getName(), getPassword());
					switch (op) {
					case 0:
						mainframe.activateUserMenuPanel();
						setVisible(false);
						break;
					case 1:
						new PopFrame("error: Incorrect password.");
						break;
					case 2:
						new PopFrame("error: Unable to find this. Please check your user name.");
						break;
					case 3:
						new PopFrame("error: Please fill all the blanks.");
						break;
					default:
						new PopFrame("error: Fatal error.");

					}
					break;
				case "I'm Landlord":
					int op_land = LandlordOperation.userLogin(getName(), getPassword());
					switch (op_land) {
					case 0:
						mainframe.activateLandlordMenuPanel();
						setVisible(false);
						break;
					case 1:
						new PopFrame("error: Incorrect password.");
						break;
					case 2:
						new PopFrame("error: Unable to find this. Please check your user name.");
						break;
					case 3:
						new PopFrame("error: Please fill all the blanks.");
						break;
					default:
						new PopFrame("error: Fatal error.");

					}
					break;
				default:
					new PopFrame("error: Identity must be chosen");
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