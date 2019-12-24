package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class PopFrame extends JFrame {

	private JPanel contentPane;
	private JButton btnOk;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() { 
				try {
					PopFrame frame = new PopFrame();
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
	public PopFrame() {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblErrorPleaseReenter = new JLabel("example");
		lblErrorPleaseReenter.setBackground(new Color(204, 204, 255));
		lblErrorPleaseReenter.setBounds(50,70,759,178);
		lblErrorPleaseReenter.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorPleaseReenter.setFont(new Font("Agency FB", Font.PLAIN, 52));
		contentPane.add(lblErrorPleaseReenter);
		
		btnOk = new JButton("OK");
		btnOk.setBackground(new Color(153, 204, 204));
		btnOk.setFont(new Font("Agency FB", Font.PLAIN, 60));
		btnOk.setBounds(14, 441, 854, 99);
		contentPane.add(btnOk);
		btnOk.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		} );
		setVisible(true);
	}
	
	public JButton getBtnOk() {
		return btnOk;
	}
	
	public PopFrame(String str) {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblErrorPleaseReenter = new JLabel(str);
		lblErrorPleaseReenter.setBackground(new Color(204, 204, 255));
		lblErrorPleaseReenter.setBounds(50,70,759,178);
		lblErrorPleaseReenter.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorPleaseReenter.setFont(new Font("Agency FB", Font.PLAIN, 52));
		contentPane.add(lblErrorPleaseReenter);
		
		btnOk = new JButton("OK");
		btnOk.setBackground(new Color(153, 204, 204));
		btnOk.setFont(new Font("Agency FB", Font.PLAIN, 60));
		btnOk.setBounds(14, 341, 854, 99);
		contentPane.add(btnOk);
		btnOk.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		} );
		setVisible(true);
	}

}
