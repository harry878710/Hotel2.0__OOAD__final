package testing.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import gui.PopFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TestToBookPanel extends JFrame {
	private JPanel contentPane;
	private JButton btnOk;
	private JTextField txtAaaaa;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestToBookPanel frame = new TestToBookPanel();
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
	public TestToBookPanel() {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 399, 190);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnOk = new JButton("OK");
		btnOk.setBounds(157, 99, 87, 23);
		contentPane.add(btnOk);
		
		txtAaaaa = new JTextField();
		txtAaaaa.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if(txtAaaaa.getText().equals("aaaaa"))txtAaaaa.setText("");
			}
		});
		txtAaaaa.setText("aaaaa");
		txtAaaaa.setBounds(83, 42, 229, 32);
		contentPane.add(txtAaaaa);
		txtAaaaa.setColumns(10);
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
	
}
