package testing.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TestGUIGUI {

	private JFrame frame;
	private JPanel panelA, panelB, panelC;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestGUIGUI window = new TestGUIGUI();
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
	public TestGUIGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelAInitialize();
	}
	
	private void panelAInitialize() {
		panelA = new JPanel();
		panelA.setBackground(Color.BLUE);
		frame.getContentPane().add(panelA, BorderLayout.CENTER);
		panelA.setLayout(null);
		
		JButton btnGoToA = new JButton("go to B");
		btnGoToA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelBInitialize();
				panelB.setVisible(true);
				panelA.setVisible(false);
			}
		});
		btnGoToA.setBounds(160, 106, 87, 23);
		panelA.add(btnGoToA);
		
		JLabel lblPanelA = new JLabel("Panel A");
		lblPanelA.setBackground(SystemColor.windowText);
		lblPanelA.setBounds(177, 54, 46, 15);
		panelA.add(lblPanelA);
		
		JButton btnGoToC = new JButton("go to C");
		btnGoToC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCInitialize();
				panelC.setVisible(true);
				panelA.setVisible(false);
			}
		});
		btnGoToC.setBounds(160, 153, 87, 23);
		panelA.add(btnGoToC);
		
		JButton button = new JButton("go to D");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelD paneld = new PanelD();
				paneld.btnGoToA.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						panelAInitialize();
						panelA.setVisible(true);
						paneld.setVisible(false);
					}
				});
				paneld.btnGoToE.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						PanelE panele = new PanelE();
						frame.getContentPane().add(panele, BorderLayout.CENTER);
						panele.setVisible(true);
						paneld.setVisible(false);
					}
				});
				frame.getContentPane().add(paneld, BorderLayout.CENTER);
				paneld.setVisible(true);
				panelA.setVisible(false);
				
			}
		});
		button.setBounds(160, 199, 87, 23);
		panelA.add(button);
	}
	
	private void panelBInitialize() {
		panelB = new JPanel();
		panelB.setBackground(Color.RED);
		frame.getContentPane().add(panelB, BorderLayout.CENTER);
		panelB.setLayout(null);
		
		JButton btnGoToB = new JButton("go to A");
		btnGoToB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelAInitialize();
				panelA.setVisible(true);
				panelB.setVisible(false);
			}
		});
		btnGoToB.setBounds(160, 106, 87, 23);
		panelB.add(btnGoToB);
		
		JLabel lblPanelB = new JLabel("Panel B");
		lblPanelB.setBackground(SystemColor.windowText);
		lblPanelB.setBounds(177, 54, 46, 15);
		panelB.add(lblPanelB);
		
		JButton btnGoToC = new JButton("go to C");
		btnGoToC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCInitialize();
				panelC.setVisible(true);
				panelB.setVisible(false);
			}
		});
		btnGoToC.setBounds(160, 153, 87, 23);
		panelB.add(btnGoToC);
	}
	
	private void panelCInitialize() {
		panelC = new JPanel();
		panelC.setBackground(Color.GREEN);
		frame.getContentPane().add(panelC, BorderLayout.CENTER);
		panelC.setLayout(null);
		
		JButton btnGoToA = new JButton("go to A");
		btnGoToA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelAInitialize();
				panelA.setVisible(true);
				panelC.setVisible(false);
			}
		});
		btnGoToA.setBounds(160, 106, 87, 23);
		panelC.add(btnGoToA);
		
		JLabel lblPanelC = new JLabel("Panel C");
		lblPanelC.setBackground(SystemColor.windowText);
		lblPanelC.setBounds(177, 54, 46, 15);
		panelC.add(lblPanelC);
		
		JButton btnGoToC = new JButton("go to B");
		btnGoToC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelBInitialize();
				panelB.setVisible(true);
				panelC.setVisible(false);
			}
		});
		btnGoToC.setBounds(160, 153, 87, 23);
		panelC.add(btnGoToC);
	}
}
