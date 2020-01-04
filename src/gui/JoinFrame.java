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

import member.UserList;
import member.UserOperation;
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
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblNewLabel = new JLabel("   Set User Name");
		lblNewLabel.setBackground(new Color(95, 158, 160));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
		lblNewLabel.setBounds(55, 86, 88, 16);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBackground(SystemColor.info);
		textField.setFont(new Font("�L�n������", Font.PLAIN, 30));
		textField.setBounds(181, 81, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("   Set Password");
		lblNewLabel_1.setBackground(new Color(95, 158, 160));
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(55, 114, 88, 16);
		contentPane.add(lblNewLabel_1);

		textField_1 = new JPasswordField();
		textField_1.setBackground(SystemColor.info);
		textField_1.setFont(new Font("�L�n������", Font.PLAIN, 30));
		textField_1.setBounds(181, 109, 130, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("   Confirm Password");
		lblNewLabel_2.setBackground(new Color(95, 158, 160));
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(55, 142, 115, 16);
		contentPane.add(lblNewLabel_2);

		textField_2 = new JPasswordField();
		textField_2.setBackground(SystemColor.info);
		textField_2.setFont(new Font("�L�n������", Font.PLAIN, 30));
		textField_2.setBounds(181, 137, 130, 26);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JButton btnRegister = new JButton("Register");
		btnRegister.setBackground(new Color(0, 139, 139));
		btnRegister.setFont(new Font("Arial Black", Font.PLAIN, 32));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int op = UserOperation.addUser(textField.getText(), new String(textField_1.getPassword()), new String(textField_2.getPassword()));
				switch (op) {
				case 0:
					UserList.userList = UserOperation.uploadUserList();
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
				if((int)arg0.getKeyChar()==10) {
					btnRegister.doClick();
				}	
			}
		});
		textField_1.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {
				if((int)arg0.getKeyChar()==10) {
					btnRegister.doClick();
				}	
			}
		});
		textField_2.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {
				if((int)arg0.getKeyChar()==10) {
					btnRegister.doClick();
				}	
			}
		});

		btnNo = new JButton("DON'T WANNA JOIN");
		btnNo.setBackground(new Color(0, 139, 139));
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNo.setFont(new Font("Arial Black", Font.PLAIN, 20));
		contentPane.add(btnNo);
		btnRegister.setBounds(181, 187, 117, 29);
		contentPane.add(btnRegister);

	}

}
