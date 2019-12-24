package testing.gui;
import java.awt.EventQueue;

import javax.swing.*;
import java.awt.SystemColor;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyFirstGUI {

	private JFrame frmMyframe;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFirstGUI window = new MyFirstGUI();
					window.frmMyframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MyFirstGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMyframe = new JFrame();
		frmMyframe.getContentPane().setBackground(SystemColor.desktop);
		frmMyframe.setTitle("MyFrame\r\n");
		frmMyframe.setBackground(SystemColor.textHighlight);
		frmMyframe.setBounds(100, 100, 450, 300);
		frmMyframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(175, 238, 238));
		panel.setBounds(10, 10, 414, 241);
		frmMyframe.getContentPane().add(panel);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(15);
		
		JButton btnNewButton = new JButton("¬d¸ß");
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tmp = textField.getText();
				JPanel panel2 = new JPanel();
				panel2.setBackground(new Color(175, 238, 238));
				panel2.setBounds(10, 10, 414, 241);
				frmMyframe.getContentPane().add(panel2);
				JTextField textField2 = new JTextField();
				panel2.add(textField2);
				textField2.setText("You entered "+tmp);
				textField2.setColumns(15);
				panel.setVisible(false);
				
			}
		});
		frmMyframe.getContentPane().setLayout(null);
		
	
		
	}
}
