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
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.SystemColor;

public class JoinFrame extends JFrame {
	private JTextField textField;
	private JPasswordField textField_1;
	private JPasswordField textField_2;
	private JPanel contentPane;
	private JButton btnNo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JoinFrame frame = new JoinFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JoinFrame() {
		setTitle("Join us");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 602, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("   Set User Name");
		lblNewLabel.setBackground(new Color(95, 158, 160));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
		lblNewLabel.setBounds(5, 6, 289, 88);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBackground(SystemColor.info);
		textField.setFont(new Font("�L�n������", Font.PLAIN, 30));
		textField.setBounds(294, 6, 289, 88);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("   Set Password");
		lblNewLabel_1.setBackground(new Color(95, 158, 160));
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(5, 94, 289, 88);
		contentPane.add(lblNewLabel_1);

		textField_1 = new JPasswordField();
		textField_1.setBackground(SystemColor.info);
		textField_1.setFont(new Font("�L�n������", Font.PLAIN, 30));
		textField_1.setBounds(294, 94, 289, 88);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("   Confirm Password");
		lblNewLabel_2.setBackground(new Color(95, 158, 160));
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(5, 182, 289, 88);
		contentPane.add(lblNewLabel_2);

		textField_2 = new JPasswordField();
		textField_2.setBackground(SystemColor.info);
		textField_2.setFont(new Font("�L�n������", Font.PLAIN, 30));
		textField_2.setBounds(294, 182, 289, 88);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JButton btnRegisterTourist = new JButton("Register");
		btnRegisterTourist.setBackground(new Color(0, 139, 139));
		btnRegisterTourist.setFont(new Font("Arial Black", Font.PLAIN, 32));
		btnRegisterTourist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int op = TouristOperation.addUser(textField.getText(), new String(textField_1.getPassword()),
						new String(textField_2.getPassword()));
				switch (op) {
				case 0:
					Tourist.userList = TouristOperation.uploadUserList();
					new PopFrame("Welcome!");
					dispose();
					break;
				case 1:
					new PopFrame("error: Password doesn't match.");
					textField_1.setText("");
					textField_2.setText("");
					break;
				case 2:
					new PopFrame("error: This user name has been used.");
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					break;
				case 3:
					new PopFrame("error: Please fill all the blanks.");
					break;
				default:
					break;
				}

			}
		});

		textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {
				if ((int) arg0.getKeyChar() == 10) {
					btnRegisterTourist.doClick();
				}
			}
		});
		textField_1.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {
				if ((int) arg0.getKeyChar() == 10) {
					btnRegisterTourist.doClick();
				}
			}
		});
		textField_2.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {
				if ((int) arg0.getKeyChar() == 10) {
					btnRegisterTourist.doClick();
				}
			}
		});

		btnNo = new JButton("DON'T WANNA JOIN");
		btnNo.setBounds(5, 368, 578, 88);
		btnNo.setBackground(new Color(0, 139, 139));
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNo.setFont(new Font("Arial Black", Font.PLAIN, 20));
		contentPane.add(btnNo);
		btnRegisterTourist.setBounds(5, 275, 289, 88);
		contentPane.add(btnRegisterTourist);

		JButton btnRegisterLandlord = new JButton("Register as Landlord");

		btnRegisterLandlord.setFont(new Font("Arial Black", Font.PLAIN, 32));
		btnRegisterLandlord.setBackground(new Color(0, 139, 139));
		btnRegisterLandlord.setBounds(294, 275, 289, 88);
		btnRegisterLandlord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int op = LandlordOperation.addUser(textField.getText(), new String(textField_1.getPassword()),
						new String(textField_2.getPassword()));
				switch (op) {
				case 0:
					Landlord.landlordList = LandlordOperation.uploadUserList();
					new PopFrame("Welcome!");
					dispose();
					break;
				case 1:
					new PopFrame("error: Password doesn't match.");
					textField_1.setText("");
					textField_2.setText("");
					break;
				case 2:
					new PopFrame("error: This user name has been used.");
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					break;
				case 3:
					new PopFrame("error: Please fill all the blanks.");
					break;
				default:
					break;
				}
			}
		});
		contentPane.add(btnRegisterLandlord);
	}
}
