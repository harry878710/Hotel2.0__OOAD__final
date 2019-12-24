package testing.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ErrorFrame extends JFrame {

	private JPanel contentPane;
	private JButton btnOk;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErrorFrame frame = new ErrorFrame();
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
	public ErrorFrame() {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 399, 191);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblErrorPleaseReenter = new JLabel("Error");
		lblErrorPleaseReenter.setBounds(66, 44, 279, 23);
		contentPane.add(lblErrorPleaseReenter);
		
		btnOk = new JButton("OK");
		btnOk.setBounds(157, 99, 87, 23);
		contentPane.add(btnOk);
		btnOk.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				 
			}
		} );
		setVisible(true);
	}
	
	public JButton getBtnOk() {
		return btnOk;
	}
	
	public ErrorFrame(String str) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 270, 158);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblErrorPleaseReenter = new JLabel("Error: "+str);
		lblErrorPleaseReenter.setBounds(41, 29, 172, 15);
		contentPane.add(lblErrorPleaseReenter);
		
		btnOk = new JButton("OK");
		btnOk.setBounds(84, 67, 87, 23);
		contentPane.add(btnOk);
		btnOk.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		} );
		setVisible(true);
	}

}
