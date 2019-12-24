package testing.gui;

import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TestGUI1 {

	private JFrame frame;
	private JPanel panel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestGUI1 window = new TestGUI1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestGUI1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBackground(Color.PINK);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(115, 52, 140, 26);
		comboBox.addItem("¹w³]");
		comboBox.addItem("1");
		comboBox.addItem("2");
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					String s = (String)e.getItem();
					System.out.println("Select "+s);
				}else if(e.getStateChange()==ItemEvent.DESELECTED) {
					String s = (String)e.getItem();
					System.out.println("Deselect "+s);
				}
				
			}
			
		});
		panel.add(comboBox);
		
		JButton btnEnter = new JButton("enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnEnter.setBounds(271, 54, 55, 24);
		panel.add(btnEnter);
	}

	
}
