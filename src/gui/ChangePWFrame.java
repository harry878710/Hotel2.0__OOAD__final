package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import member.Landlord;
import member.LandlordOperation;
import member.Tourist;

import member.TouristOperation;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class ChangePWFrame extends JFrame {
	private JTextField txtPleaseDontEnter;
	private JPasswordField textFieldOld;
	private JPasswordField textFieldNew;
	private JPanel contentPane;
	private JPasswordField textFieldConfirm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePWFrame cpFrame = new ChangePWFrame();
					cpFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChangePWFrame() {
		setTitle("The Longer, The Better");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblName = new JLabel(" Confirm Name");
		lblName.setOpaque(true);
		lblName.setBackground(new Color(153, 204, 204));
		lblName.setFont(new Font("Agency FB", Font.PLAIN, 36));
		contentPane.add(lblName);

		txtPleaseDontEnter = new JTextField();
		txtPleaseDontEnter.setBackground(new Color(240, 255, 240));
		txtPleaseDontEnter.setForeground(new Color(204, 0, 0));
		txtPleaseDontEnter.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				if (txtPleaseDontEnter.getText().equals("Please DON'T enter others name")) {
					txtPleaseDontEnter.setText("");
				}
			}
		});
		txtPleaseDontEnter.setFont(new Font("Chiller", Font.PLAIN, 28));
		txtPleaseDontEnter.setText("Please DON'T enter others name");
		txtPleaseDontEnter.setToolTipText("");
		txtPleaseDontEnter.setHorizontalAlignment(SwingConstants.CENTER);

		contentPane.add(txtPleaseDontEnter);
		txtPleaseDontEnter.setColumns(10);

		JLabel lblOld = new JLabel(" Old Password");
		lblOld.setBackground(new Color(153, 204, 204));
		lblOld.setOpaque(true);
		lblOld.setFont(new Font("Agency FB", Font.PLAIN, 36));
		contentPane.add(lblOld);

		textFieldOld = new JPasswordField();
		textFieldOld.setBackground(new Color(240, 255, 240));
		contentPane.add(textFieldOld);
		textFieldOld.setColumns(10);

		JLabel lblNew = new JLabel(" New password");
		lblNew.setBackground(new Color(153, 204, 204));
		lblNew.setOpaque(true);
		lblNew.setFont(new Font("Agency FB", Font.PLAIN, 36));
		contentPane.add(lblNew);

		textFieldNew = new JPasswordField();
		textFieldNew.setBackground(new Color(240, 255, 240));
		contentPane.add(textFieldNew);
		textFieldNew.setColumns(10);

		JButton btnChange = new JButton("Change !");
		btnChange.setBackground(new Color(32, 178, 170));
		btnChange.setFont(new Font("Agency FB", Font.PLAIN, 36));

		txtPleaseDontEnter.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {
				if ((int) arg0.getKeyChar() == 10) {
					btnChange.doClick();
				}
			}
		});
		textFieldOld.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {
				if ((int) arg0.getKeyChar() == 10) {
					btnChange.doClick();
				}
			}
		});
		textFieldNew.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {
				if ((int) arg0.getKeyChar() == 10) {
					btnChange.doClick();
				}
			}
		});

		JLabel labelConfirm = new JLabel(" Confirm New password");
		labelConfirm.setBackground(new Color(153, 204, 204));
		labelConfirm.setOpaque(true);
		labelConfirm.setFont(new Font("Agency FB", Font.PLAIN, 36));
		contentPane.add(labelConfirm);

		textFieldConfirm = new JPasswordField();
		textFieldConfirm.setBackground(new Color(240, 255, 240));
		textFieldConfirm.setColumns(10);
		textFieldConfirm.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {
				if ((int) arg0.getKeyChar() == 10) {
					btnChange.doClick();
				}
			}
		});
		contentPane.add(textFieldConfirm);

		JButton btnIRegret = new JButton("I REGRET ...");
		btnIRegret.setBackground(new Color(32, 178, 170));
		btnIRegret.setFont(new Font("Agency FB", Font.PLAIN, 36));
		btnIRegret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnIRegret);

		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (TouristOperation.anyoneLoggedin()) {
					if (txtPleaseDontEnter.getText().equals(TouristOperation.whoIsLoggedin())) {
						int op = TouristOperation.changePassword(txtPleaseDontEnter.getText(),
								new String(textFieldOld.getPassword()), new String(textFieldNew.getPassword()),
								new String(textFieldConfirm.getPassword()));
						switch (op) {
						case 3:
							new PopFrame("Incomplete data");
							textFieldOld.setText("");
							textFieldNew.setText("");
							textFieldConfirm.setText("");
							break;
						case 1:
							new PopFrame("Your old password is wrong");
							textFieldOld.setText("");
							textFieldNew.setText("");
							textFieldConfirm.setText("");
							break;
						case 2:
							new PopFrame("New Password doesn't match");
							textFieldOld.setText("");
							textFieldNew.setText("");
							textFieldConfirm.setText("");
							break;
						case 4:
							new PopFrame("DON'T use the same password");
							textFieldOld.setText("");
							textFieldNew.setText("");
							textFieldConfirm.setText("");
							break;
						case 0:
							Tourist.userList = TouristOperation.uploadUserList();
							for (int i = 0; i < Tourist.userList.size(); i++) {
								if (Tourist.userList.get(i).getName().equals(txtPleaseDontEnter.getText())) {
									Tourist.userList.get(i).setLogin(true);
								} else {
									Tourist.userList.get(i).setLogin(false);
								}
							}
							new PopFrame("Change success");
							dispose();
							break;
						default:
							new PopFrame("error: fatal error.");
						}
					} else {
						new PopFrame("Liar ! U NOT " + txtPleaseDontEnter.getText());
					}
				} else if (LandlordOperation.anyoneLoggedin()) {
					if (txtPleaseDontEnter.getText().equals(LandlordOperation.whoIsLoggedin())) {
						int op = LandlordOperation.changePassword(txtPleaseDontEnter.getText(),
								new String(textFieldOld.getPassword()), new String(textFieldNew.getPassword()),
								new String(textFieldConfirm.getPassword()));
						switch (op) {
						case 3:
							new PopFrame("Incomplete data");
							textFieldOld.setText("");
							textFieldNew.setText("");
							textFieldConfirm.setText("");
							break;
						case 1:
							new PopFrame("Your old password is wrong");
							textFieldOld.setText("");
							textFieldNew.setText("");
							textFieldConfirm.setText("");
							break;
						case 2:
							new PopFrame("New Password doesn't match");
							textFieldOld.setText("");
							textFieldNew.setText("");
							textFieldConfirm.setText("");
							break;
						case 4:
							new PopFrame("DON'T use the same password");
							textFieldOld.setText("");
							textFieldNew.setText("");
							textFieldConfirm.setText("");
							break;
						case 0:
							Landlord.landlordList = LandlordOperation.uploadUserList();
							for (int i = 0; i < Landlord.landlordList.size(); i++) {
								if (Landlord.landlordList.get(i).getName().equals(txtPleaseDontEnter.getText())) {
									Landlord.landlordList.get(i).setLogin(true);
								} else {
									Landlord.landlordList.get(i).setLogin(false);
								}
							}
							new PopFrame("Change success");
							dispose();
							break;
						default:
							new PopFrame("error: fatal error.");
						}
					} else {
						new PopFrame("Liar ! U NOT " + txtPleaseDontEnter.getText());
					}

				}
			}
		});
		contentPane.add(btnChange);

	}
}
