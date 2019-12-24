package testing.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

public class TestGUI2 {

	private JFrame frame;
	private Test1 test1;
	private Test2 test2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestGUI2 window = new TestGUI2();
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
	public TestGUI2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel1();
	}

	private void panel1() {
		test1 = new Test1();
		test1.getBtnButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// String btnString = e.getActionCommand();
				// -> btnString == text of btn
				try {
					Long d = new Long(test1.getTextEnter());
					panel2();
					test2.setVisible(true);
					test1.setVisible(false);
				} catch (NumberFormatException e1) {
					ErrorFrame test4 = new ErrorFrame();
				}
				
			}
		});
		frame.getContentPane().add(test1);
	}

	private void panel2() {
		String tmp = "";
		tmp = test1.getTextEnter() + "\n" + test1.getPassEnter() + "\n" + test1.getComboSelect();
		test2 = new Test2();
		test2.setTextArea(tmp);
		test2.getBtnBack().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel1();
				test1.setVisible(true);
				test2.setVisible(false);
			}
		});
		frame.getContentPane().add(test2);

	}
}
